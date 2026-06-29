insert into estado(nome_estd , sigla) 
values
	('Acre', 'AC'),
	('Alagoas', 'AL'),
	('Amapá', 'AP'),
	('Amazonas', 'AM'),
	('Bahia', 'BA'),
	('Ceará', 'CE'),
	('Distrito Federal', 'DF'),
	('Espírito Santo', 'ES'),
	('Goiás', 'GO'),
	('Maranhão', 'MA'),
	('Mato Grosso', 'MT'),
	('Mato Grosso do Sul', 'MS'),
	('Minas Gerais', 'MG'),
	('Pará', 'PA'),
	('Paraíba','PB'),
	('Paraná','PR'),
	('Pernambuco','PE'),
	('Piauí','PI'),
	('Rio de Janeiro','RJ'),
	('Rio Grande do Norte','RN'),
	('Rio Grande do Sul','RS'),
	('Rondônia','RO'),
	('Roraima','RR'),
	('Santa Catarina','SC'),
	('São Paulo', 'SP'),
	('Sergipe', 'SE'),
	('Tocantins', 'TO');

insert into cidade(nome_cid, id_estd_cid)
values
	('Sorocaba',25),
	('Santa Helena',16),
	('São Paulo',25),
	('Brasília',7),
	('Foz do iguaçu', 16),
	('Maringá',16),
	('Florianópolis',24),
	('Santa Helena',10);

insert into cargo(nome_cargo)
values
	('Gerente'),
	('Operador');
	
insert into status_locacao(nome_statuslocacao)
values
	('Em aberto'),
	('Finalizada'),
	('Atrasada');

insert into status_pagamento(nome_status_pagamento)
values
	('Em aberto'),
	('Pago');

insert into status_veiculo(nome_status)
values 
	('Disponível'),
	('Alugado'),
	('Em manutenção'),
	('Avariado');



select * from cidade;
select * from estado;
select * from cargo;
select * from status_locacao;
select * from status_pagamento;
select * from status_veiculo;