package com.web.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.Getter;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoDataFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String tableName;

	public NoDataFoundException(String tableName) {
		super(String.format("No data found! Table %s has no data!", tableName));

		this.tableName = tableName;
	}

}
