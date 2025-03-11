/*Se crea la base de datos */
drop schema if exists proyecto;
drop user if exists usuario_prueba;
drop user if exists usuario_reportes;
CREATE SCHEMA proyecto ;

/*Se crea un usuario para la base de datos llamado "usuario_prueba" y tiene la contraseña "Usuario_Clave."*/
create user 'usuario_prueba'@'%' identified by 'Usuar1o_Clave.';
create user 'usuario_reportes'@'%' identified by 'Usuar1o_Reportes.';

/*Se asignan los prvilegios sobr ela base de datos Proyecto al usuario creado */
grant all privileges on proyecto.* to 'usuario_prueba'@'%';
grant select on proyecto.* to 'usuario_reportes'@'%';
flush privileges;

use proyecto;

;

create table producto (
  id_producto INT NOT NULL AUTO_INCREMENT,
  id_categoria INT NOT NULL,
  nombre VARCHAR(30) NOT NULL, 
  descripcion VARCHAR(30) NOT NULL,  
  detalle VARCHAR(1600) NOT NULL, 
  precio double,
  stock int,  
  imagen varchar(1024),
  PRIMARY KEY (id_producto),
  foreign key fk_producto_caregoria (id_categoria) references categoria(id_categoria)  
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


INSERT INTO producto (id_producto,id_categoria,nombre,descripcion,precio,existencias,imagen) VALUES
(1,1,'Camisa roja','Camisa de color rojo ',23000,5,'https://tse3.mm.bing.net/th?id=OIP.c4KV6r_Nwg1BEHt_LOGkJQHaHS&pid=Api&P=0&h=180'),
(2,1,'Camisa amarilla','Camisa  de color amarillo ',20000,10,'https://tse1.mm.bing.net/th?id=OIP.ru8rn-ucapUImIQ0dd6AgAHaLH&pid=Api&P=0&h=180'),
(3,1,'Pantalon','Pantalon azul',15000,8,'https://tse2.mm.bing.net/th?id=OIP.WAsEG_BZruX6dOJ8XErsIQHaLH&pid=Api&P=0&h=180'),
