package com.kaique.apiBoleto.domain.services;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.kaique.apiBoleto.domain.dto.BoletoDto;
import com.kaique.apiBoleto.domain.entities.BoletoEntity;
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
	
	
	public BoletoEntity buscaPorCodigo(String codigo) {
		return repository.buscaCodigobarra(codigo)
				.orElseThrow(() -> new  EntidadeJaExistenteException("Boleto não encontrado"));
	}

	public BoletoEntity salvar(BoletoDto dto) {
		BoletoEntity boleto = new BoletoEntity();

		if (repository.buscaCodigobarra(dto.getCodigoBarras()).isPresent())
			throw new EntidadeJaExistenteException("Esse código já esta presente no sistema.");

		converteDto(boleto, dto);
		boleto = repository.save(boleto);
		
		boletoProducer.enviarMensagem(BoletoMapper.toAvro(boleto));
		
		return boleto;
	}

	public void converteDto(BoletoEntity entidade, BoletoDto dto) {

		entidade.setCodigoBarras(dto.getCodigoBarras());
	}
	
	  private BoletoEntity recuperaBoleto(String codigoBarras) {
	        return repository.buscaCodigobarra(codigoBarras)
	                .orElseThrow(() -> new  EntidadeJaExistenteException("Boleto não encontrado"));
	    }
	

    public void atualizar(BoletoEntity boleto) {
        var boletoAtual = recuperaBoleto(boleto.getCodigoBarras());

        boletoAtual.setStatusBoleto(boleto.getStatusBoleto());
        repository.save(boletoAtual);
    }
}
