create table pedido (
	id bigint not null auto_increment,
	data_confirmacao datetime(6) not null,
	data_criacao datetime(6) not null,
	data_entrega datetime(6) not null,
	endereco_bairro varchar(255),
	endereco_cep varchar(255),
	endereco_complemento varchar(255),
	endereco_logradouro varchar(255),
	endereco_numero varchar(255),
	status integer not null,
	subtotal decimal(19,2) not null,
	taxa_frete decimal(19,2) not null,
	valor_total decimal(19,2) not null,
	cliente_id bigint not null,
	endereco_cidade_id bigint,
	forma_pagamento_id bigint not null,
	restaurante_id bigint not null,
	primary key (id),

	constraint fk_pedido_endereco_cidade foreign key (endereco_cidade_id) references cidade (id),
    constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante (id),
    constraint fk_pedido_usuario_cliente foreign key (cliente_id) references usuario (id),
    constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id)

) engine=InnoDB default charset=utf8;

create table item_pedido (
	id bigint not null auto_increment,
	observacao varchar(80) not null,
	preco_total decimal(19,2) not null,
	preco_unitario decimal(19,2) not null,
	quantidade integer not null,
	pedido_id bigint not null,
	produto_id bigint not null,
	primary key (id),
	constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido (id),
    constraint fk_item_pedido_produto foreign key (produto_id) references produto (id)
) engine=InnoDB default charset=utf8;
