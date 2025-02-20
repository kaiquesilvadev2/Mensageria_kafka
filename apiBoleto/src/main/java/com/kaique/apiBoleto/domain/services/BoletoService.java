package com.kaique.apiBoleto.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.apiBoleto.domain.dto.BoletoDto;
import com.kaique.apiBoleto.domain.entities.Boleto;
import com.kaique.apiBoleto.domain.exceptions.EntidadeJaExistenteException;
import com.kaique.apiBoleto.domain.kafka.BoletoProducer;
import com.kaique.apiBoleto.domain.mapper.BoletoMapper;
import com.kaique.apiBoleto.domain.repositories.BoletoRepository;

@Service
public class BoletoService {

	@Autowired
	private BoletoRepository repository;
	
	@Autowired
	private BoletoProducer boletoProducer;

	public Boleto salvar(BoletoDto dto) {
		Boleto boleto = new Boleto();

		if (repository.buscaCodigobarra(dto.getCodigoBarras()).isPresent())
			throw new EntidadeJaExistenteException("Esse código já esta presente no sistema.");

		converteDto(boleto, dto);
		boleto = repository.save(boleto);
		
		boletoProducer.enviarMensagem(BoletoMapper.toAvro(boleto));
		
		return boleto;
	}

	public void converteDto(Boleto entidade, BoletoDto dto) {

		entidade.setCodigoBarras(dto.getCodigoBarras());
	}
}
