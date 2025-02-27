package com.kaique.apiBoleto.domain.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.kaique.apiBoleto.domain.mapper.BoletoMapper;
import com.kaique.apiBoleto.domain.services.BoletoService;
import com.kaique.avro.Boleto;

@Service
public class NotificacaoConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificacaoConsumer.class);

    private final BoletoService boletoService;

    public NotificacaoConsumer(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @KafkaListener(topics = "${spring.kafka.topico-notificacao}")
    public void consumer(@Payload Boleto boleto) {
        LOGGER.info(String.format("Consumindo notificacao -> %s", boleto));
        boletoService.atualizar(BoletoMapper.toEntity(boleto));
    }
}
