
INSERT INTO mesas (nome,ativo,created,updated) VALUES
	 ('Mesa 1',true,now(),now()),
	 ('Mesa 2',true,now(),now()),
	 ('Mesa 3',true,now(),now()),
	 ('Mesa 4',true,now(),now()),
	 ('Mesa 5',true,now(),now()),
	 ('Mesa 6',true,now(),now()),
	 ('Mesa 7',true,now(),now()),
	 ('Mesa 8',true,now(),now()),
	 ('Mesa 9',true,now(),now()),
	 ('Mesa 10',true,now(),now());

INSERT INTO usuarios (email,senha,nome,created,updated,ativo,mesa_id) VALUES
	 ('admin@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Admin',now(), now(), true, NULL),
	 ('garcom1@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Garom 1',now(), now(), true, NULL),
	 ('garcom2@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Garom 2',now(), now(), true, NULL),
	 ('garcom3@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Garom 3',now(), now(), true, NULL),
	 ('garcom4@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Garom 4',now(), now(), true, NULL),
	 ('garcom5@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Garom 5',now(), now(), true, NULL),
	 ('garcom6@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Garom 6',now(), now(), true, NULL),
	 ('garcom7@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Garom 7',now(), now(), true, NULL),
	 ('garcom8@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Garcom 8',now(), now(), true, NULL),
	 ('garcom9@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Garcom 9',now(), now(), true, NULL),
	 ('mesa1@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Mesa 1',now(), now(), true, 1),
	 ('mesa2@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Mesa 2',now(), now(), true, 2),
	 ('mesa3@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Mesa 3',now(), now(), true, 3),
	 ('mesa4@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Mesa 4',now(), now(), true, 4),
	 ('mesa5@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Mesa 5',now(), now(), true, 5),
	 ('mesa6@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Mesa 6',now(), now(), true, 6),
	 ('mesa7@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Mesa 7',now(), now(), true, 7),
	 ('mesa8@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Mesa 8',now(), now(), true, 8),
	 ('mesa9@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Mesa 9',now(), now(), true, 9),
	 ('mesa10@bvlabs.lol','$2a$10$6OiiR57.a.sVpgiPTbY45uIsgbs.bdyfXSziosh9HF9hhZq96mk2a','Mesa 10',now(), now(), true, 10);
	 
INSERT INTO roles (nome,ativo,created,updated) VALUES
	 ('ADMIN_ROLE',true,now(),now()),
	 ('USER_ROLE',true,now(),now()),
	 ('GARCOM_ROLE',true,now(),now()),
	 ('MESA_ROLE',true,now(),now());

INSERT INTO usuarios_roles (usuario_id,roles_id) VALUES
	 (1,1),
	 (1,2),
	 (2,3),
	 (3,3),
	 (4,3),
	 (5,3),
	 (6,3),
	 (7,3),
	 (8,3),
	 (9,3),
	 (10,3),
	 (11,4),
	 (12,4),
	 (13,4),
	 (14,4),
	 (15,4),
	 (16,4),
	 (17,4),
	 (18,4),
	 (19,4),
	 (20,4);

INSERT INTO estabelecimentos (nome,ativo) VALUES
	 ('Côco Bambu Iguatemi',true),
	 ('Côco Bambu Aldeota',true),
	 ('Côco Bambu Praia',true),
	 ('Órbita Blue',false),
	 ('Chico do Caranguejo',true);

INSERT INTO cardapio_tipo (nome,ativo,created,updated) VALUES
	 ('COMIDA',true,now(),now()),
	 ('APERITIVO',true,now(),now()),
	 ('BEBIDA',true,now(),now());

INSERT INTO cardapio_icone (nome,ativo,created,updated) VALUES
	 ('local_dining',true,now(),now()),
	 ('egg',true,now(),now()),
	 ('wine_bar',true,now(),now()),
	 ('sports_bar',true,now(),now()),
	 ('liquor',true,now(),now());

INSERT INTO cardapios (estabelecimento_id,nome,ativo,preco,cardapio_tipo_id,cardapio_icone_id) VALUES
	 (1,'Batata frita',true,15.00,1,1),
	 (1,'Pizza',true,17.00,1,1),
	 (1,'Arroz',true,8.00,1,1),
	 (1,'Coxinha',true,7.00,1,2),
	 (1,'Vinho',true,19.00,3,3),
	 (1,'Cerveja',true,12.00,3,4),
	 (1,'Whisky',true,65.00,3,5),
	 (1,'Feijão',true,9.85,1,1),
	 (1,'Isca de Peixe',true,14.79,2,2);
	 
INSERT INTO atendentes (nome,ativo,created,updated) VALUES
	 ('Roberto',true,now(),now()),
	 ('Paulo',true,now(),now());


/*INSERT INTO atendimentos (mesa_id,atendente_id,ativo,created,updated,status) VALUES
	 (2,1,true,now(),now(),'EM_ATENDIMENTO'),
	 (5,2,false,now(),now(),'AGUARDANDO'),
	 (5,2,true,now(),now(),'EM_ATENDIMENTO'),
	 (9,NULL,true,now(),now(),'ENCERRADO'),
	 (9,NULL,true,now(),now(),'ENCERRADO'),
	 (9,NULL,true,now(),now(),'ENCERRADO'),
	 (9,NULL,true,now(),now(),'ENCERRADO'),
	 (9,NULL,true,now(),now(),'EM_PAGAMENTO');

INSERT INTO pedidos (atendimento_id,cardapio_id,quantidade,ativo,created,updated) VALUES
	 (1,2,1,true,now(),now()),
	 (1,9,1,true,now(),now()),
	 (2,6,7,true,now(),now()),
	 (3,3,1,true,now(),now()),
	 (1,8,3,true,now(),now()),
	 (1,6,7,true,now(),now()),
	 (2,4,3,true,now(),now()),
	 (3,1,3,true,now(),now()),
	 (1,6,10,true,now(),now()),
	 (2,1,2,true,now(),now()),
	 (2,3,1,true,now(),now());*/
