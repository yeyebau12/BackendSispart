
INSERT INTO database_sispart.role (nombre) VALUES ('ROLE_ADMINISTRADOR');
INSERT INTO database_sispart.role (nombre) VALUES ('ROLE_RECEPCIONISTA');
INSERT INTO database_sispart.role (nombre) VALUES ('ROLE_SERVICIOS');


INSERT INTO  database_sispart.tipos_de_documentos (nom_tipo_documento) VALUES ('REGISTRO CIVIL');
INSERT INTO  database_sispart.tipos_de_documentos (nom_tipo_documento) VALUES ('TARJETA DE IDENTIDAD');
INSERT INTO  database_sispart.tipos_de_documentos (nom_tipo_documento) VALUES ('CEDULA DE CIUDADANIA');


INSERT INTO database_sispart.tipos_de_sangre (nom_tipo_sangre) VALUES ('O+');
INSERT INTO database_sispart.tipos_de_sangre (nom_tipo_sangre) VALUES ('O-');
INSERT INTO database_sispart.tipos_de_sangre (nom_tipo_sangre) VALUES ('A+');
INSERT INTO database_sispart.tipos_de_sangre (nom_tipo_sangre) VALUES ('A-');

INSERT INTO database_sispart.sexo(nom_sexo) VALUES ('MASCULINO');
INSERT INTO database_sispart.sexo(nom_sexo) VALUES ('FEMENINO');


INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Afganistán');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Albania');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Alemania');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Andorra');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Angola');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('AntiguayBarbuda');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('ArabiaSaudita');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Argelia');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Argentina');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Armenia');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Aruba');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Australia');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Austria');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Azerbaiyán');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Bahamas');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Bangladé');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Barbados');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Baréin');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Bélgica');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Belice');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Benín');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Bielorrusia');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Birmania');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Bolivia');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('BosniayHerzegovina');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Botsuana');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Brasil');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Brunéi');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Bulgaria');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('BurkinaFaso');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Burundi');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Bután');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('CaboVerde');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Camboya');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Camerún');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Canadá');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Catar');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Chad');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Chile');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('China');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Chipre');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('CiudaddelVaticano');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Colombia');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Comoras');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('CoreadelNorte');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('CoreadelSur');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('CostadeMarfil');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('CostaRica');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Croacia');
INSERT INTO database_sispart.nacionalidad (nombre) VALUES('Cuba');



INSERT INTO database_sispart.empleados (apellido, arl, correo, direccion, edad, eps, fecha_nacimento, nom_contacto_emergencia, nombre, num_contacto_emergencia, num_documento, num_telefono, cod_sexo, tip_documento, tipo_sangre) VALUES ('Maurico', 'SURA', 'die@unbosque.edu.co', 'calle100', '24', 'Compensar', '1999-06-14', 'Juan Mauricio', 'Diego', '3145678399', '1283920034', '324537489', '1', '3', '3');
INSERT INTO database_sispart.empleados(apellido, arl, correo, direccion, edad, eps, fecha_nacimento, nom_contacto_emergencia, nombre, num_contacto_emergencia, num_documento,  num_telefono, cod_sexo, tip_documento, tipo_sangre) VALUES ('Ahumada', 'SEGUROS BOLIVAR', 'cahumada@unbosque.edu.co', 'calle45', '19', 'Compensar', '2001-06-20', 'Rosendo Torres', 'Camilo', '3245363748', '1003728399', '379293008', '1', '3', '2');

INSERT INTO database_sispart.usuario_empleado(confirm_contrasena, contrasena, create_user, enabled, num_documento, user_name, cod_tip_documento) VALUES ('$2a$10$9EoTRLBT246SzG/Gp4Fpfe1Posvm7k5H5Jl6KVVCaRedObqp9yhlq', '$2a$10$z6E7IBBb9zdRWM.9jm8a3.Hj4BOSPWVAMoMaaDa3K5y0Lf0zp2oky', NOW(), '1', '1003728399', 'cahumadag', '3');
INSERT INTO database_sispart.usuarios_empleados_roles (cod_usuario_empleados, cod_role_empleado) VALUES ('1', '1');

INSERT INTO database_sispart.huespedes (apellido, correo, estado_huesped, lugar_origen, nom_contacto_emergencia, nombre, num_celular, num_contacto_emergencia, num_documento, cod_nacionalidad, cod_tip_documento) VALUES ('Gomez', 'g.r@gmail.com', '1', 'Bogota', 'Yute', 'Rosa', '3453672384', '36272893945', '1023849458', '4', '3');

INSERT INTO database_sispart.habitacion (descrip_habitacion, estado_habitacion, max_personas_habitacion, nombre_habitacion, num_habitacion, piso_habitacion,precio_habitacion) VALUES ('wifi,baño,camaking', 'Disponible', '4', 'Matrimonial', '101', '1', '45000');

