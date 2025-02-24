package com.kaique.validadorBoleto.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.kaique.avro.Boleto;
import com.kaique.validadorBoleto.mapper.BoletoMapper;
import com.kaique.validadorBoleto.services.ValidarBoletoService;


@Service
public class BoletoConsumer  {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoletoConsumer.class);
    private final ValidarBoletoService validarBoletoService;

    public BoletoConsumer(ValidarBoletoService validarBoletoService) {
        this.validarBoletoService = validarBoletoService;
    }

    @KafkaListener(topics = "${spring.kafka.topico-boleto}", groupId = "${spring.kafka.consumer.group-id}")
    public void consomeBoleto(Boleto boleto, Acknowledgment ack) throws InterruptedException {
     LOGGER.info(String.format("Consumindo mensagem -> %s", boleto));
       // validarBoletoService.validar(BoletoMapper.toEntity(boleto));
        ack.acknowledge();
    }
}