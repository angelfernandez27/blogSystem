package com.blogsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String resourceName;
	private String fieldName;
	private Long fieldValue;
	public ResourceNotFoundException(String resourceName, String fieldName, Long id) {
		super(String.format("%s Not Found with : %s : '%s'", resourceName, fieldName, id));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = id;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Long getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(Long fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	
	
	
	

}
