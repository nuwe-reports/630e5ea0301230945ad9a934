# Reto Talent Squad Backend II

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Documentation](#documentation)
* [Setup](#setup)

## General info
Api de busqueda de empleo, basada en los estilos de arquitectura de monolito y REST.

Se organiza en capas, teniendo diferentes paquetes:
- entity, contiene las clases entidad.
- repository, se encuentran las interfaces necesarias para implemtar la persistencia de los datos.
- service, incluye la lógica de negocios.
- controller, se incluyen tres controladores, uno encargado de todas las peticiones relacionadas con los usuarios, otro para las ofertas y otro que permite que los usuarios se suscriban y reciban por email las ofertas de empleo más recientes.

Además se crearon diferentes dto de entrada y salida para resgauardar la información sensible de los usuarios, los cuales se encuentran en el paquete dto.

## Technologies
Project is created with:
* Java 17
* Spring Boot 2.7.3
* Maven
* H2
* Docker

## Documentation

La aplicación debe estar ejecutandose para poder ver la documentación de la API en el siguiente enlace:

http://localhost:8080/swagger-ui/index.html

Diagrama de clases:

https://drive.google.com/file/d/1Tp2d-PUXwFuiJVj6SF7aSqKvhxe-dRaH/view?usp=sharing


## Setup
Para ejecutar la aplicación en Docker, utilizar el comando:

```
docker-compose up
```
