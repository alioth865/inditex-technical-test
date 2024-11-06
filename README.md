# Prueba técnica para Inditex

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- H2 Database
- JUnit 5
- Karate
- OpenAPI (Swagger)
- Maven

## Estructura del Proyecto

El proyecto sigue una arquitectura hexagonal con los siguientes paquetes:

- `application`: Contiene la lógica de negocio de la aplicación.
- `domain`: Contiene las entidades y puertos.
- `infrastructure`: Contiene la implementación de los repositorios y la configuración de la base de
  datos.

El objetivo de esta estructura es separar la lógica de negocio de la aplicación de los detalles
técnicos y de la infraestructura. Además, proporciona claridad y facilita el mantenimiento del
código.

## Enfoque API First

Se ha utilizado OpenAPI (Swagger) para definir la API REST. La definición de la API se encuentra en
el archivo `openapi.yaml` en la raíz del proyecto. Una vez iniciado el servidor, se puede acceder a
la documentación de la API en la siguiente URL:

- [Documentación de la API](http://localhost:8080/swagger-ui/index.html)

Para generar la implementación de la API a partir del archivo `openapi.yaml`, se ha utilizado el
plugin `springdoc-openapi-maven-plugin`.

## Ejemplos de Uso del Endpoint REST

A continuación, se presentan ejemplos de cómo realizar peticiones al servicio REST utilizando cURL:

```bash
# Peticion para obtener el precio del product 35455  en la brand 1 el 2020-06-14-00.00.00
curl -X GET "http://localhost:8080/brand/1/product/35455/price/2020-06-14-00.00.00" -H "accept: application/json"

# Peticion para obtener todas las marcas
curl -X GET "http://localhost:8080/brands" -H "accept: application/json"

# Peticion para obtener todos los productos
curl -X GET "http://localhost:8080/products" -H "accept: application/json"
```

## Test

Se han implementado test unitarios para obtener una cobertura cercana al 100%. Además, se han
implementado dost test de integración uno usando Karate y otro MockMvc y para probar el endpoint del API según lo solicitado en el
enunciado.

## Base de Datos

Se ha utilizado una base de datos en memoria H2, inicializada con los datos del ejemplo. La
configuración de la base de datos se encuentra en el archivo `application.properties`.

## Instrucciones de Instalación y Ejecución

1. Clona el repositorio: `git clone https://github.com/alioth865/inditex-technical-test.git`
2. Navega al directorio del proyecto: `cd inditex-technical-test    `
3. Compila el proyecto: `mvn clean package`
4. Ejecuta la aplicación: `java -jar target/inditex-0.0.1-SNAPSHOT.jar`

## Instrucciones para Ejecutar las Pruebas

Para ejecutar las pruebas, simplemente ejecuta el siguiente comando:

```bash
mvn test
```

## Enunciado

En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja
el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas
determinadas. A continuación se muestra un ejemplo de la tabla con los campos relevantes:

| BRAND_ID | START_DATE          | END_DATE            | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|---------------------|---------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2          | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3          | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4          | 35455      | 1        | 38.95 | EUR  |

Campos:

- BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
- START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
- PRICE_LIST: Identificador de la tarifa de precios aplicable.
- PRODUCT_ID: Identificador código de producto.
- PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se
  aplica la de mayor prioridad (mayor valor numérico).
- PRICE: precio final de venta.
- CURR: iso de la moneda.

Se pide:

Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta tal que:

Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de
cadena.
Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar,
fechas de aplicación y precio final a aplicar.

Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, (se
pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato
que se considere adecuado para los mismos).

Desarrollar unos test al endpoint rest que validen las siguientes peticiones al servicio con los
datos del ejemplo:

-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)

Se valorará:

- Diseño y construcción del servicio.
- Calidad de Código.
- Resultados correctos en los test.



