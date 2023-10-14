# Documentación del Descargador de PDF de PIMA y Actualizador de Base de Datos

## Introducción

El Descargador de PDF de PIMA y Actualizador de Base de Datos es una herramienta de automatización diseñada para facilitar la gestión de archivos PDF adjuntos en correos electrónicos de Gmail y actualizar una base de datos MySQL con la información extraída de estos archivos. Este script es útil en situaciones en las que se reciben PDFs relacionados con información de precios al por mayor de productos agrícolas y se necesita mantener una base de datos actualizada con estos datos.

## Características

### Monitoreo de Correo Electrónico

El script monitorea una cuenta de Gmail en busca de correos electrónicos etiquetados como "PIMA". Los correos electrónicos etiquetados como "PIMA" se consideran relevantes y se procesarán para extraer datos de los archivos PDF adjuntos.

### Descarga de PDF

Cuando se encuentra un correo electrónico con un archivo PDF adjunto, el script descarga automáticamente el PDF a un directorio local especificado.

### Extracción de Datos de PDF

El script utiliza la biblioteca "pdfplumber" para extraer datos de las tablas contenidas en los archivos PDF. Se enfoca en la extracción de información relacionada con precios de productos agrícolas.

### Transformación de Datos

La información extraída se transforma y se adapta a una estructura específica requerida para la actualización de la base de datos MySQL. Esto asegura que los datos sean coherentes y estén listos para su inserción en la base de datos.

### Actualización de la Base de Datos MySQL

El script se conecta a una base de datos MySQL a través de un túnel SSH y actualiza la base de datos con los datos extraídos de los PDF. Garantiza que los datos sean almacenados de manera eficiente y segura.

### Programación de Tareas

El script utiliza la biblioteca "schedule" para programar tareas de forma automática. Puedes configurar el intervalo de tiempo entre ejecuciones, por ejemplo, cada 12 horas, para que el script revise los correos electrónicos y actualice la base de datos de manera periódica.

## Requisitos Previos

Antes de utilizar este script, asegúrate de tener lo siguiente:

- Python instalado en tu sistema.

- Las bibliotecas de Python necesarias instaladas. Puedes instalarlas utilizando `pip`:

- Acceso a una cuenta de Gmail desde donde recibirás correos electrónicos relacionados con los archivos PDF que deseas procesar.

- Acceso a una base de datos MySQL donde deseas almacenar los datos extraídos.

## Configuración

Antes de ejecutar el script, debes realizar algunas configuraciones importantes:

- Establece las credenciales de tu cuenta de Gmail en el script. Debes proporcionar la dirección de correo electrónico y la contraseña de la cuenta que se utilizará para acceder a la bandeja de entrada de Gmail. Se recomienda utilizar una cuenta de servicio para mantener la seguridad de tus credenciales.

- Configura los detalles de la conexión a la base de datos MySQL. Esto incluye el nombre de host, puerto, nombre de usuario, contraseña y nombre de la base de datos.

- Especifica las rutas de los directorios para los archivos PDF descargados y el archivo de registro que realiza un seguimiento de los archivos ya descargados.

## Uso

1. Ejecución del Script:
 - Ejecuta el script utilizando un intérprete de Python. Puedes hacerlo ejecutando el siguiente comando en tu terminal:
   ```
   python nombre_del_script.py
   ```
 - El script comenzará a monitorear tu bandeja de entrada de Gmail y a procesar cualquier correo electrónico etiquetado como "PIMA".

2. Descarga de PDF:
 - Cuando se encuentra un nuevo correo electrónico con un archivo PDF adjunto, el script lo descargará automáticamente al directorio especificado.

3. Extracción de Datos y Actualización de la Base de Datos:
 - El script extraerá datos de los PDF descargados y los transformará para que se ajusten a la estructura requerida por la base de datos MySQL.
 - Luego, se conectará a la base de datos MySQL a través de un túnel SSH y actualizará la base de datos con los datos extraídos.

4. Programación de Ejecuciones:
 - Puedes programar ejecuciones automáticas del script según tus necesidades. Para hacerlo, ajusta el intervalo de tiempo en la configuración del script (por ejemplo, cada 12 horas).

## Resolución de Problemas

Si encuentras algún problema con el script, sigue estos pasos para resolverlos:

- Verifica tu configuración y asegúrate de que todas las bibliotecas de Python necesarias estén instaladas.

- Asegúrate de que tu cuenta de Gmail y tu base de datos MySQL sean accesibles y que las credenciales proporcionadas sean correctas.

## Licencia

Este script se proporciona bajo una licencia de código abierto, lo que significa que puedes modificarlo y distribuirlo según tus necesidades. Te alentamos a utilizarlo de manera efectiva y a adaptarlo para satisfacer tus requisitos específicos.
