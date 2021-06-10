package com.vsystel.api.service;

import java.util.List;

import com.vsystel.api.models.Price;
import com.vsystel.api.models.PriceConsult;
import com.vsystel.api.models.PriceConsultResult;

public interface PriceConsultService {
	
	public List<Price> getAll();
	
	public PriceConsultResult getPrices(PriceConsult priceConsult);
}
