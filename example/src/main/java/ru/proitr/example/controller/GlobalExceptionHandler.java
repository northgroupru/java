package ru.proitr.example.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.proitr.example.exception.ProjectException;
import ru.proitr.example.model.rest.ApiResponse;
import ru.proitr.example.model.rest.ApiValidationError;
import ru.proitr.example.model.status.ProjectStatusCode;
import ru.proitr.example.service.ProjectValidationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gainutdinov on 08.09.17.
 */
@ControllerAdvice
public class GlobalExceptionHandler
{
	@ExceptionHandler(Exception.class)
	public Object handleException(HttpServletRequest request, HttpServletResponse response, Exception ex)
	{
		ProjectStatusCode projectStatusCode;
		ApiResponse<Object> resp;

		if (ex instanceof ProjectException)
		{
			ProjectException projectException = (ProjectException) ex;
			resp = new ApiResponse<>(projectException.getStatus());

			if (ex.getMessage() != null)
			{
				resp.setMessage(ex.getMessage());
			}

			Errors validationErrors = projectException.getValidationErrors();

			if (validationErrors != null)
			{
				resp.setValidationErrors(validationErrors);

				for (ApiValidationError validationError : resp.getValidationErrors())
				{
					if (validationError.getDescription() != null && validationError.getDescription().startsWith("validation."))
					{
						validationError.setCode(validationError.getCode());
						validationError.setDescription(ProjectValidationUtils.getErrorMessage(validationError.getCode()));
					}
				}
			}
		}
		else
		{
			projectStatusCode = ProjectStatusCode.UNKNOWN_ERROR;
			resp = new ApiResponse<>(projectStatusCode);
		}

		if (!resp.getMessage().trim().endsWith("."))
		{
			resp.appendMessage(".");
		}

		return getResponseObject(request, response, resp);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Object handleHttpMessageNotReadable(HttpServletRequest request, HttpServletResponse response, HttpMessageNotReadableException ex)
	{
		ProjectStatusCode projectStatusCode = ProjectStatusCode.ILLEGAL_DATA_FORMAT;
		ApiResponse<Object> resp = new ApiResponse<>(projectStatusCode);

		return getResponseObject(request, response, resp);
	}

	private Object getResponseObject(HttpServletRequest request, HttpServletResponse response, ApiResponse<Object> resp)
	{
		if (request.getHeader(HttpHeaders.ACCEPT) != null)
		{
			Map<String, Object> model = new HashMap<>();
			model.put("data", resp);

			return new ModelAndView("freemarker/exception/error", model);
		}
		else
		{
			return new ResponseEntity(resp, HttpStatus.OK);
		}
	}

}
