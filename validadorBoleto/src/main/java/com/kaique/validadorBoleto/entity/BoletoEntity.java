package com.kaique.validadorBoleto.entity;


import java.time.OffsetDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.kaique.validadorBoleto.entity.enums.StatusBoleto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_boleto")
public class BoletoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigoBarras;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_boleto")
	private StatusBoleto statusBoleto = StatusBoleto.INICIALIZADO;

	@CreationTimestamp
	private OffsetDateTime dataCriacao;

	@UpdateTimestamp
	private OffsetDateTime dataAtualizacao;

	public BoletoEntity() {
	}

	public BoletoEntity(Long id, String codigoBarras, StatusBoleto statusBoleto, OffsetDateTime dataCriacao,
			OffsetDateTime dataAtualizacao) {
		this.id = id;
		this.codigoBarras = codigoBarras;
		this.statusBoleto = statusBoleto;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public StatusBoleto getStatusBoleto() {
		return statusBoleto;
	}

	public void setStatusBoleto(StatusBoleto statusBoleto) {
		this.statusBoleto = statusBoleto;
	}

	public OffsetDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(OffsetDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public OffsetDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(OffsetDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoletoEntity other = (BoletoEntity) obj;
		return Objects.equals(id, other.id);
	}
}
