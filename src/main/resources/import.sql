-- Usuarios
INSERT INTO usuarios (id, name, email, password, roles, telefono, estado)
VALUES (1, 'admin', 'admin@cinetickets.com', 'admin123', 'ADMIN', '3001234567', 'ACTIVO', CURRENT_DATE),
       (2, 'sofia', 'sofia@email.com', 'usuario123', 'USER', '3011112222', 'ACTIVO', CURRENT_DATE);

-- Películas
INSERT INTO peliculas (pelicula_id, titulo, descripcion, categoria, duracion, estado, imagen_url)
VALUES (1, 'Avengers: Endgame', 'Los Vengadores enfrentan a Thanos.', 'Acción', '3h 1m', 'ACTIVA', 'https://img.com/endgame.jpg', 'https://youtube.com/trailer1'),
       (2, 'Intensamente 2', 'Regresan las emociones de Riley.', 'Animación', '1h 40m', 'ACTIVA', 'https://img.com/intensamente.jpg', 'https://youtube.com/trailer2');

-- Funciones
INSERT INTO funcion (id, pelicula_pelicula_id, fecha, hora, sala, disponibilidad, precio_entrada)
VALUES (1, 1, '2025-04-10', '18:00:00', 'Sala 1', 50, 15000.00),
       (2, 2, '2025-04-11', '20:00:00', 'Sala 2', 40, 12000.00);

-- Compras
INSERT INTO compra (compra_id, usuario_id, pelicula_pelicula_id, precio_total, cantidad_tickets, fecha_compra)
VALUES (1, 2, 1, 30000.00, 2, '2025-04-01');

-- DetalleCompra
INSERT INTO detallecompra (id, compra_compra_id, funcion_id)
VALUES (1, 1, 1),
       (2, 1, 1);

-- Pago
INSERT INTO pago (id, compra_compra_id, metodopago, estadopago)
VALUES (1, 1, 'Tarjeta Crédito', 'COMPLETADO');
