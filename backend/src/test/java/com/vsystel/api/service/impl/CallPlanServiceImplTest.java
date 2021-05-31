package com.vsystel.api.service.impl;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import com.vsystel.api.models.CallPlan;
import com.vsystel.api.repository.CallPlanRepository;


@ContextConfiguration(classes = { CallPlanServiceImpl.class })
@ExtendWith(MockitoExtension.class)
public class CallPlanServiceImplTest {
	
	@Mock
	private CallPlanRepository callPlanRepository;
	
	@Mock
	private CallPlan callPlan;
	
	@InjectMocks
	private CallPlanServiceImpl callPlanService;
	
	private List<CallPlan> emptyListOfCallPlan = new ArrayList<>();
	private List<CallPlan> listOfCallPlan = asList(callPlan);
	
	@Test
	public void testete() {
		Mockito.when(callPlanRepository.findAll()).thenReturn(emptyListOfCallPlan);
		
		List<CallPlan> methodResult = callPlanService.getPlans();
		
		assertTrue(methodResult.isEmpty());
	}
	
	@Test
	public void testes2() {
		Mockito.when(callPlanRepository.findAll()).thenReturn(listOfCallPlan);
		
		List<CallPlan> methodResult = callPlanService.getPlans();
		
		assertFalse(methodResult.isEmpty());
	}
}
