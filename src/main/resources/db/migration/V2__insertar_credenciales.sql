INSERT INTO perfil (nombre) VALUES
('ADMIN'),
('DOCENTE'),
('ALUMNO');

INSERT INTO usuario(nombre, email, password, perfil_id) VALUES
('Diego Zapata', 'diego@correo.cl', '$2a$10$yOUeIr.Vx7VvU73fBUikSuiJFcBR.wXjHJiGlfjXEwY97KxE0bQAi', 1),
('Kim Yoohyeon', 'yoohyeon@correo.cl', '$2a$10$QOrxKnWIWarpqGlKKUZFt.FesFA8RXUuwPmLjl9qfN.HoGsR2hoz6', 2),
('Maricarmen Gonzalez', 'maricarmen@correo.cl', '$2a$10$ZS8ag5/74QEDoWE0bORBj.Af0T2mS3TySGDy9JYILBEct3HnR3D4i', 3);