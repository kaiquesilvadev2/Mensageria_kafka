package com.kaique.apiBoleto.domain.enuns;

public enum StatusBoleto {

	INICIALIZADO("INICIALIZADO"),
	VALIDADO("VALIDADO"),
	ERRO_VALIDACAO("Erro de validação"),
	PAGO("PAGO"),
	ERRO_PAGAMENTO("Erro no pagamento");

	private String descricao;

	private StatusBoleto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
