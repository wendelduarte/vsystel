package com.vsystel.api.service;

import com.vsystel.api.models.PriceConsultResult;
import com.vsystel.api.models.PriceConsult;

public interface PriceConsultService {
	
	public PriceConsultResult getPrices(PriceConsult priceConsult);
}
