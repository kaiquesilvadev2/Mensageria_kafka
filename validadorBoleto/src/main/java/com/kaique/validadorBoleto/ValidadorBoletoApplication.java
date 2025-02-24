package com.kaique.validadorBoleto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class ValidadorBoletoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidadorBoletoApplication.class, args);
	}

}
