package com.kaique.apiBoleto.domain.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kaique.avro.Boleto;


@Component
public class BoletoProducer {

    @Value("${spring.kafka.topico-boleto}")
    public String topico;
    private final KafkaTemplate<String, Boleto> kafkaTemplate;

    public BoletoProducer(KafkaTemplate<String, Boleto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarMensagem(Boleto boleto) {
        kafkaTemplate.send(topico , boleto);
    }

  

}