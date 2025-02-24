package com.kaique.validadorBoleto.services;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.kaique.validadorBoleto.entity.BoletoEntity;
import com.kaique.validadorBoleto.entity.enums.StatusBoleto;
import com.kaique.validadorBoleto.kafka.NotificacaoProducer;
import com.kaique.validadorBoleto.mapper.BoletoMapper;
import com.kaique.validadorBoleto.repositores.BoletoRepository;

@Service
public class PagarBoletoService {

    private final BoletoRepository boletoRepository;
    private final NotificacaoProducer notificacaoProducer;

    public PagarBoletoService(BoletoRepository boletoRepository, NotificacaoProducer notificacaoProducer) {
        this.boletoRepository = boletoRepository;
        this.notificacaoProducer = notificacaoProducer;
    }

    //@SneakyThrows
    public void pagar(BoletoEntity boleto) {
        //Thread.sleep(10000);
        String codigoBarrasNumeros = boleto.getCodigoBarras().replaceAll("[^0-9]", "");
        if (codigoBarrasNumeros.length() > 47) {
            complementarBoletoErro(boleto);
        } else {
            complementarBoletoSuccesso(boleto);
        }

        boletoRepository.save(boleto);
        notificacaoProducer.enviarMensagem(BoletoMapper.toAvro(boleto));
    }

    private void complementarBoletoErro(BoletoEntity boleto) {
        boleto.setDataAtualizacao(OffsetDateTime.now());
        boleto.setStatusBoleto(StatusBoleto.ERRO_PAGAMENTO);
    }

    private void complementarBoletoSuccesso(BoletoEntity boleto) {
        boleto.setDataAtualizacao(OffsetDateTime.now());
        boleto.setStatusBoleto(StatusBoleto.PAGO);
    }
}
