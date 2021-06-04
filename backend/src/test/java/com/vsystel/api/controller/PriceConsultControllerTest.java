package com.vsystel.api.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.vsystel.api.models.PriceConsult;
import com.vsystel.api.models.PriceConsultResult;
import com.vsystel.api.service.PriceConsultService;

@WebMvcTest(PriceConsultController.class)
public class PriceConsultControllerTest {

	private static final Long ID = 1L;
	private static final Integer ORIGIN_AREA_CODE = 11;
	private static final Integer TARGET_AREA_CODE = 17;
	private static final Integer CALL_DURATION_LIMIT = 60;
	private static final String DESCRIPTION = "FaleMais 60";
	private static final BigDecimal PRICE_WITH_PLAN = BigDecimal.valueOf(37.40);
	private static final BigDecimal PRICE_WITHOUT_PLAN = BigDecimal.valueOf(136.0);
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PriceConsultService priceConsultService;
	
	private PriceConsultResult consultationHistory = PriceConsultResult.builder()
			.id(ID)
			.originAreaCode(ORIGIN_AREA_CODE)
			.targetAreaCode(TARGET_AREA_CODE)
			.callDurationLimit(CALL_DURATION_LIMIT)
			.planDescription(DESCRIPTION)
			.priceWithPlan(PRICE_WITH_PLAN)
			.priceWithoutPlan(PRICE_WITHOUT_PLAN)
			.build();
	
	@Test
	public void givenAGetRequestInPlansEndpointAndHasPlansThenReturnPlans() throws Exception {
		String requestBody = "{\"origin_area_code\": 18,\"target_area_code\": 17,\"call_duration\": 100,\"plan_id\": 1}";
		
		when(priceConsultService.getPrices(any(PriceConsult.class))).thenReturn(consultationHistory);
		
		MockHttpServletRequestBuilder request = post("/plans/call/consult").contentType(MediaType.APPLICATION_JSON).content(requestBody)
                .accept(MediaType.APPLICATION_JSON);
		
		
		mockMvc.perform(request)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(ID.intValue())))
			.andExpect(jsonPath("$.origin_area_code", is(ORIGIN_AREA_CODE)))
			.andExpect(jsonPath("$.target_area_code", is(TARGET_AREA_CODE)))
			.andExpect(jsonPath("$.call_duration_limit", is(CALL_DURATION_LIMIT)))
			.andExpect(jsonPath("$.plan_description", is(DESCRIPTION)))
			.andExpect(jsonPath("$.price_with_plan", is(PRICE_WITH_PLAN.doubleValue())))
			.andExpect(jsonPath("$.price_without_plan", is(PRICE_WITHOUT_PLAN.doubleValue())));
	}
}
