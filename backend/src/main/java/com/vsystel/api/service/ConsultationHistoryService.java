package com.vsystel.api.service;

import java.util.List;

import com.vsystel.api.models.PriceConsultResult;

public interface ConsultationHistoryService {
	
	public List<PriceConsultResult> getHistoryPageable(Integer page);
	
	public void save(PriceConsultResult consult);
}
