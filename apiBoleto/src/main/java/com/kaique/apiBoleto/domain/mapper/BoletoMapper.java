package com.kaique.apiBoleto.domain.mapper;

import com.kaique.avro.Boleto;

public class BoletoMapper {

	public static Boleto toAvro(com.kaique.apiBoleto.domain.entities.Boleto boleto) {
		// @formatter:off
		return Boleto.newBuilder()
				.setCodigoBarras(boleto.getCodigoBarras())
				.setSituacaoBoleto(boleto.getStatusBoleto()
				.ordinal())
				.build();
		// @formatter:on

	}
}
