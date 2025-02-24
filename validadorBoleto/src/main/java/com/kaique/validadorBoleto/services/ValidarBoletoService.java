package com.kaique.validadorBoleto.services;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.kaique.validadorBoleto.entity.BoletoEntity;
import com.kaique.validadorBoleto.entity.enums.StatusBoleto;
import com.kaique.validadorBoleto.kafka.NotificacaoProducer;
import com.kaique.validadorBoleto.mapper.BoletoMapper;
import com.kaique.validadorBoleto.repositores.BoletoRepository;

@Service
public class ValidarBoletoService {


    private final BoletoRepository boletoRepository;
    private final NotificacaoProducer notificacaoProducer;

    private final PagarBoletoService pagarBoletoService;

    public ValidarBoletoService(BoletoRepository boletoRepository, NotificacaoProducer notificacaoProducer, PagarBoletoService pagarBoletoService) {
        this.boletoRepository = boletoRepository;
        this.notificacaoProducer = notificacaoProducer;
        this.pagarBoletoService = pagarBoletoService;
    }

    public void validar(BoletoEntity boleto) {
        var codigo = Integer.parseInt(boleto.getCodigoBarras().substring(0,1));
        if (codigo % 2 == 0) {
            complementarBoletoErro(boleto);
            boletoRepository.save(boleto);
            notificacaoProducer.enviarMensagem(BoletoMapper.toAvro(boleto));
        } else {
            complementarBoletoSucesso(boleto);
            boletoRepository.save(boleto);
            notificacaoProducer.enviarMensagem(BoletoMapper.toAvro(boleto));
            pagarBoletoService.pagar(boleto);
        }
    }

    private void complementarBoletoErro(BoletoEntity boleto) {
        boleto.setDataCriacao(OffsetDateTime.now());
        boleto.setDataAtualizacao(OffsetDateTime.now());
        boleto.setStatusBoleto(StatusBoleto.ERRO_VALIDACAO);
    }

    private void complementarBoletoSucesso(BoletoEntity boleto) {
        boleto.setDataCriacao(OffsetDateTime.now());
        boleto.setDataAtualizacao(OffsetDateTime.now());
        boleto.setStatusBoleto(StatusBoleto.VALIDADO);
    }
}