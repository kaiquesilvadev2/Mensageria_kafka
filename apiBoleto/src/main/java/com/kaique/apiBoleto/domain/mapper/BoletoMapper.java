package com.kaique.apiBoleto.domain.mapper;

import com.kaique.apiBoleto.domain.entities.BoletoEntity;
import com.kaique.apiBoleto.domain.enuns.StatusBoleto;
import com.kaique.avro.Boleto;

public class BoletoMapper {

	public static Boleto toAvro(BoletoEntity boleto) {
		// @formatter:off
		return Boleto.newBuilder()
				.setCodigoBarras(boleto.getCodigoBarras())
				.setSituacaoBoleto(boleto.getStatusBoleto()
				.ordinal())
				.build();
		// @formatter:on

	}
	
    public static BoletoEntity toEntity(Boleto boleto) {
        BoletoEntity boletoEntity = new BoletoEntity();
        boletoEntity.setCodigoBarras(boleto.getCodigoBarras().toString());
        boletoEntity.setStatusBoleto(StatusBoleto.values()[boleto.getSituacaoBoleto()]);
        return boletoEntity;
    }
}
