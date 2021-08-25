package br.com.conductor.crud.config.validation.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorFormDtoTest {

	public ErrorFormDto error;
	
	private static final String FIELD_DEFAULT = "FIELD_ERROR";
	private static final String ERROR_DEFAULT = "MSG_ERROR";
	
	@BeforeEach
	void setup() {
		error = new ErrorFormDto(FIELD_DEFAULT, ERROR_DEFAULT);
	}

	@Test
	void verifyGetField() {
		assertEquals(FIELD_DEFAULT, error.getField());
	}
	
	@Test
	void verifyGetError() {
		assertEquals(ERROR_DEFAULT, error.getError());
	}
	
}
