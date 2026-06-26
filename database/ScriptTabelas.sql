create table estado (
	id_estd SERIAL primary key,
	nome_estd varchar(50) not null unique,
	sigla varchar(2) not null unique
);

create table cidade (
	id_cid SERIAL primary key,
	nome_cid varchar(100) not null,
	id_estd_cid int,
	
	constraint fk_estado_cidade
		foreign key (id_estd_cid)
		references estado(id_estd)
		on delete cascade
);

create table endereco(
	id_endr SERIAL primary key,
	rua varchar(100),
	numero varchar(20),
	bairro varchar(100),
	complemento varchar(50),
	CEP varchar(10),
	id_cid_endr int,
	
	constraint fk_cidade_endr
		foreign key (id_cid_endr)
		references  cidade(id_cid)
		on delete cascade
);

create table pessoa(
	id_pes SERIAL primary key,
	nome_pes varchar(200),
	CPF varchar(12) unique,
	data_nascimento date,
	sexo bit,
	email varchar(100),
	id_endr_pes int,
	
	constraint fk_endr_pessoa
		foreign key (id_endr_pes)
		references endereco(id_endr)
		on delete cascade
);

create table cliente(
	id_cliente serial primary key,
	id_pes_cliente int,
	
	constraint fk_pes_cliente
		foreign key(id_pes_cliente)
		references pessoa(id_pes)
);

create table telefone(
	id_tel serial primary key,
	numero_tel varchar(10),
	id_pes_tel int,
	
	constraint fk_pessoa_tel
		foreign key (id_pes_tel)
		references pessoa(id_pes)
		on delete cascade
);

create table cargo(
	id_cargo serial primary key,
	nome_cargo varchar(50) unique
)

create table funcionario(
	id_func serial primary key,
	salario decimal(10,2),
	id_pes_func int,
	id_cargo_func int,
	
	constraint fk_pes_func 
		foreign key (id_pes_func)
		references pessoa(id_pes)
		on delete cascade,
	
	constraint fk_cargo_func
		foreign key (id_cargo_func)
		references cargo(id_cargo)
		on delete cascade
);

create table marca(
	id_marca serial primary key,
	nome_marca varchar(50)
);

create table modelo(
	id_modelo serial primary key,
	nome_modelo varchar(50),
	ano_fab int,
	valor_diario decimal(10,2),
	id_marca_modelo int,
	
	constraint fk_marca_modelo
		foreign key (id_marca_modelo)
		references marca(id_marca)
		on delete cascade
);

create table combustivel(
	id_combustivel serial primary key,
	nome_combustivel varchar(50) unique
);

create table status_veiculo(
	id_status serial primary key,
	nome_status varchar(50) unique
);

create table veiculo(
	id_veiculo serial primary key,
	renavam varchar(12) unique,
	placa varchar(9) unique,
	cor varchar(10),
	url_imagem varchar(255),
	kms_atual float,
	tipo_veiculo varchar(50),
	
	id_modelo_veiculo int,
	id_combustivel_veiculo int,
	id_status_veiculo int,
	
	constraint fk_modelo_veiculo
		foreign key (id_modelo_veiculo)
		references modelo(id_modelo)
		on delete cascade,
		
	constraint fk_combustivel_veiculo
		foreign key (id_combustivel_veiculo)
		references combustivel(id_combustivel)
		on delete cascade,
		
	constraint fk_status_veiculo
		foreign key (id_status_veiculo)
		references status_veiculo(id_status)
		on delete cascade
);

create table manutencao(
	id_manut serial primary key,
	data_inicio date,
	data_fim date,
	descricao_manut text,
	valor_manut decimal(10,2),
	
	id_veiculo_manut int,
	
	constraint fk_veiculo_manut
		foreign key(id_veiculo_manut)
		references veiculo(id_veiculo)
);

create table status_locacao(
	id_statuslocacao serial primary key,
	nome_statuslocacao varchar(50)
);

create table locacao(
	id_locacao serial primary key,
	data_saida date,
	data_retorno_previsto date,
	data_retorno_real date,
	kms_saida float,
	kms_retorno float,
	
	id_cliente_locacao int,
	id_veiculo_locacao int,
	id_func_locacao int,
	id_status_locacao int,
	
	constraint fk_cliente_locacao
		foreign key (id_cliente_locacao)
		references cliente(id_cliente)
		on delete cascade,
		
	constraint fk_veiculo_locacao
		foreign key(id_veiculo_locacao)
		references veiculo(id_veiculo)
		on delete cascade,
		
	constraint fk_func_locacao
		foreign key(id_func_locacao)
		references funcionario(id_func)
		on delete cascade,
		
	constraint fk_status_locacao
		foreign key(id_status_locacao)
		references status_locacao(id_statuslocacao)
		on delete cascade
);

create table cobranca_adicional(
	id_cobranca serial primary key,
	descricao_cobranca text,
	valor_cobranca decimal(10,2),
	
	id_locacao_cobranca int,
	
	constraint fk_locacao_cobranca
		foreign key(id_locacao_cobranca)
		references locacao(id_locacao)
		on delete cascade
);

create table status_pagamento(
	id_status_pagamento serial primary key,
	nome_status_pagamento varchar(50)
);

create table pagamento(
	id_pagamento serial primary key,
	data_pagamento date,
	valor_pagamento decimal(10,2),
	forma_pagamento varchar(50),
	
	id_locacao_pagamento int,
	id_status_pagamento int,
	
	constraint fk_locacao_pagamento
		foreign key(id_locacao_pagamento)
		references locacao(id_locacao)
		on delete cascade,
		
	constraint fk_status_pagamento
		foreign key(id_status_pagamento)
		references status_pagamento(id_status_pagamento)
		on delete cascade
);




