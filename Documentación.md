# Documentación de la API Cosecha Fácil

## Introducción
La API Cosecha Fácil proporciona acceso a datos actualizados sobre los precios mayoristas de productos agrícolas en Costa Rica. Esta documentación está diseñada para ayudarte a comprender cómo utilizar la API y aprovechar al máximo su funcionalidad.

## Requisitos
Para utilizar la API de Cosecha Fácil, necesitarás lo siguiente:

- Acceso a Internet.
- Una cuenta de usuario y una clave de acceso proporcionadas por éste usuario.
- Conocimiento básico de las solicitudes HTTP y cómo procesar las respuestas JSON.

## Autenticación
Para acceder a la API, debes autenticarte enviando tu clave de acceso en la cabecera de autorización de la solicitud HTTP.

```http
GET /api/vegetales
Authorization: Bearer tu_clave_de_acceso
```

## Recursos Disponibles
La API ofrece varios recursos para acceder a los datos de precios de productos agrícolas:

1. **Obtener todos los vegetales**

   - **URL**: `/api/vegetales`
   - **Método HTTP**: GET
   - **Descripción**: Obtiene una lista de todos los productos agrícolas con información de precios.
   - **Parámetros de consulta**: Ninguno
   - **Ejemplo de respuesta**:
     ```json
     [
       {
         "id": 1,
         "fecha": "2023-10-01",
         "nombre": "Tomate",
         "unidad": "Kilogramo",
         "precioMinimo": 0.5,
         "precioMaximo": 0.8,
         "precioPromedio": 0.65,
         "precioVenta": 0.7
       },
       {
         "id": 2,
         "fecha": "2023-10-01",
         "nombre": "Papa",
         "unidad": "Kilogramo",
         "precioMinimo": 0.4,
         "precioMaximo": 0.6,
         "precioPromedio": 0.55,
         "precioVenta": 0.58
       }
     ]
     ```

2. **Obtener un vegetal por ID**

   - **URL**: `/api/vegetales/{id}`
   - **Método HTTP**: GET
   - **Descripción**: Obtiene los detalles de un producto agrícola específico por su ID.
   - **Parámetros de consulta**: `id` (ID del vegetal)
   - **Ejemplo de respuesta**:
     ```json
     {
       "id": 1,
       "fecha": "2023-10-01",
       "nombre": "Tomate",
       "unidad": "Kilogramo",
       "precioMinimo": 0.5,
       "precioMaximo": 0.8,
       "precioPromedio": 0.65,
       "precioVenta": 0.7
     }
     ```

3. **Agregar un nuevo vegetal**

   - **URL**: `/api/vegetales`
   - **Método HTTP**: POST
   - **Descripción**: Agrega un nuevo producto agrícola a la base de datos.
   - **Cuerpo de la solicitud**: Datos del vegetal en formato JSON.
   - **Ejemplo de solicitud**:
     ```json
     {
       "fecha": "2023-10-01",
       "nombre": "Zanahoria",
       "unidad": "Kilogramo",
       "precioMinimo": 0.4,
       "precioMaximo": 0.6,
       "precioPromedio": 0.55,
       "precioVenta": 0.58
     }
     ```
   - **Ejemplo de respuesta**:
     ```json
     {
       "id": 3,
       "fecha": "2023-10-01",
       "nombre": "Zanahoria",
       "unidad": "Kilogramo",
       "precioMinimo": 0.4,
       "precioMaximo": 0.6,
       "precioPromedio": 0.55,
       "precioVenta": 0.58
     }
     ```

4. **Actualizar un vegetal por ID**

   - **URL**: `/api/vegetales/{id}`
   - **Método HTTP**: PUT
   - **Descripción**: Actualiza los datos de un producto agrícola específico por su ID.
   - **Parámetros de consulta**: `id` (ID del vegetal)
   - **Cuerpo de la solicitud**: Datos actualizados del vegetal en formato JSON.
   - **Ejemplo de solicitud**:
     ```json
     {
       "fecha": "2023-10-02",
       "nombre": "Zanahoria",
       "unidad": "Kilogramo",
       "precioMinimo": 0.45,
       "precioMaximo": 0.7,
       "precioPromedio": 0.6,
       "precioVenta": 0.65
     }
     ```
   - **Ejemplo de respuesta**:
     ```json
     {
       "id": 3,
       "fecha": "2023-10-02",
       "nombre": "Zanahoria",
       "unidad": "Kilogramo",
       "precioMinimo": 0.45,
       "precioMaximo": 0.7,
       "precioPromedio": 0.6,
       "precioVenta": 0.65
     }
     ```

5. **Eliminar un vegetal por ID**

   - **URL**: `/api/vegetales/{id}`
   - **Método HTTP**: DELETE
   - **Descripción**: Elimina un producto agrícola específico por su ID.
   - **Parámetros de consulta**: `id` (ID del vegetal)
   - **Ejemplo de respuesta**: 204 No Content

## Errores y Manejo de Excepciones
La API proporciona códigos de estado

 HTTP y mensajes de error informativos para ayudarte a identificar y solucionar problemas. Si experimentas algún problema, revisa la respuesta de la API para obtener detalles sobre el error.

## Preguntas Frecuentes
**P: ¿Cómo obtengo una clave de acceso para la API?**
R: Para obtener una clave de acceso, ponte en contacto con el equipo de soporte de Cosecha Fácil.

**P: ¿Cuál es la frecuencia de actualización de los datos de precios?**
R: Los datos se actualizan diariamente.

## Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para obtener más detalles.
