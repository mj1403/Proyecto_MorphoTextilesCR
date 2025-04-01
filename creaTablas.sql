CREATE SCHEMA ProyectoMorpho;

--Creacion del usuario principal
CREATE USER 'main_user'@'%' IDENTIFIED BY 'main_password';
GRANT ALL PRIVILEGES ON ProyectoMorpho.* TO 'main_user'@'%';
FLUSH PRIVILEGES;

USE ProyectoMorpho;

--Creacion de tablas
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    direccion VARCHAR (500),
    telefono VARCHAR(20),
    fecha_registro DATE,
    rol ENUM('cliente', 'admin') DEFAULT 'cliente') 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8mb4;

CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion VARCHAR(2000),
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    imagen VARCHAR(1024),
	categoria VARCHAR(50) NOT NULL,
    fecha_creacion DATE)
ENGINE=InnoDB 
DEFAULT CHARSET=utf8mb4;
    
CREATE TABLE pedidos (
	id_pedido INT AUTO_INCREMENT PRIMARY KEY,
	id_usuario INT NOT NULL,
    fecha_pedido DATE,
	estado VARCHAR(20) DEFAULT 'pendiente',
	total DECIMAL(10,2) NOT NULL,
	FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8mb4;

CREATE TABLE detalle_pedido (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE CASCADE) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8mb4;

CREATE TABLE carrito (
    id_carrito INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL DEFAULT 1,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE CASCADE) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8mb4;

CREATE TABLE comentarios (
    id_comentario INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_producto INT NOT NULL,
    comentario VARCHAR (1000) NOT NULL,
    fecha DATE,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE CASCADE) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8mb4;

--Insercion de los datos en las tablas
INSERT INTO productos (id_producto, nombre, descripcion, precio, stock, imagen, categoria, fecha_creacion) VALUES
(1, 'Camiseta Deportiva', 'Camiseta personalizable de tela deportiva', 7000, 10, 'https://cicadex.com/wp-content/uploads/2022/10/350-344_47-Camisa-COSTA-RICA-copia.jpg', 'Camisas', '2014-01-20'),
(2, 'Sudadera Personalizable', 'Sudadera con opcion de estampado personalizado', 15000, 20, 'https://media2.positivos.com/218141-large_default/sudadera-personalizada-blanca.jpg', 'Sudaderas', '2020-06-01'),
(3, 'Gorra PV', 'Gorras con estampado de Pura Vida', 4000, 19, 'https://landingwebimg.s3.amazonaws.com/uploads/landing_page_product_variant/image/684922/image-upload1725233900.webp', 'Gorras', '2019-09-13'),
(4, 'Taza Perzonalizada', 'Taza de cafe perzonalizable', 5000, 50, 'https://www.crealo.es/1106643-medium_default/taza-ceramica-blanca-personalizada.jpg', 'Tazas', 2015-02-14),
(5, 'Pines Perzonalizados', 'Pin perzonalisable con imagen a eleccion', 500, 100, 'https://i.etsystatic.com/5967322/r/il/c5aba0/609298391/il_570xN.609298391_ofo8.jpg', 2010-02-20);