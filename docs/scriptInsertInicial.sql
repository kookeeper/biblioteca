insert into grupo_usuario (id_grupo_usuario, descricao_grupo_usuario) values (1, 'Administrativo');
insert into usuario (login, senha, nome_usuario, id_grupo_usuario) values ('login', 'senha', 'Administrador', 1);

﻿insert into permissao (funcionalidade) values ('cadastrarUsuario');
insert into permissao (funcionalidade) values ('cadastrarGrupoUsuario');
insert into permissao (funcionalidade) values ('cadastrarTipoMaterialBibliografico');
insert into permissao (funcionalidade) values ('cadastrarMaterialBibliografico');
insert into permissao (funcionalidade) values ('reservarMaterialBibliografico');
insert into permissao (funcionalidade) values ('bloquearMaterialBibliografico');
insert into permissao (funcionalidade) values ('emprestarMaterialBibliografico');
insert into permissao (funcionalidade) values ('soliticarReserva');
