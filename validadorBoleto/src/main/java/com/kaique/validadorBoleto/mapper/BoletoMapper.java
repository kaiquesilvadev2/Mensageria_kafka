package com.kaique.validadorBoleto.mapper;

import com.kaique.avro.Boleto;
import com.kaique.validadorBoleto.entity.BoletoEntity;
import com.kaique.validadorBoleto.entity.enums.StatusBoleto;

public class BoletoMapper {

    public static BoletoEntity toEntity(Boleto boleto) {
        BoletoEntity boletoEntity = new BoletoEntity();
        boletoEntity.setCodigoBarras(boleto.getCodigoBarras().toString());
        boletoEntity.setStatusBoleto(StatusBoleto.values()[boleto.getSituacaoBoleto()]);
        return boletoEntity;
    }

    public static Boleto toAvro(BoletoEntity boleto) {
        return Boleto.newBuilder()
                .setCodigoBarras(boleto.getCodigoBarras())
                .setSituacaoBoleto(boleto.getStatusBoleto().ordinal()).build();
    }
}