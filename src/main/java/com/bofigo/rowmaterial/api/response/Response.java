package com.bofigo.rowmaterial.api.response;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@Setter
@Getter
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

}
