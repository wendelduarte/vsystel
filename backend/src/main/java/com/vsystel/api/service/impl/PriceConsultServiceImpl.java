package com.vsystel.api.service.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsystel.api.models.CallPlan;
import com.vsystel.api.models.PriceConsultResult;
import com.vsystel.api.models.Price;
import com.vsystel.api.models.PriceConsult;
import com.vsystel.api.repository.PriceRepository;
import com.vsystel.api.service.CallPlanService;
import com.vsystel.api.service.ConsultationHistoryService;
import com.vsystel.api.service.PriceConsultService;

@Service
public class PriceConsultServiceImpl implements PriceConsultService {

	private static final int INDEX_OF_UNIQUE_ELEMENT = 0;
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private ConsultationHistoryService consultationHistoryService;
	
	@Autowired
	private CallPlanService callPlanService;

	@Override
	public PriceConsultResult getPrices(PriceConsult priceConsult) {
		PriceConsultResult consult = new PriceConsultResult();
		Optional<Price> priceTable = filterPricesByTargetAreaCode(
				priceRepository.findByOriginAreaCode(priceConsult.getOriginAreaCode()),
				priceConsult.getTargetAreaCode()
		);
		CallPlan plan = callPlanService.getById(priceConsult.getPlanId());
		
		if(priceTable.isPresent()) {
			consult = calculateCallValue(priceConsult, priceTable.get(), plan);
		} else {
			consult =  PriceConsultResult.builder()
					.originAreaCode(priceConsult.getOriginAreaCode())
					.targetAreaCode(priceConsult.getTargetAreaCode())
					.callDurationLimit(priceConsult.getCallDuartion())
					.planDescription(plan.getDescription())
					.date(Instant.now())
					.build();
		}
		
		consultationHistoryService.save(consult);
		return consult;
	}

	private Optional<Price> filterPricesByTargetAreaCode(List<Price> allPrices, Integer targetAreaCode) {
		List<Price> filteredPrice = allPrices.stream()
				.filter(price -> price.getTargetAreaCode().equals(targetAreaCode))
				.collect(Collectors.toList());
		return filteredPrice.isEmpty() ? Optional.empty() : Optional.of(filteredPrice.get(INDEX_OF_UNIQUE_ELEMENT));
	}
	
	private PriceConsultResult calculateCallValue(PriceConsult priceConsult, Price price, CallPlan plan) {
		BigDecimal valueWithPlan = plan.calcPriceWithPlan(priceConsult.getCallDuartion(), price.getPricePerMinute());
		BigDecimal valueWithoutPlan = calcPriceWithoutPlan(priceConsult.getCallDuartion(), price.getPricePerMinute());
		
		return PriceConsultResult.builder()
				.originAreaCode(priceConsult.getOriginAreaCode())
				.targetAreaCode(priceConsult.getTargetAreaCode())
				.callDurationLimit(priceConsult.getCallDuartion())
				.planDescription(plan.getDescription())
				.priceWithPlan(valueWithPlan)
				.priceWithoutPlan(valueWithoutPlan)
				.date(Instant.now())
				.build();
	}

	private BigDecimal calcPriceWithoutPlan(Integer callDuration, BigDecimal pricePerMinute) {
		return pricePerMinute.multiply(BigDecimal.valueOf(callDuration));
	}

}
