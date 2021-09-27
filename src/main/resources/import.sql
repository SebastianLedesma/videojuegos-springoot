INSERT INTO categorias(id,activo,nombre) values (1,1,'Sandbox'),(2,'','Aventura'),(3,0,'Terror'),(4,1,'Deporte'),(5,1,'Estrategia');

INSERT INTO estudios(id,activo,nombre) values (1,1,'Estudio 1'),(2,'','Estudio 2'),(3,0,'Estudio 3'),(4,1,'Estudio 4'),(5,1,'Estudio 5');

INSERT INTO videojuegos(id,activo,descripcion,fecha_lanzamiento,imagen,precio,stock,titulo,fk_categoria,fk_estudio) values (1,1,'Juego de Mario bros','2021-08-28 03:00:00','mariobros.jpg',155,15,'Mario Bros',1,1);

INSERT INTO videojuegos(id,activo,descripcion,fecha_lanzamiento,imagen,precio,stock,titulo,fk_categoria,fk_estudio) values (2,1,'Juego de carreras','2021-07-26 21:49:36','nfs.jpg',250,0,'Need for Speed Underground ',1,1);

INSERT INTO videojuegos(id,activo,descripcion,fecha_lanzamiento,imagen,precio,stock,titulo,fk_categoria,fk_estudio) values (3,1,'Juego arcade','2021-09-06 21:50:17','pacman.jpg',375,20,'Pacman',1,1);