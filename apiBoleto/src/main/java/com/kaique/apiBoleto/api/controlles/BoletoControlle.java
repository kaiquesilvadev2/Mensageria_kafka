package com.kaique.apiBoleto.api.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.apiBoleto.domain.dto.BoletoDto;
import com.kaique.apiBoleto.domain.entities.BoletoEntity;
import com.kaique.apiBoleto.domain.services.BoletoService;

@RestController
@RequestMapping("/boletos")
public class BoletoControlle {

	@Autowired
	private BoletoService service;
	
	@GetMapping("/{codigo}")
	public BoletoEntity name(@PathVariable String codigo) {
		return service.buscaPorCodigo(codigo);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public BoletoEntity name(@RequestBody BoletoDto dto) {
		return service.salvar(dto);
	}
}
