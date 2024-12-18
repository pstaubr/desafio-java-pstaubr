# desafio-java-pstaubr

Desafpio Java Pablo Staub R
Es una aplicación Java/Maven/Spring Boot (versión 2.7.8) expone una API RESTful con swagger cuya ruta es: http://localhost:8080/desafiojavapstaubr-ms/swagger-ui/index.html#/  para la creación y consulta de usuarios.

## Cómo ejecutar el ms. ##

* Clonar este repositorio
* Asegúrese de estar usando Java 11 y Maven.

Una vez que se ejecute la aplicación, debería ver algo como esto

## Información del ms. ##

MS un servicio REST de Creacion de usuarios y consulta de token jwt con base de datos H2 (en memoria).
Puerto 8080. (revisar en properties).

* Integración completa con framework Spring boot,inversión de control,inyección de dependencia y restful / JSON.
* Puede validar el estado del ms (solo si el proyecto está desplegado correctamente).
* Excepciones de aplicaciones a la respuesta HTTP correcta con mensajes de excepción.
* Spring Data JPA/Hibernate y repository.

### Creación y consulta de usuario. ### 

```
REQUEST:
Endpoint:
http://localhost:8080/desafiojavapstaubr-ms/api/user/registerUser
POST /desafiojavapstaubr-ms/api/user/register
accept: aplicación/json
content-type: aplicación/json

{
	"name": "Juan Rodriguez",
	"email": "juan@rodriguez.org",
	"password": "Kunter2",
	"phones": [
		{
			"number": "1234567",
			"citycode": "1",
			"contrycode": "57"
		}
	]
}

RESPUESTA: HTTP 200 (OK)
{
  "id": "4028810593dafa610193db00188a0000",
  "email": "juan@rodriguez.org",
  "created": "2024-12-18T15:20:37.1887682",
  "modified": "2024-12-18T15:20:36.8293609",
  "last_login": "2024-12-18T15:20:36.4557572",
  "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJpYXQiOjE3MzQ1NDYwMzd9.XgiwbZnFE6DI5kuh_Dr8PUn7OsyyQe6HLAT2ErLqwghCeYAn6WCv9Hu6y1e_Iwd_WacAErRmhCX3u5QnK4p01g",
  "isactive": true,
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
RESPUESTA: HTTP 400 (NOK)
Body: 
{
  "status": 400,
  "message": "Mensaje error: Email ya existe en nuestro registros...",
  "timestamp": "2024-12-18T18:21:03.271+00:00"
}
```

### Buscar usuario por token (Header Authorization). ### 

```
Endpoint:
http://localhost:8080/desafiojavapstaubr-ms/api/user/byTokenUser
GET /api/user/byTokenUser
accept: aplicación/json
Authorization: Bearer TOKEN generado en la creación de usuario

Respuesta: HTTP 200 (OK)
{
  "id": "402881d185d2d9830185d2ee01a60002",
  "email": "juan@rodriguez.orgs",
  "created": "2023-01-21T06:04:51.501614",
  "modified": "2023-01-21T06:04:51.491612",
  "last_login": "2023-01-21T06:04:51.409615",
  "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmdzIiwiaWF0IjoxNjc0MjgxMDkxfQ.UyTlFUvvZeUFqhPxuTVkG4T5HS-3ZUE1Ipa45fgBFK2IKbark4QIwFv6IgcqSPq0oSzHU_ao6_w0qcbp7Z2wFQ",
  "isactive": true,
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
```

### Estado del servicio. ###

```
Endpoint:
http://localhost:8080/desafiojavapstaubr-ms/healthcheck
GET /accept
accept: aplicación/json
RESPUESTA: HTTP 200 (OK)
Body: healthcheck OK.
```

### Para ver su base de datos en memoria H2. ###

El perfil de "testdb" se ejecuta en la base de datos en memoria H2. Para ver y consultar la base de datos, puede navegar
a http://localhost:8080/console. El nombre de usuario predeterminado es 'sa' con una contraseña '123456'
