insert into cozinha (id, nome) values (1, 'Chinesa');
insert into cozinha (id, nome) values (2, 'Brasileira');

insert into forma_pagamento (descricao) values ('dinheiro');

insert into restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) values ('Restaurante 1', 0.00, 1, 1);
insert into restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) values ('Restaurante 2', 10.0, 1, 1);
insert into restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) values ('Restaurante 3', 0.00, 2, 1);

insert into permissao (nome, descricao) values ('Pode alterar', 'pode alterar campos x, y, z');

insert into estado(nome) values ('Ceará');

insert into cidade (nome, estado_id) values ('Fortaleza', 1);
