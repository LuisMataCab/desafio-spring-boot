insert into estado_tarea (codigo_estado,desc_estado) values ('TBL','Tarea en backLog');
insert into estado_tarea (codigo_estado,desc_estado) values ('TPH','Tarea por hacer');
insert into estado_tarea (codigo_estado,desc_estado) values ('TEP','Tarea en proceso');
insert into estado_tarea (codigo_estado,desc_estado) values ('TPA','Tarea pausada');
insert into estado_tarea (codigo_estado,desc_estado) values ('TTA','Tarea terminada');
commit;
insert into usuario (username,password,nombre,apellido,rol) values ('lmatamalac','$2a$10$ZLFXQ/iDDtd.EnpFUawgBOsC12RfKnP73mi47QZec3Wv.rDU7.XjW','Luis','Matamala','USUARIO');
commit;