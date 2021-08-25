package br.com.conductor.crud.config.validation.dto;

public class ErrorFormDto {

	private String field;
	
	private String error;

	public ErrorFormDto(String field, String error) {
		super();
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}
	
}
