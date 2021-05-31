package com.vsystel.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsystel.api.models.CallPlan;
import com.vsystel.api.repository.CallPlanRepository;
import com.vsystel.api.service.CallPlanService;

@Service
public class CallPlanServiceImpl implements CallPlanService {

	@Autowired
	private CallPlanRepository callPlanRepository;

	@Override
	public List<CallPlan> getPlans() {
		return callPlanRepository.findAll();
	}
	
	
}
