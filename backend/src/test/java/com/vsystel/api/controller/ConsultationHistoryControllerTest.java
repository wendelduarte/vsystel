package com.vsystel.api.controller;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.vsystel.api.models.PriceConsultResult;
import com.vsystel.api.service.ConsultationHistoryService;

@WebMvcTest(ConsultationHistoryController.class)
public class ConsultationHistoryControllerTest {

	private static final Integer PAGE = 1;
	private static final Long ID = 1L;
	private static final Integer ORIGIN_AREA_CODE = 011;
	private static final Integer TARGET_AREA_CODE = 017;
	private static final Integer CALL_DURATION_LIMIT = 60;
	private static final String DESCRIPTION = "FaleMais 60";
	private static final BigDecimal PRICE_WITH_PLAN = BigDecimal.valueOf(37.40);
	private static final BigDecimal PRICE_WITHOUT_PLAN = BigDecimal.valueOf(136.0);
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ConsultationHistoryService consultationHistoryService;
	
	private PriceConsultResult consultationHistory = PriceConsultResult.builder()
			.id(ID)
			.originAreaCode(ORIGIN_AREA_CODE)
			.targetAreaCode(TARGET_AREA_CODE)
			.callDurationLimit(CALL_DURATION_LIMIT)
			.planDescription(DESCRIPTION)
			.priceWithPlan(PRICE_WITH_PLAN)
			.priceWithoutPlan(PRICE_WITHOUT_PLAN)
			.build();
	
	private List<PriceConsultResult> history = asList(consultationHistory);
	
	@Test
	public void givenAGetRequestInPlansEndpointAndHasPlansThenReturnPlans() throws Exception {
		when(consultationHistoryService.getHistoryPageable(Mockito.eq(PAGE))).thenReturn(history);
		
		mockMvc.perform(get("/plans/call/history?page=" + PAGE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].id", is(ID.intValue())))
			.andExpect(jsonPath("$[0].origin_area_code", is(ORIGIN_AREA_CODE)))
			.andExpect(jsonPath("$[0].target_area_code", is(TARGET_AREA_CODE)))
			.andExpect(jsonPath("$[0].call_duration_limit", is(CALL_DURATION_LIMIT)))
			.andExpect(jsonPath("$[0].plan_description", is(DESCRIPTION)))
			.andExpect(jsonPath("$[0].price_with_plan", is(PRICE_WITH_PLAN.doubleValue())))
			.andExpect(jsonPath("$[0].price_without_plan", is(PRICE_WITHOUT_PLAN.doubleValue())));
	}
	
	@Test
	public void givenAGetRequestInPlansEndpointAndHasNotPlansThenReturnEmpty() throws Exception {
		mockMvc.perform(get("/plans/call/history?page=" + PAGE))
			.andExpect(status().isNoContent());
	}
 
}
