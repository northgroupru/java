package ru.proitr.example.exception;

import org.springframework.validation.Errors;
import ru.proitr.example.model.status.ProjectStatusCode;

/**
 * Created by Gainutdinov on 08.09.17.
 */
public class ProjectException extends RuntimeException
{
	private ProjectStatusCode status;
	private Errors validationErrors;

	public ProjectException(ProjectStatusCode status)
	{
		super(status.getMessage());
		this.status = status;
	}

	public ProjectException(ProjectStatusCode status, String message)
	{
		super(message);
		this.status = status;
	}

	public ProjectException(ProjectStatusCode status, Errors validationErrors)
	{
		this(status);
		this.validationErrors = validationErrors;
	}

	public ProjectStatusCode getStatus()
	{
		return status;
	}

	public void setStatus(ProjectStatusCode status)
	{
		this.status = status;
	}

	public Errors getValidationErrors()
	{
		return validationErrors;
	}

	public void setValidationErrors(Errors validationErrors)
	{
		this.validationErrors = validationErrors;
	}
}
