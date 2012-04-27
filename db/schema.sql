/*
Created: 17/4/2011
Modified: 4/7/2011
Model: co.oper
Database: Oracle 10g
*/

-- Create sequences section -------------------------------------------------

CREATE SEQUENCE "mensagem_id_seq"
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE "relatorio_visita_id_seq"
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE "tecnico_id_seq"
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE "orcamento_id_seq"
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

-- Create tables section -------------------------------------------------

-- Table usuario

CREATE TABLE "usuario"(
  "codigo" Varchar2(20 ) NOT NULL,
  "senha" Char(64 ) NOT NULL,
  "email" Varchar2(80 ),
  "status" Varchar2(10 ) NOT NULL,
  "ultimo_acesso" Timestamp(6),
  "criado_em" Timestamp(6),
  "nivel" Number(2,0) NOT NULL
)
/

-- Add keys for table usuario

ALTER TABLE "usuario" ADD CONSTRAINT "pk_usuario" PRIMARY KEY ("codigo")
/

-- Table associado

CREATE TABLE "associado"(
  "codigo" Varchar2(20 ) NOT NULL,
  "nome" Varchar2(120 ) NOT NULL,
  "cpfcnpj" Varchar2(18 ) NOT NULL,
  "data_cadastro" Timestamp(6) NOT NULL,
  "tem_conta" Number(1,0) NOT NULL
)
/

-- Add keys for table associado

ALTER TABLE "associado" ADD CONSTRAINT "pk_associado" PRIMARY KEY ("codigo")
/

-- Table mensagem

CREATE TABLE "mensagem"(
  "id" Integer NOT NULL,
  "conteudo" Varchar2(1000 ) NOT NULL,
  "data" Timestamp(6) NOT NULL,
  "lida" Number(1,0) NOT NULL,
  "enviado_por" Varchar2(20 ),
  "enviado_para" Varchar2(20 ),
  "enviado_por_fonte" Number(2,0)
)
/

-- Add keys for table mensagem

ALTER TABLE "mensagem" ADD CONSTRAINT "pk_mensagem" PRIMARY KEY ("id")
/

-- Table titulo

CREATE TABLE "titulo"(
  "id" Integer NOT NULL,
  "data_criacao" Timestamp(6) NOT NULL,
  "data_vencimento" Timestamp(6) NOT NULL,
  "status" Varchar2(30 ) NOT NULL,
  "tipo" Varchar2(30 ) NOT NULL,
  "valor" Number(10,2) NOT NULL,
  "associado" Varchar2(20 ) NOT NULL
)
/

-- Add keys for table titulo

ALTER TABLE "titulo" ADD CONSTRAINT "pk_titulo" PRIMARY KEY ("id")
/

-- Table graos

CREATE TABLE "graos"(
  "romaneio" Integer NOT NULL,
  "data" Timestamp(6) NOT NULL,
  "descontos" Number(10,2) NOT NULL,
  "descricao" Varchar2(200 ) NOT NULL,
  "motorista_caminhao" Varchar2(80 ) NOT NULL,
  "peso_qtde" Number(10,2) NOT NULL,
  "placaCaminhao" Char(7 ) NOT NULL,
  "produto" Varchar2(30 ) NOT NULL,
  "status" Varchar2(30 ) NOT NULL,
  "associado" Varchar2(20 )
)
/

-- Add keys for table graos

ALTER TABLE "graos" ADD CONSTRAINT "pk_graos" PRIMARY KEY ("romaneio")
/

-- Table relatorio_visita

CREATE TABLE "relatorio_visita"(
  "id" Integer NOT NULL,
  "data" Timestamp(6) NOT NULL,
  "descricao" Varchar2(1000 ) NOT NULL,
  "associado" Varchar2(20 ) NOT NULL,
  "tecnico" Varchar2(20 ) NOT NULL,
  "comentario" Varchar2(1000 ),
  "nota" Number(2,0)
)
/

-- Add keys for table relatorio_visita

ALTER TABLE "relatorio_visita" ADD CONSTRAINT "pk_relatorio_visita" PRIMARY KEY ("id")
/

-- Table tecnico

CREATE TABLE "tecnico"(
  "id" Varchar2(20 ) NOT NULL,
  "nome" Varchar2(80 ) NOT NULL
)
/

-- Add keys for table tecnico

ALTER TABLE "tecnico" ADD CONSTRAINT "pk_tecnico" PRIMARY KEY ("id")
/

-- Table loja

CREATE TABLE "loja"(
  "codigo" Integer NOT NULL
)
/

-- Add keys for table loja

ALTER TABLE "loja" ADD CONSTRAINT "pk_loja" PRIMARY KEY ("codigo")
/

-- Table produto

CREATE TABLE "produto"(
  "codigo" Integer NOT NULL,
  "descricao" Varchar2(500 ) NOT NULL,
  "preco" Number(10,2) NOT NULL
)
/

-- Add keys for table produto

ALTER TABLE "produto" ADD CONSTRAINT "pk_produto" PRIMARY KEY ("codigo")
/

-- Table produto_na_loja

CREATE TABLE "produto_na_loja"(
  "loja" Integer NOT NULL,
  "produto" Integer NOT NULL,
  "quantidade" Number(15,2)
)
/

-- Add keys for table produto_na_loja

ALTER TABLE "produto_na_loja" ADD CONSTRAINT "pk_produto_na_loja" PRIMARY KEY ("loja","produto")
/

-- Table orcamento

CREATE TABLE "orcamento"(
  "id" Integer NOT NULL,
  "total" Number(10,2) NOT NULL,
  "comentario" Varchar2(1000 ),
  "associado" Varchar2(20 ) NOT NULL,
  "data" Timestamp(6) NOT NULL
)
/

-- Add keys for table orcamento

ALTER TABLE "orcamento" ADD CONSTRAINT "pk_orcamento" PRIMARY KEY ("id")
/

-- Table produto_do_orcamento

CREATE TABLE "produto_do_orcamento"(
  "orcamento" Integer NOT NULL,
  "produto" Integer NOT NULL,
  "quantidade" Number(15,2)
)
/

-- Add keys for table produto_do_orcamento

ALTER TABLE "produto_do_orcamento" ADD CONSTRAINT "pk_produto_no_orcamento" PRIMARY KEY ("orcamento","produto")
/

-- Table acesso

CREATE TABLE "acesso"(
  "data" Timestamp(6) NOT NULL,
  "ip" Varchar2(40 ) NOT NULL,
  "pagina" Varchar2(200 ) NOT NULL,
  "cod_associado" Char(20 )
)
/

-- Add keys for table acesso

ALTER TABLE "acesso" ADD CONSTRAINT "pk_acesso" PRIMARY KEY ("data","ip")
/

-- Trigger for sequence mensagem_id_seq for column id in table mensagem ---------

CREATE OR REPLACE TRIGGER "ts_mensagem_mensagem_id_seq" BEFORE INSERT
ON "mensagem" FOR EACH ROW
BEGIN
	SELECT "mensagem_id_seq".nextval INTO :new."id" FROM DUAL;
END;
/
CREATE OR REPLACE TRIGGER "tsu_mensagem_mensagem_id_seq" AFTER UPDATE OF "id"
ON "mensagem" FOR EACH ROW
BEGIN
	RAISE_APPLICATION_ERROR(-20010,'Cannot update column "id" in table "mensagem" as it uses sequence.');
END;
/

-- Trigger for sequence relatorio_visita_id_seq for column id in table relatorio_visita ---------

CREATE OR REPLACE TRIGGER "ts_relatorio_visita_relatori_0" BEFORE INSERT
ON "relatorio_visita" FOR EACH ROW
BEGIN
	SELECT "relatorio_visita_id_seq".nextval INTO :new."id" FROM DUAL;
END;
/
CREATE OR REPLACE TRIGGER "tsu_relatorio_visita_relator_0" AFTER UPDATE OF "id"
ON "relatorio_visita" FOR EACH ROW
BEGIN
	RAISE_APPLICATION_ERROR(-20010,'Cannot update column "id" in table "relatorio_visita" as it uses sequence.');
END;
/

-- Trigger for sequence orcamento_id_seq for column id in table orcamento ---------

CREATE OR REPLACE TRIGGER "ts_orcamento_orcamento_id_seq" BEFORE INSERT
ON "orcamento" FOR EACH ROW
BEGIN
	SELECT "orcamento_id_seq".nextval INTO :new."id" FROM DUAL;
END;
/
CREATE OR REPLACE TRIGGER "tsu_orcamento_orcamento_id_seq" AFTER UPDATE OF "id"
ON "orcamento" FOR EACH ROW
BEGIN
	RAISE_APPLICATION_ERROR(-20010,'Cannot update column "id" in table "orcamento" as it uses sequence.');
END;
/

-- Create relationships section ------------------------------------------------- 

ALTER TABLE "titulo" ADD CONSTRAINT "Relationship3" FOREIGN KEY ("associado") REFERENCES "associado" ("codigo")
/

ALTER TABLE "graos" ADD CONSTRAINT "fk_associado_graos" FOREIGN KEY ("associado") REFERENCES "associado" ("codigo")
/

ALTER TABLE "relatorio_visita" ADD CONSTRAINT "fk_assoc_relat_visita" FOREIGN KEY ("associado") REFERENCES "associado" ("codigo")
/

ALTER TABLE "relatorio_visita" ADD CONSTRAINT "fk_tecnico_relat_visita" FOREIGN KEY ("tecnico") REFERENCES "tecnico" ("id")
/

ALTER TABLE "produto_na_loja" ADD CONSTRAINT "fk_loja_produto_na_loja" FOREIGN KEY ("loja") REFERENCES "loja" ("codigo")
/

ALTER TABLE "produto_na_loja" ADD CONSTRAINT "fk_produto_produto_na_loja" FOREIGN KEY ("produto") REFERENCES "produto" ("codigo")
/

ALTER TABLE "produto_do_orcamento" ADD CONSTRAINT "fk_orc_produto_do_orc" FOREIGN KEY ("orcamento") REFERENCES "orcamento" ("id")
/

ALTER TABLE "produto_do_orcamento" ADD CONSTRAINT "fk_prod_prod_do_orc" FOREIGN KEY ("produto") REFERENCES "produto" ("codigo")
/

ALTER TABLE "orcamento" ADD CONSTRAINT "fk_associado_orcamento" FOREIGN KEY ("associado") REFERENCES "associado" ("codigo")
/




