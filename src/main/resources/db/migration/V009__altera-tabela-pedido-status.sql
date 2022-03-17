ALTER TABLE pedido
drop COLUMN status;

ALTER TABLE pedido
add column status varchar(10);