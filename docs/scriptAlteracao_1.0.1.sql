ALTER TABLE `usuario` ADD telefone varchar(30) null;
ALTER TABLE `usuario` ADD endereco varchar(100) null;
ALTER TABLE `usuario` ADD nome_usuario_chines varchar(100) null;

ALTER TABLE `material_bibliografico` ADD codigo_material_bibliografico varchar(100) not null;
ALTER TABLE `material_bibliografico` ADD id_local_material_bibliografico int(11) NOT NULL;

CREATE TABLE `local_material_bibliografico` (
  `id_local_material_bibliografico` int(11) NOT NULL auto_increment,
  `descricao_local_material_bibliografico` varchar(100) NOT NULL,
  PRIMARY KEY  (`id_local_material_bibliografico`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `material_bibliografico` ADD CONSTRAINT `fk_material_local` FOREIGN KEY (`id_local_material_bibliografico`) REFERENCES `local_material_bibliografico` (`id_local_material_bibliografico`) ON DELETE NO ACTION ON UPDATE NO ACTION;

