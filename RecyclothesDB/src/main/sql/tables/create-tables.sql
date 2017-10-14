-- drop database IF EXISTS web;

-- CREATE DATABASE  web ;

CREATE TABLE IF NOT EXISTS web.tb_acceso  (
   id_acceso  int(11) NOT NULL AUTO_INCREMENT,
   id_cliente  int(11) DEFAULT NULL,
   fecha_acceso_in  datetime NOT NULL,
   fecha_acceso_out  datetime DEFAULT NULL,
   status  int(11) DEFAULT NULL,
   PRIMARY KEY ( id_acceso )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS web.tb_cliente  (
   id_cliente  int(11) NOT NULL AUTO_INCREMENT,
   nombres  varchar(45) DEFAULT NULL,
   apellidos  varchar(45) DEFAULT NULL,
   password  varchar(45) DEFAULT NULL,
   correo  varchar(255) NOT NULL,
   token  varchar(255) DEFAULT NULL,
   fechaRegistroCreacion  datetime NOT NULL,
   fechaUltimaVisita  datetime DEFAULT NULL,
   telefono  varchar(45) DEFAULT NULL,
   PRIMARY KEY ( id_cliente )
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

CREATE TABLE  IF NOT EXISTS web.tb_detalle_reserva  (
   id_detalle_reserva  int(11) NOT NULL AUTO_INCREMENT,
   id_reserva  int(11) NOT NULL,
   id_producto  int(11) NOT NULL,
   PRIMARY KEY ( id_detalle_reserva )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  IF NOT EXISTS web.tb_foto_producto  (
   id_foto_producto  int(11) NOT NULL AUTO_INCREMENT,
   id_producto  int(11) DEFAULT  0 ,
   foto  mediumblob ,
   PRIMARY KEY ( id_foto_producto )
) ENGINE=InnoDB AUTO_INCREMENT=1292 DEFAULT CHARSET=utf8;

CREATE TABLE  IF NOT EXISTS web.tb_producto  (
   id_producto  int(11) NOT NULL AUTO_INCREMENT,
   descripcion  varchar(45) NOT NULL,
   estado_producto  int(11) NOT NULL COMMENT  '0 PENDIENTE / 1 DISPONIBLE / 2 PEDIDO / 3 RESERVADO / 4 ENTREGADO ',
   fec_ingreso  datetime DEFAULT NULL,
   catalogo_producto  int(11) DEFAULT NULL ,
   talla_producto  int(11) DEFAULT NULL ,
   precio_producto  int(11) DEFAULT NULL,
   id_foto_producto  int(11) DEFAULT NULL,
   PRIMARY KEY ( id_producto )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  IF NOT EXISTS web.tb_reserva  (
   id_reserva  int(11) NOT NULL AUTO_INCREMENT,
   id_cliente  int(11) NOT NULL,
   fec_reserva  datetime NOT NULL,
   comentarios  varchar(250) DEFAULT NULL,
   estado_reserva  int(11) NOT NULL COMMENT  '0 PENDIENTE POR PAGAR ',
   fec_entrega  datetime DEFAULT NULL,
   cod_reserva  varchar(45) DEFAULT NULL COMMENT  'codigo hash con largo 6 de acuerdo al toString del objecto' ,
   costo_reserva  int(11) DEFAULT NULL,
   total_a_pagar  int(11) NOT NULL,
   datosDeEntrega  varchar(250) DEFAULT NULL,
   hora_entrega  int(11) DEFAULT NULL,
   PRIMARY KEY ( id_reserva )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_parametro (
   nombre varchar(200) NOT NULL,
   valor varchar(250) NOT NULL,
   descripcion varchar(250) DEFAULT NULL,
   PRIMARY KEY ( nombre )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO web.tb_parametro
(nombre,
 valor,
 descripcion)
VALUES
   ('STATUS_LOGGIN_FOR_USUARIO' , 'TRUE', null);
INSERT INTO web.tb_parametro
(nombre,
 valor,
 descripcion)
VALUES
   ('TOTAL_FOTOS_POR_PAGINA' , '24', null);
INSERT INTO web.tb_parametro
(nombre,
 valor,
 descripcion)
VALUES
   ('TOTAL_MAXIMA_RESERVAS_PENDIENTES_POR_CLIENTE' , '3', null);

/*
INSERT INTO  IF NOT EXISTS web . tb_cliente
( nombres ,
  apellidos ,
  password ,
  correo ,
  token ,
  fechaRegistroCreacion ,
  fechaUltimaVisita ,
  telefono )
VALUES
   ('CESAR',
    'CASTILLO',
    'a',
    'genesiscastillo@hotmail.com',
    null,
    20/02/2017,
    20/02/2017,
    '+56995181762');*/