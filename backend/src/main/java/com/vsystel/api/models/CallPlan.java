package com.vsystel.api.models;

import java.math.BigDecimal;

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
@Table(name = "call_plans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CallPlan {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	@JsonProperty("call_duration_limit")
	private Integer callDurationLimit;
	
	public BigDecimal calcPriceWithPlan(Integer callDuration, BigDecimal valueMinute) {
		if(callDuration <= callDurationLimit) {
			return BigDecimal.ZERO;
		}
		Integer overtime = callDuration - callDurationLimit;
		return valueMinute.add(valueMinute.multiply(BigDecimal.valueOf(0.1)))
				.multiply(BigDecimal.valueOf(overtime));
	}
}
