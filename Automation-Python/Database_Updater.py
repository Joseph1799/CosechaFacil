from simplegmail import Gmail
from simplegmail.query import construct_query
import os
import schedule
import time
import subprocess
import datetime
import glob
from datetime import datetime
import pdfplumber
import re
import pymysql.cursors
from sshtunnel import SSHTunnelForwarder


# Ruta al archivo que contiene/contendrá los nombres de los archivos PDF descargados.
downloaded_files_log = 'C:\\Users\\user\\Documents\\CosechaFacilCR\\PDFs_Registro\\downloaded_files.txt'
# Ruta del archivo PDF
pdf_dir = "C:/Users/user/Documents/CosechaFacilCR/PDFs_Registro"

def load_downloaded_files():
    if os.path.exists(downloaded_files_log):
        with open(downloaded_files_log, 'r') as file:
            return set(file.read().splitlines())
    return set()

def save_downloaded_file(filename):
    with open(downloaded_files_log, 'a') as file:
        file.write(filename + '\n')

def update_Database():
    # Busca el archivo PDF más reciente en el directorio
    list_of_pdfs = glob.glob(os.path.join(pdf_dir, "*.pdf"))
    latest_pdf = max(list_of_pdfs, key=os.path.getctime)

    # Crear una lista para almacenar todos los datos de las páginas
    all_data = []

    # Crear un objeto pdfplumber para cada página y extraer los datos
    with pdfplumber.open(latest_pdf) as pdf:

        # Extraer el texto de la primera página para obtener la fecha
        first_page = pdf.pages[0]
        first_page_text = first_page.extract_text()

        # Utilizar expresión regular para extraer la fecha del inicio del PDF
        date_match = re.search(r'(\d{1,2}/\d{1,2}/\d{4})', first_page_text)
        if date_match:
            pdf_date = date_match.group(1)
            datetime_obj = datetime.strptime(pdf_date, '%d/%m/%Y')
            mysql_date = datetime_obj.strftime('%Y-%m-%d')
        else:
            pdf_date = ""

        for page in pdf.pages:
            # Extraer las celdas de la tabla de la página
            table = page.extract_table()

            # Inicializar una lista para almacenar los datos de esta página
            data = []

            # Iterar sobre las filas de la tabla
            for row in table[0:]:
                # Manejar el caso en el que hay más o menos valores de los esperados
                if len(row) == 6:
                    producto, unidad, minimo, maximo, moda, promedio = row
                else:
                    # Si la fila no tiene 6 valores, asignar valores predeterminados
                    producto, unidad, minimo, maximo, moda, promedio = row[:6]  # Tomar solo los primeros 6 valores

                # Utilizar expresiones regulares para extraer el nombre del producto y la fecha
                matches = re.findall(r'(.+)\s+(\d{1,2}/\d{1,2}/\d{4})$', producto)
                if matches:
                    nombre_producto, fecha = matches[0]
                else:
                    nombre_producto, fecha = producto, ""

                # Eliminar comas y convertir los precios a números flotantes
                minimo = float(minimo.replace(",", ""))
                maximo = float(maximo.replace(",", ""))
                moda = float(moda.replace(",", ""))
                promedio = float(promedio.replace(",", ""))

                # Crear un diccionario con la estructura requerida
                vegetal = {
                    "fecha_de_plaza": fecha if fecha else mysql_date,
                    "producto": nombre_producto,
                    "unidad_comercializacion": unidad,
                    "minimo": minimo,
                    "maximo": maximo,
                    "moda": moda,
                    "promedio": promedio
                }
                data.append(vegetal)

            # Agregar los datos de esta página a la lista de todos los datos
            all_data.extend(data)

        # Imprimir los datos con la nueva estructura
        # for i, item in enumerate(all_data, start=1):
        # item["id"] = i
        # print(item)

    # Conexión SSH y MySQL
    ssh_host = '0.0.0.0'
    ssh_port = 22
    ssh_username = 'username'
    ssh_password = '********'

    # Configuración de la conexión MySQL a través del túnel SSH
    db_host = 'localhost'
    db_port = 3306
    db_user = 'user'
    db_password = '************'
    db_name = 'database_name'

    try:
        # Configuración del túnel SSH
        with SSHTunnelForwarder(
                (ssh_host, ssh_port),
                ssh_username=ssh_username,
                ssh_password=ssh_password,
                remote_bind_address=('127.0.0.1', db_port),
        ) as tunnel:
            print("Túnel SSH establecido")

            # Configuración de la conexión MySQL
            connection = pymysql.connect(
                host=db_host,
                port=tunnel.local_bind_port,
                user=db_user,
                password=db_password,
                db=db_name,
                cursorclass=pymysql.cursors.DictCursor,
            )

            # Realiza operaciones en la base de datos MySQL
            with connection.cursor() as cursor:
                for item in all_data:
                    # Verificar si el ítem ya existe en la base de datos
                    select_query = """
                            SELECT id FROM precios_mayoristas
                            WHERE fecha_de_plaza = %s AND producto = %s
                            """
                    cursor.execute(select_query, (item['fecha_de_plaza'], item['producto']))
                    existing_item = cursor.fetchone()

                    if existing_item:
                        # El ítem ya existe, actualizarlo
                        update_query = """
                                UPDATE precios_mayoristas
                                SET unidad_comercializacion = %s,
                                    minimo = %s,
                                    maximo = %s,
                                    moda = %s,
                                    promedio = %s
                                WHERE id = %s
                                """

                        cursor.execute(update_query, (
                            item['unidad_comercializacion'],
                            item['minimo'],
                            item['maximo'],
                            item['moda'],
                            item['promedio'],
                            existing_item['id']
                        ))
                    else:
                        # El ítem no existe, insertarlo
                        insert_query = """
                                INSERT INTO precios_mayoristas (fecha_de_plaza, producto, unidad_comercializacion, minimo, maximo, moda, promedio)
                                VALUES (%s, %s, %s, %s, %s, %s, %s)
                                """

                        cursor.execute(insert_query, (
                            item['fecha_de_plaza'],
                            item['producto'],
                            item['unidad_comercializacion'],
                            item['minimo'],
                            item['maximo'],
                            item['moda'],
                            item['promedio']
                        ))

            # Guarda los cambios en la base de datos
            connection.commit()

            # Cierra la conexión MySQL
            connection.close()
            print("Actualización completada exitosamente.")

    except Exception as e:
        print("Error:", e)

def check_and_download_email():
    print("Buscando emails nuevos...")
    gmail = Gmail()

    # Consulta para obtener correos actuales etiquetados como "PIMA".
    query_params = {
        "label": "PIMA",
    }

    messages = gmail.get_messages(query=construct_query(query_params))

    # Ordenar los mensajes por fecha de manera descendente (los más recientes primero).
    messages.sort(key=lambda message: message.date, reverse=True)

    if messages:

        latest_message = messages[0]  # El primer mensaje en la lista es el más reciente.
        # Verificar si el correo electrónico contiene un archivo PDF adjunto.
        for attachment in latest_message.attachments:
            if attachment.filename.lower().endswith('.pdf'):
                # Comprobar si el PDF ya ha sido descargado.
                downloaded_files = load_downloaded_files()
                if attachment.filename not in downloaded_files:
                    ## Muestra la informacion del email recibido
                    print("To: " + latest_message.recipient)
                    print("From: " + latest_message.sender)
                    print("Subject: " + latest_message.subject)
                    print("Date: " + latest_message.date)
                    print("Preview: " + latest_message.snippet)
                    # Guardar el archivo PDF adjunto en la ubicación deseada.
                    attachment.save('C:\\Users\\user\\Documents\\CosechaFacilCR\\PDFs_Registro\\' + attachment.filename, overwrite=True)
                    print("PDF descargado:", attachment.filename)
                    # Registrar el archivo descargado.
                    save_downloaded_file(attachment.filename)
                    # Ejecutar el metodo "update_Database()" para actualizar la base de datos
                    update_Database()

                else:
                    print("Verificación exitosa: No hay nuevos pdf por descargar")

    else:
        print("No se encontraron correos electrónicos en la etiqueta 'PIMA'.")

# Programar la verificación de correos cada 12 horas.
schedule.every(12).hours.do(check_and_download_email)

while True:
    schedule.run_pending()
    time.sleep(1)
