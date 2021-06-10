package com.vsystel.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsystel.api.models.Price;
import com.vsystel.api.models.PriceConsult;
import com.vsystel.api.models.PriceConsultResult;
import com.vsystel.api.service.PriceConsultService;

@RestController
@RequestMapping(value = "/plans/call")
public class PriceConsultController {

	@Autowired
	private PriceConsultService priceConsultService;
	
	@GetMapping("/price-list")
	public ResponseEntity<List<Price>> getAll(){
		List<Price> prices = priceConsultService.getAll();
		return prices.isEmpty() ? 
				new ResponseEntity<List<Price>>(prices, HttpStatus.NO_CONTENT)
				: new ResponseEntity<List<Price>>(prices, HttpStatus.OK);
	}
	
	@PostMapping("/consult")
	public ResponseEntity<PriceConsultResult> getHistoryPagineted(@Valid @RequestBody PriceConsult priceConsult){
		return new ResponseEntity<PriceConsultResult>(
				priceConsultService.getPrices(priceConsult),
				HttpStatus.OK);
	}
}