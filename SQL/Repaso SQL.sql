DROP DATABASE IF EXISTS tienda_db;
CREATE DATABASE tienda_db;
USE tienda_db;

CREATE TABLE cliente(
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(255),
edad INT,
ciudad VARCHAR(255)
);

CREATE TABLE pedidos(
id_cliente INT,
id INT AUTO_INCREMENT PRIMARY KEY,
fecha VARCHAR(255),
FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

CREATE TABLE productos(
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(255),
precio FLOAT(10,2),
stock INT
);

CREATE TABLE pedidos_productos(
id_pedidos INT,
id_productos INT,
cantidad INT,
PRIMARY KEY (id_pedidos, id_productos),
FOREIGN KEY (id_pedidos) REFERENCES pedidos(id),
FOREIGN KEY (id_productos) REFERENCES productos(id)
);

-- Manipulación de datos (DML - insert, update, delete, select)
INSERT INTO cliente (nombre, ciudad, edad) VALUES 
('Ana Lopez', 'Madrid', 30),
('Carlos Ruiz', 'Sevilla', 45),
('Lucia', 'Cadiz', 25);

INSERT INTO productos (nombre, precio, stock) VALUES
('Tablet', 250, 15),
('Raton', 10, 200),
('Alfombrilla', 5, 307);

INSERT INTO pedidos (fecha, id_cliente) VALUES
('2025-01-10', 1),
('2025-04-17', 2),
('2025-04-18', 2),
('2025-07-03', 3);

INSERT INTO pedidos_productos (id_pedidos, id_productos, cantidad) VALUES
(1,1,1), 
(1,2,1),
(2,3,1),
(1,3,1);

-- CAMBIAR EL STOCK DE LAS TABLETS
-- UPDATE productos SET stock = 10 WHERE id_productos = 1;

-- MOSTRAR NOMBRE Y CIUDAD DE LOS CLIENTES MAYORES DE 26 AÑOS
SELECT * FROM cliente WHERE edad > 26;

-- CLIENTES CUYO APELLIDO EMPIEZA POR L
SELECT * FROM cliente WHERE nombre LIKE '%L';

-- CLIENTES ENTRE 25 Y 45 Y ORDENARLOS ALFABETICAMENTE
SELECT * FROM cliente WHERE edad BETWEEN 25 AND 45 ORDER BY nombre;

-- PRODUCTOS CON PRECIO MENOR A 50€ CON PRECIO DESCENDIENTE
SELECT * FROM productos WHERE precio < 50 ORDER BY precio DESC;

-- MOSTRAR DATOS DE LOS PEDIDOS CON EL NOMBRE Y CIUDAD DEL CLIENTE
SELECT pedido.*, cliente.nombre, cliente.ciudad FROM pedidos
INNER JOIN cliente ON cliente.id = producto.id_cliente;

-- MOSTRAR TOTAL GASTADO POR CADA CLIENTE
SELECT cliente.*, productos.* FROM cliente 
INNER JOIN pedidos ON productos.id_cliente = cliente.id_cliente
INNER JOIN pedidos_productos ON pedidos_productos.id_pedido = productos.id_pedido
INNER JOIN productos ON productos.id_producto = pedidos_productos.id_producto;