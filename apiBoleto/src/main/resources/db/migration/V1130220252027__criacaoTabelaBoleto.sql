  create table tb_boleto (
        id bigint generated by default as identity,
        codigo_barras varchar(255),
        data_atualizacao timestamp(6) with time zone,
        data_criacao timestamp(6) with time zone,
        status_boleto varchar(255) check (status_boleto in ('INICIALIZADO','VALIDADO','ERRO_VALIDACAO','PAGO','ERRO_PAGAMENTO')),
        primary key (id)
    )