# Descargador de PDF de PIMA y Actualizador de Base de Datos

Este script de Python está diseñado para automatizar el proceso de descarga de archivos PDF adjuntos desde correos electrónicos de Gmail, la extracción de datos de los PDF y la actualización de una base de datos MySQL con la información extraída. Es particularmente útil para procesar PDF relacionados con información de precios al por mayor de productos agrícolas.

## Características

- **Monitoreo de Correo Electrónico:** El script monitorea una bandeja de entrada de Gmail en busca de correos electrónicos etiquetados como "PIMA" para identificar correos relevantes con archivos PDF adjuntos.

- **Descarga de PDF:** Cuando se encuentra un nuevo correo electrónico con un archivo PDF adjunto, el script descarga automáticamente el PDF a un directorio especificado.

- **Extracción de Datos de PDF:** Extrae datos de los PDF, apuntando específicamente a tablas dentro de los documentos.

- **Transformación de Datos:** El script procesa y transforma los datos para que se ajusten a una estructura específica requerida para una base de datos MySQL.

- **Actualización de la Base de Datos MySQL:** Se conecta a una base de datos MySQL a través de un túnel SSH y actualiza la base de datos con la información extraída, garantizando la integridad de los datos.

- **Programación:** El script programa la verificación de correos electrónicos y el procesamiento de datos para que se ejecute en intervalos específicos (por ejemplo, cada 12 horas) utilizando la biblioteca `schedule`.

## Requisitos Previos

Antes de usar este script, asegúrate de tener lo siguiente:

- Python instalado en tu sistema.

- Las bibliotecas de Python necesarias instaladas. Puedes instalarlas utilizando `pip`:

- Acceso a una cuenta de Gmail donde se reciben los correos electrónicos relevantes.

- Una base de datos MySQL donde deseas almacenar los datos extraídos.

## Configuración

Antes de ejecutar el script, debes configurar lo siguiente:

- Establece las credenciales de tu cuenta de Gmail en el script para permitir el acceso a tu bandeja de entrada.

- Configura los detalles de conexión de la base de datos MySQL (host, puerto, nombre de usuario, contraseña y nombre de la base de datos) dentro del script.

- Establece las rutas de los directorios para los PDF descargados y el archivo de registro que realiza un seguimiento de los archivos descargados.

## Uso

1. Ejecuta el script utilizando un intérprete de Python. Comenzará a revisar tu bandeja de entrada de Gmail en busca de correos electrónicos etiquetados como "PIMA" y procesará cualquier archivo PDF adjunto nuevo.

2. El script descargará los nuevos PDF, extraerá datos y actualizará tu base de datos MySQL.

3. Las ejecuciones programadas (por ejemplo, cada 12 horas) continuarán revisando nuevos correos electrónicos y procesándolos.

## Resolución de Problemas

- Si encuentras algún problema con el script, verifica tu configuración y asegúrate de que todas las bibliotecas de Python necesarias estén instaladas.

- Asegúrate de que tu cuenta de Gmail y tu base de datos MySQL sean accesibles.

## Licencia

Este script se proporciona bajo una licencia de código abierto. Siéntete libre de modificarlo y distribuirlo según sea necesario.


