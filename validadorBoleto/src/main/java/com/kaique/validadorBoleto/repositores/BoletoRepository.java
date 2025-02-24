package com.kaique.validadorBoleto.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaique.validadorBoleto.entity.BoletoEntity;

public interface BoletoRepository extends JpaRepository<BoletoEntity, Long> {
}
