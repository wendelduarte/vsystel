package com.vsystel.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsystel.api.models.PriceConsult;
import com.vsystel.api.models.PriceConsultResult;
import com.vsystel.api.service.PriceConsultService;

@RestController
@RequestMapping(value = "/plans/call/consult")
public class PriceConsultController {

	@Autowired
	private PriceConsultService priceConsultService;
	
	@PostMapping
	public ResponseEntity<PriceConsultResult> getHistoryPagineted(@Valid @RequestBody PriceConsult priceConsult){
		return new ResponseEntity<PriceConsultResult>(
				priceConsultService.getPrices(priceConsult),
				HttpStatus.OK);
	}
}