package com.kaique.apiBoleto.api.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.apiBoleto.domain.dto.BoletoDto;
import com.kaique.apiBoleto.domain.entities.Boleto;
import com.kaique.apiBoleto.domain.services.BoletoService;

@RestController
@RequestMapping("/boletos")
public class BoletoControlle {

	@Autowired
	private BoletoService service;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Boleto name(@RequestBody BoletoDto dto) {
		return service.salvar(dto);
	}
}
