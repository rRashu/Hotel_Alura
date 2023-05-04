create database Hotel_Alura;

use Hotel_Alura;

create table huespedes(
	id integer auto_increment,
    nombre varchar (200) not null,
    apellido varchar (200) not null,
    fecha_nacimiento date not null,
    nacionalidad varchar (200) not null,
    telefono varchar (200) not null,
    primary key (id)
);

create table reservas(
	id integer auto_increment,
    fecha_entrada date not null,
    fecha_salida date not null,
    valor float not null,
    forma_pago varchar (200) not null,
    id_huesped integer,
    primary key (id)
);

ALTER TABLE reservas ADD FOREIGN KEY (id_huesped) REFERENCES huespedes(id);

INSERT INTO `hotel_alura`.`huespedes`(`id`, `nombre`, `apellido`, `fecha_nacimiento`, `nacionalidad`, `telefono`) 
	VALUES ('1', 'robin', 'rezabala', '1994-07-11', 'gret', '099999');

