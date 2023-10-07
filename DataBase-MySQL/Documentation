# Documentación de la Base de Datos - Cosecha Fácil

Esta documentación describe la estructura de la base de datos utilizada en el proyecto Cosecha Fácil para gestionar los precios de productos agrícolas.

## Estructura de la Base de Datos

La base de datos consta de una sola tabla principal: `precios_mayoristas`. Esta tabla almacena información sobre los precios de los productos agrícolas.

### Tabla: `precios_mayoristas`

- `id` (int, clave primaria): Identificador único para cada registro.
- `fecha_de_plaza` (date): Fecha en la que se registraron los precios.
- `producto` (varchar(255)): Nombre del producto agrícola.
- `unidad_comercializacion` (varchar(255)): Unidad de comercialización del producto.
- `minimo` (decimal(10,2)): Precio mínimo del producto.
- `maximo` (decimal(10,2)): Precio máximo del producto.
- `moda` (decimal(10,2)): Precio modal del producto.
- `promedio` (decimal(10,2)): Precio promedio del producto.

## Relaciones

En esta base de datos, no se definen relaciones entre tablas, ya que solo se almacena información de precios de productos agrícolas en una sola tabla.

## Uso de la Base de Datos

La base de datos `Cosecha Fácil` se utiliza para almacenar y gestionar los precios de los productos agrícolas. Los desarrolladores pueden acceder a esta base de datos para realizar consultas y análisis de precios.

## Ejemplos de Consultas SQL

A continuación, se muestran ejemplos de consultas SQL que se pueden realizar en la base de datos `Cosecha Fácil`:

### Consulta de Precios Promedio por Producto:

```sql
SELECT producto, AVG(promedio) AS precio_promedio
FROM precios_mayoristas
GROUP BY producto;
