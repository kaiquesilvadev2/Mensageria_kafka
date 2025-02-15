package com.kaique.apiBoleto.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class EntidadeJaExistenteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntidadeJaExistenteException(String msg) {
		super(msg);
	}
}