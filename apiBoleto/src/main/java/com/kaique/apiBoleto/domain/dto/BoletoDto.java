package com.kaique.apiBoleto.domain.dto;

import jakarta.validation.constraints.NotBlank;

public class BoletoDto {

	@NotBlank
	private String codigoBarras;
	
	public BoletoDto() {
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	
	
}
