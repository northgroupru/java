package ru.proitr.example.model.rest;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import ru.proitr.example.model.status.ProjectStatusCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gainutdinov on 08.09.17.
 */
public class ApiResponse<T> implements Serializable
{
	private static final long serialVersionUID = 4206019900770090420L;

	private String message;
	private Integer status;

	private List<ApiValidationError> validationErrors;

	public ApiResponse()
	{

	}

	public ApiResponse(ProjectStatusCode projectStatusCode)
	{
		this.status = projectStatusCode.getCode();
		this.message = projectStatusCode.getMessage();
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public List<ApiValidationError> getValidationErrors()
	{
		return validationErrors;
	}

	public void setValidationErrors(List<ApiValidationError> validationErrors)
	{
		this.validationErrors = validationErrors;
	}

	public void setValidationErrors(Errors errors)
	{
		validationErrors = new ArrayList<>();

		for (FieldError fieldError : errors.getFieldErrors())
		{
			ApiValidationError apiValidationError = new ApiValidationError();
			apiValidationError.setResource(fieldError.getObjectName());
			apiValidationError.setCode(fieldError.getCode());
			apiValidationError.setDescription(fieldError.getDefaultMessage());
			apiValidationError.setField(fieldError.getField());

			validationErrors.add(apiValidationError);
		}

		for (ObjectError objectError : errors.getGlobalErrors())
		{
			ApiValidationError apiValidationError = new ApiValidationError();
			apiValidationError.setResource(objectError.getObjectName());
			apiValidationError.setCode(objectError.getCode());

			validationErrors.add(apiValidationError);
		}
	}

	public void appendMessage(String msg)
	{
		if (message == null)
		{
			message = msg;
		}
		else
		{
			message = message + msg;
		}
	}
}
