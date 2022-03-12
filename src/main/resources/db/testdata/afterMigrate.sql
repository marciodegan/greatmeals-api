set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table produto auto_increment= 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;

insert into cozinha (id, nome) values (1, 'Chinesa');
insert into cozinha (id, nome) values (2, 'Brasileira');

insert into estado(nome) values ('Ceará');

insert into cidade (nome, estado_id) values ('Fortaleza', 1);
insert into forma_pagamento (id, descricao) values (1, 'dinheiro');
insert into forma_pagamento (id, descricao) values (2, 'débito');
insert into forma_pagamento (id, descricao) values (3, 'crédito');

insert into permissao (nome, descricao) values ('Pode alterar', 'pode alterar campos x, y, z');

insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo) values (1, 'Restaurante 1', 0.00, 1, "90000-001", "Rua das Rosas", "100", "ap11", "Centro", 1, utc_timestamp, utc_timestamp, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo) values (2, 'Restaurante 2', 0.00, 1, "90000-002", "Rua dos Cravos", "200", "ap12", "Centro", 1, utc_timestamp, utc_timestamp, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo) values (3, 'Restaurante 3', 0.00, 1, "90000-003", "Rua das Margaridas", "300", "ap13", "Centro", 1, utc_timestamp, utc_timestamp, true);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 2), (2, 3), (3, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Produto 1', 'Descricao do Produto 1', 50.00, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Produto 2', 'Descricao do Produto 2', 60.00, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Produto 3', 'Descricao do Produto 3', 70.00, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Produto 4', 'Descricao do Produto 4', 80.00, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Produto 5', 'Descricao do Produto 5', 90.00, 1, 2);

insert into grupo (nome) values ('Administrativo');
insert into grupo (nome) values ('Diretoria');

