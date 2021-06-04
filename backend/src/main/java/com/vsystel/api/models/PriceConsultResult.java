package com.vsystel.api.models;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "consultation_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceConsultResult {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("origin_area_code")
	private Integer originAreaCode;
	
	@JsonProperty("target_area_code")
	private Integer targetAreaCode;
	
	@JsonProperty("call_duration_limit")
	private Integer callDurationLimit;
	
	@JsonProperty("plan_description")
	private String planDescription;
	
	@JsonProperty("price_with_plan")
	private BigDecimal priceWithPlan;
	
	@JsonProperty("price_without_plan")
	private BigDecimal priceWithoutPlan;
	
	private Instant date;
}
