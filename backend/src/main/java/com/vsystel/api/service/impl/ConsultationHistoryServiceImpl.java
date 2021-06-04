package com.vsystel.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vsystel.api.models.PriceConsultResult;
import com.vsystel.api.repository.ConsultationHistoryRepository;
import com.vsystel.api.service.ConsultationHistoryService;

@Service
public class ConsultationHistoryServiceImpl implements ConsultationHistoryService {

	private static final Integer NUMBER_OF_ITEMS = 5;
	
	@Autowired
	private ConsultationHistoryRepository consultationHistoryRepository;

	@Override
	public List<PriceConsultResult> getHistoryPageable(Integer page) {
		Pageable pageable = PageRequest.of(page, NUMBER_OF_ITEMS);
		return consultationHistoryRepository.findAll(pageable).getContent();
	}

	@Override
	public void save(PriceConsultResult consult) {
		consultationHistoryRepository.save(consult);
	}

}
