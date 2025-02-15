package com.kaique.apiBoleto.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kaique.apiBoleto.domain.entities.Boleto;

public interface BoletoRepository extends JpaRepository<Boleto, Long>{
	
	@Query(nativeQuery = true , value = "SELECT * FROM tb_boleto b WHERE b.codigo_barras = ?1 LIMIT 1;")
	Optional<Boleto> buscaCodigobarra(String codigo);
}
