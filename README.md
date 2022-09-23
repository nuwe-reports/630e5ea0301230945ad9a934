# Reto Talent Squad Backend II

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Documentation](#documentation)
* [Setup](#setup)
* [Testing](#testing)
* [Improvements](#Improvements)

## General info
Api de busqueda de empleo, basada en los estilos de arquitectura de monolito y REST.

Se organiza en capas, teniendo diferentes paquetes:
- entity, contiene las clases entidad.
- repository, se encuentran las interfaces necesarias para implemtar la persistencia de los datos.
- service, incluye la lógica de negocios.
- controller, se incluyen tres controladores, uno encargado de todas las peticiones relacionadas con los usuarios, otro para las ofertas y otro que permite que los usuarios se suscriban y reciban por email las ofertas de empleo más recientes.

También incluye diferentes dto, tanto de entrada como de salida, para resgauardar la información sensible de los usuarios, los cuales se encuentran en el paquete dto.


## Technologies
Project is created with:
* Java 17
* Spring Boot 2.7.3
* Maven
* H2
* Docker

## Documentation

La aplicación debe estar ejecutandose para poder ver la documentación de la API en el siguiente enlace:

http://localhost:8080/swagger-ui-easy-job-search.html

Diagrama de clases entity:

https://drive.google.com/file/d/1Tp2d-PUXwFuiJVj6SF7aSqKvhxe-dRaH/view?usp=sharing


## Setup
Para ejecutar la aplicación en Docker, ejecutar en la terminal el comando:

```
docker-compose up
```

## Testing

Postman Collection:

La colección de peticiones en postman se encuentra en la carpeta raíz del proyecto.

```
easy-job.postman_collection.json
```
Además, se realizaron pruebas unitarias de la capa servicio.

## Improvements

- Implementar mecanismos de Autorización y Autenticación, pudiendo usar para ello Spring Security.
- Realizar tests de integración.
- Para implementar la suscripción de candidatos a recibir nuevas ofertas de empleo, se configuró tanto con gmail como con un servicio de envio de mails (sendinblue), ambas opciones funcionan, se dejó habilitada la opción con gmail.
En ambos casos están expuestas las contraseñas que se crearon para el uso de aplicaciones de terceros menos seguras, por lo cual, una mejora a implementar sería utilizar un sistema de bóvedas para evitar dicha exposición.
