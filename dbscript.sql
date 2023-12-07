-- public.usuario definition

-- Drop table

-- DROP TABLE public.usuario;

CREATE TABLE public.usuario (
	id serial4 NOT NULL,
	nome text NOT NULL,
	email text NOT NULL,
	endereco text NULL,
	numero text NULL,
	municipio text NULL,
	uf varchar(2) NULL,
	momento_cadastro timestamp NOT NULL,
	CONSTRAINT usuario_pkey PRIMARY KEY (id)
);