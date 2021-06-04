package com.vsystel.api.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "price_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Integer originAreaCode;
	private Integer targetAreaCode;
	private BigDecimal pricePerMinute;
}
