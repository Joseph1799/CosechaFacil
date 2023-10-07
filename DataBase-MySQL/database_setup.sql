-- Crea la tabla 'precios_mayoristas' si no existe
CREATE TABLE IF NOT EXISTS `precios_mayoristas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_de_plaza` date NOT NULL,
  `producto` varchar(255) NOT NULL,
  `unidad_comercializacion` varchar(255) NOT NULL,
  `minimo` decimal(10,2) NOT NULL,
  `maximo` decimal(10,2) NOT NULL,
  `moda` decimal(10,2) NOT NULL,
  `promedio` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
