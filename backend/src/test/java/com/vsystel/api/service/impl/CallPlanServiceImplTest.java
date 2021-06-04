package com.vsystel.api.service.impl;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import com.vsystel.api.models.CallPlan;
import com.vsystel.api.repository.CallPlanRepository;

@ContextConfiguration(classes = { CallPlanServiceImpl.class })
@ExtendWith(MockitoExtension.class)
public class CallPlanServiceImplTest {

	private static final Long PLAN_ID = 1L;

	@Mock
	private CallPlanRepository callPlanRepository;

	@Mock
	private CallPlan callPlan;

	@InjectMocks
	private CallPlanServiceImpl callPlanService;

	private List<CallPlan> emptyListOfCallPlan = new ArrayList<>();
	private List<CallPlan> listOfCallPlan = asList(callPlan);

	@Test
	public void givenHasNoPlansOnDatabaseThenReturnEmptyList() {
		when(callPlanRepository.findAll()).thenReturn(emptyListOfCallPlan);

		List<CallPlan> methodResult = callPlanService.getPlans();

		assertTrue(methodResult.isEmpty());
	}

	@Test
	public void givenHasNoPlansOnDatabaseThenReturnPlansAsList() {
		when(callPlanRepository.findAll()).thenReturn(listOfCallPlan);

		List<CallPlan> methodResult = callPlanService.getPlans();

		assertFalse(methodResult.isEmpty());
	}


	@Test
	public void givenAInvalidPlanIdWhenFindPlanByIdThenThrowException() {
		when(callPlanRepository.findById(eq(PLAN_ID))).thenReturn(Optional.empty());
		
		NoSuchElementException exception = assertThrows(NoSuchElementException.class,
				() -> callPlanService.getById(PLAN_ID));
		
		assertTrue(exception.getMessage().contains("No value present"));
	}
 
 	@Test
 	public void teste() {
		when(callPlanRepository.findById(eq(PLAN_ID))).thenReturn(Optional.of(callPlan));

		CallPlan methodResult = callPlanService.getById(PLAN_ID);

		assertEquals(callPlan, methodResult);
	}
}
