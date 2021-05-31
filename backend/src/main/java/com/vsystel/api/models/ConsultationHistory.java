package com.vsystel.api.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "consultation_history")
@Data
@Builder
public class ConsultationHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Integer originAreaCode;
	private Integer targetAreaCode;
	private Integer callDurationLimit;
	private String planDescription;
	private BigDecimal priceWithPlan;
	private BigDecimal priceWithoutPlan;
}
