insert into cozinha (id, nome) values (1, 'Chinesa');
insert into cozinha (id, nome) values (2, 'Brasileira');

insert into forma_pagamento (id, descricao) values (1, 'dinheiro');
insert into forma_pagamento (id, descricao) values (2, 'débito');
insert into forma_pagamento (id, descricao) values (3, 'crédito');

insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Restaurante 1', 0.00, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Restaurante 2', 10.0, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Restaurante 3', 0.00, 2);

insert into permissao (nome, descricao) values ('Pode alterar', 'pode alterar campos x, y, z');

insert into estado(nome) values ('Ceará');

insert into cidade (nome, estado_id) values ('Fortaleza', 1);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 2), (2, 3), (3, 1);
