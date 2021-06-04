package com.vsystel.api.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Field {

	private String fieldName;
	private String message;
	
}
