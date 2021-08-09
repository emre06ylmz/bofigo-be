package com.bofigo.rowmaterial.api.response;

import java.util.Date;

public class Response<T> {

	private ResponseStatus status = ResponseStatus.SUCESS;
	private String message;
	private T data;
	private Date timestamp = new Date();

	public Response() {
	}

	public Response(T data) {
		this.data = data;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
