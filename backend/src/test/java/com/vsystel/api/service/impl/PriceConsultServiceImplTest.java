package com.vsystel.api.service.impl;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import com.vsystel.api.models.CallPlan;
import com.vsystel.api.models.Price;
import com.vsystel.api.models.PriceConsult;
import com.vsystel.api.models.PriceConsultResult;
import com.vsystel.api.repository.PriceRepository;
import com.vsystel.api.service.CallPlanService;
import com.vsystel.api.service.ConsultationHistoryService;

@ContextConfiguration(classes = { PriceConsultServiceImpl.class })
@ExtendWith(MockitoExtension.class)
public class PriceConsultServiceImplTest {
	
	private static final Integer ORIGIN_AREA_CODE = 11;
	private static final Integer TARGET_AREA_CODE = 16;
	private static final Integer CALL_DURATION = 100;
	private static final Integer CALL_DURATION_WITH_LIMIT = 25;
	private static final Integer PLAN_CALL_DURATION_LIMIT = 30;
	private static final Long PLAN_ID = 1L;
	private static final BigDecimal PRICE_PER_MINUTE = BigDecimal.valueOf(1.90);
	private static final BigDecimal EXPECTED_PRICE_WITH_PLAN = BigDecimal.valueOf(146.30);
	private static final BigDecimal EXPECTED_PRICE_WITHOUT_PLAN = BigDecimal.valueOf(190.0);
	private static final BigDecimal EXPECTED_PRICE_WITH_PLAN_AND_LIMIT = BigDecimal.valueOf(0);
	private static final BigDecimal EXPECTED_PRICE_WITHOUT_PLAN_AND_LIMIT = BigDecimal.valueOf(47.5);
	private static final String DESCRIPTION = "FaleMais 30";
	
	@Mock
	private PriceRepository priceRepository;
	
	@Mock
	private ConsultationHistoryService consultationHistoryService;
	
	@Mock
	private CallPlanService callPlanService;

	@Mock
	private PriceConsultResult consultationHistory;
	
	@Captor
	private ArgumentCaptor<PriceConsultResult> captorPriceConsultResult;
	
	
	@InjectMocks
	private PriceConsultServiceImpl priceConsultServiceServiceImpl;
	
	private PriceConsult priceConsult = PriceConsult.builder()
			.originAreaCode(ORIGIN_AREA_CODE)
			.targetAreaCode(TARGET_AREA_CODE)
			.callDuartion(CALL_DURATION)
			.planId(PLAN_ID)
			.build();
	
	private PriceConsult consultWithLimitDuration = PriceConsult.builder()
			.originAreaCode(ORIGIN_AREA_CODE)
			.targetAreaCode(TARGET_AREA_CODE)
			.callDuartion(CALL_DURATION_WITH_LIMIT)
			.planId(PLAN_ID)
			.build();
	
	private Price price = Price.builder()
			.originAreaCode(ORIGIN_AREA_CODE)
			.targetAreaCode(TARGET_AREA_CODE)
			.pricePerMinute(PRICE_PER_MINUTE)
			.build();
	
	private CallPlan plan = CallPlan.builder()
			.id(PLAN_ID)
			.description(DESCRIPTION)
			.callDurationLimit(PLAN_CALL_DURATION_LIMIT)
			.build();
	@Test
	public void givenCallIsLargerThanPlanLimitThenShouldCalcAndReturnPrices() {
		when(priceRepository.findByOriginAreaCode(eq(ORIGIN_AREA_CODE))).thenReturn(asList(price));
		when(callPlanService.getById(eq(PLAN_ID))).thenReturn(plan);
		
		PriceConsultResult methodResult = priceConsultServiceServiceImpl.getPrices(priceConsult);
		
		verify(consultationHistoryService).save(captorPriceConsultResult.capture());
		assertTrue(captorPriceConsultResult.getValue().getPriceWithPlan().compareTo(EXPECTED_PRICE_WITH_PLAN) == 0);
		assertEquals(EXPECTED_PRICE_WITHOUT_PLAN, captorPriceConsultResult.getValue().getPriceWithoutPlan());
		assertEquals(captorPriceConsultResult.getValue(), methodResult);
	}
	
	@Test
	public void givenCallIsNotLargerThanPlanLimitThenShouldCalcAndReturnPrices() {
		when(priceRepository.findByOriginAreaCode(eq(ORIGIN_AREA_CODE))).thenReturn(asList(price));
		when(callPlanService.getById(eq(PLAN_ID))).thenReturn(plan);
		
		PriceConsultResult methodResult = priceConsultServiceServiceImpl.getPrices(consultWithLimitDuration);
		
		verify(consultationHistoryService).save(captorPriceConsultResult.capture());
		assertEquals(EXPECTED_PRICE_WITH_PLAN_AND_LIMIT, captorPriceConsultResult.getValue().getPriceWithPlan());
		assertEquals(EXPECTED_PRICE_WITHOUT_PLAN_AND_LIMIT, captorPriceConsultResult.getValue().getPriceWithoutPlan());
		assertEquals(captorPriceConsultResult.getValue(), methodResult);
	}
	
	@Test
	public void givenIsAInvalidOriginAndDestinyCall() {
		when(callPlanService.getById(eq(PLAN_ID))).thenReturn(plan);
		
		PriceConsultResult methodResult = priceConsultServiceServiceImpl.getPrices(priceConsult);
		
		verify(consultationHistoryService).save(captorPriceConsultResult.capture());
		assertTrue(captorPriceConsultResult.getValue().getPriceWithPlan() == null);
		assertTrue(captorPriceConsultResult.getValue().getPriceWithoutPlan() == null);
		assertEquals(captorPriceConsultResult.getValue(), methodResult);
	}
}