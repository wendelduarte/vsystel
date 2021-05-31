package com.vsystel.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsystel.api.models.CallPlan;
import com.vsystel.api.service.CallPlanService;

@RestController
@RequestMapping(value = "/plans/call")
public class CallPlanController {

	@Autowired
	private CallPlanService callPlanService;
	
	@GetMapping
	public ResponseEntity<List<CallPlan>> getPlans(){
		List<CallPlan> callPlans = callPlanService.getPlans();
		return callPlans.isEmpty() ? 
				new ResponseEntity<List<CallPlan>>(callPlans, HttpStatus.NO_CONTENT)
				: new ResponseEntity<List<CallPlan>>(callPlans, HttpStatus.OK);
	}
}
