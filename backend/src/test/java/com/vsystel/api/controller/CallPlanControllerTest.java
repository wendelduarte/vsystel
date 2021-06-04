package com.vsystel.api.controller;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.vsystel.api.models.CallPlan;
import com.vsystel.api.service.CallPlanService;

@WebMvcTest(CallPlanController.class)
public class CallPlanControllerTest {

	private static final Long ID = 1L;
	private static final String DESCRIPTION = "FaleMais30";
	private static final Integer CALL_DURATION_LIMIT = 30;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CallPlanService callPlanService;
	
	private CallPlan callPlan = CallPlan.builder()
			.id(ID)
			.description(DESCRIPTION)
			.callDurationLimit(CALL_DURATION_LIMIT)
			.build();
	  
	private List<CallPlan> callPlans = asList(callPlan);
	 
	
	@Test
	public void givenAGetRequestInPlansEndpointAndHasPlansThenReturnPlans() throws Exception {
		when(callPlanService.getPlans()).thenReturn(callPlans);
		
		mockMvc.perform(get("/plans/call"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].id", is(ID.intValue())))
			.andExpect(jsonPath("$[0].description", is(DESCRIPTION)))
			.andExpect(jsonPath("$[0].call_duration_limit", is(CALL_DURATION_LIMIT)));
	}
	
	@Test
	public void givenAGetRequestInPlansEndpointAndHasNotPlansThenReturnEmpty() throws Exception {
		mockMvc.perform(get("/plans/call"))
			.andExpect(status().isNoContent());
	}
 
}
