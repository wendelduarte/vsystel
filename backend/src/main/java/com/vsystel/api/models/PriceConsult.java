package com.vsystel.api.models;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceConsult {

	@NotNull
	@JsonProperty("origin_area_code")
	private final Integer originAreaCode;
	
	@NotNull
	@JsonProperty("target_area_code")
	private final Integer targetAreaCode;

	@NotNull
	@JsonProperty("call_duration")
	private final Integer callDuartion;
	
	@NotNull
	@JsonProperty("plan_id")
	private final Long planId;
}
