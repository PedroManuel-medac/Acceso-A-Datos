DROP DATABASE IF EXISTS tienda_db;
CREATE DATABASE tienda_db;
USE tienda_db;

-- Crear tablas
CREATE TABLE cliente (
id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    edad INT,
    ciudad VARCHAR(50)
);

CREATE TABLE pedido (
id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
);

CREATE TABLE producto (
id_producto INT AUTO_INCREMENT PRIMARY KEY,
    precio DECIMAL(10,2),
    nombre VARCHAR(50),
    stock INT
);

CREATE TABLE pedido_producto (
id_pedido INT,
    id_producto INT,
    cantidad INT,
    PRIMARY KEY(id_pedido, id_producto),
    FOREIGN KEY(id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY(id_producto) REFERENCES producto(id_producto)
);

-- Insertar valores
INSERT INTO cliente (nombre,ciudad,edad)
VALUES
('Ana López', 'Madrid', 30),
('Carlos Ruiz', 'Sevilla', 45),
('Lucia Gómez', 'Granada', 22);

INSERT INTO producto (nombre,precio,stock)
VALUES
('Portatil',800.00, 2),
('Ratón',10,50),
('Teclado',20,50);
   
INSERT INTO pedido (fecha, id_cliente)
VALUES
('2024-01-10',1),
('2024-01-11',2),
('2024-01-12',3),
('2024-05-05',1);
   
INSERT INTO pedido_producto (id_pedido, id_producto, cantidad)
VALUES
(1,1,1), -- Ana compra 1 portatil
(1,2,2), -- Ana compra 2 ratones
(2,3,3), -- Carlos compra 2 teclados
(3,1,1), -- Lucia compra 1 portatil
(4,3,5); -- Ana compra 5 tecldos


-- Clientes
SELECT * FROM cliente;
-- Productos
SELECT * FROM producto;
-- Pedido producto
SELECT * FROM pedido_producto;

-- Solicita el ID del pedido e imprime: Nombre y ciudad del cliente. Fecha del pedido. Lista de productos pedidos con sus nombres, 
-- cantidades y precios. Total del pedido.

SELECT cliente.nombre, cliente.ciudad, pedido.fecha, producto.nombre, pedido_producto.cantidad, producto.precio, (pedido_producto.cantidad * producto.precio) AS subtotal FROM pedido
JOIN cliente ON pedido.id_cliente = cliente.id_cliente
JOIN pedido_producto ON pedido.id_pedido = pedido_producto.id_pedido
JOIN producto ON pedido_producto.id_producto = producto.id_producto
WHERE pedido.id_pedido = 1;

--

SELECT cliente.nombre, cliente.ciudad, pedido.fecha, SUM(pedido_producto.cantidad * producto.precio) AS total_pedido FROM pedido
JOIN cliente ON pedido.id_cliente = cliente.id_cliente
JOIN pedido_producto ON pedido.id_pedido = pedido_producto.id_pedido
JOIN producto ON pedido_producto.id_producto = producto.id_producto
WHERE pedido.id_pedido = 1
GROUP BY cliente.nombre, cliente.ciudad, pedido.fecha;


SELECT * FROM pedido;

