insert into cozinha (id, nome) values (1, 'Chinesa');
insert into cozinha (id, nome) values (2, 'Brasileira');

insert into estado(nome) values ('Ceará');

insert into cidade (nome, estado_id) values ('Fortaleza', 1);
insert into forma_pagamento (id, descricao) values (1, 'dinheiro');
insert into forma_pagamento (id, descricao) values (2, 'débito');
insert into forma_pagamento (id, descricao) values (3, 'crédito');


insert into permissao (nome, descricao) values ('Pode alterar', 'pode alterar campos x, y, z');

insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) values (1, 'Restaurante 1', 0.00, 1, "90000-001", "Rua das Rosas", "100", "ap11", "Centro", 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) values (2, 'Restaurante 2', 0.00, 1, "90000-002", "Rua dos Cravos", "200", "ap12", "Centro", 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) values (3, 'Restaurante 3', 0.00, 1, "90000-003", "Rua das Margaridas", "300", "ap13", "Centro", 1, utc_timestamp, utc_timestamp);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 2), (2, 3), (3, 1);
