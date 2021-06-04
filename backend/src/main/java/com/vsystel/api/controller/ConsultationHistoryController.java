package com.vsystel.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsystel.api.models.PriceConsultResult;
import com.vsystel.api.service.ConsultationHistoryService;

@RestController
@RequestMapping(value = "/plans/call/history")
public class ConsultationHistoryController {

	@Autowired
	private ConsultationHistoryService consultationHistoryService;
	
	@GetMapping
	public ResponseEntity<List<PriceConsultResult>> getHistoryPagineted(@RequestParam("page") Integer page){
		List<PriceConsultResult> history = consultationHistoryService.getHistoryPageable(page);
		return history.isEmpty() ? 
				new ResponseEntity<List<PriceConsultResult>>(history, HttpStatus.NO_CONTENT)
				: new ResponseEntity<List<PriceConsultResult>>(history, HttpStatus.OK);
	}
	
}
