INSERT INTO categorias(id,activo,nombre) values (1,1,'Sandbox'),(2,'','Aventura'),(3,0,'Terror'),(4,1,'Deporte'),(5,1,'Estrategia'),(6,1,'Carreras');

INSERT INTO estudios(id,activo,nombre) values (1,1,'Estudio 1'),(2,'','Estudio 2'),(3,0,'Estudio 3'),(4,1,'Estudio 4'),(5,1,'Estudio 5');

INSERT INTO videojuegos(id,activo,descripcion,fecha_lanzamiento,imagen,precio,stock,titulo,fk_categoria,fk_estudio) values (1,1,'Juego de aventuras','2021-08-28 03:00:00','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUSVhaqKzL_moyAI3ShTdfLBpEopCmUP_pwA&usqp=CAU',155,15,'Mario Bros',1,1);

INSERT INTO videojuegos(id,activo,descripcion,fecha_lanzamiento,imagen,precio,stock,titulo,fk_categoria,fk_estudio) values (2,1,'Juego de carreras','2021-07-26 21:49:36','http://3.bp.blogspot.com/-oMxRi825yeg/U1bJ94BMcgI/AAAAAAAAAV4/eXiZOS7NVEA/s1600/1NFSUnderground-Logo.jpg',250,0,'Need for speed underground',1,1);

INSERT INTO videojuegos(id,activo,descripcion,fecha_lanzamiento,imagen,precio,stock,titulo,fk_categoria,fk_estudio) values (3,1,'Juego arcade','2021-09-06 21:50:17','https://e.rpp-noticias.io/normal/2016/10/14/035103_266475.png',375,20,'Tetris',1,1);