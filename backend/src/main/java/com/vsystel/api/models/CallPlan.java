package com.vsystel.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Entity
@Table(name = "call_plans")
@Builder
public class CallPlan {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	@JsonProperty("call_duration_limit")
	private Integer callDurationLimit;

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Integer getCallDurationLimit() {
		return callDurationLimit;
	}
}
