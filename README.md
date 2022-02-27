# Prueba MELI - Mutantes

La prueba consiste en desarrollar una API-REST que exponga los servicios donde permita validar una secuencia de ADN,
verificando si es humano o mutante y las estadisticas de las muestras de ADN - El detalle de la prueba esta en el pdf en
la raiz de este repositorio dentro de la carpeta recursos con el nombre "Prueba-Mutantes.pdf"

  ----

## Contenido

- [Arquitectura](#arquitectura)
- [Tecnologías y herramientas](#arquitectura)
- [Instalacion](#api)
- [Api](#api)
- [Ejemplos](#ejemplos)
- [Cobertura](#Cobertura)
- [Conclusiones](#Conclusiones)

----

# Arquitectura

- Es una arquitectura orientada a servicios y por medio de capas para garantizar una arquitectura limpia, estas capas
  estan representadas por los distintos paquetes.

----

# Tecnologías y herramientas

* [Java11] - Lenguaje de programación
* [Maven] - Paquetización y dependencias
* [JUnit4] - Framework para testing
* [Mockito] - Para Mocker servicios para testing
* [MySQL] - Base de datos
* [AWS] - Servidor en la nube
* [Spring-boot] - Server
* [IntelliJ-IDEA] - IDE de desarrollo
* [JaCoCo] - Para estudiar cobertura de los test unitarios
* [Git] - Versionado
* [GitHub] - Repositorio y manual de uso

-------

# Instalación

- Se debe tener estas tecnologías instaladas en la maquina para correr el proyecto (ya sea de forma local o en el
  server).

| Requiere |  |
| ------ | ------ |
| Java11 | https://adoptium.net/releases.html?variant=openjdk11 |
| Git | https://git-scm.com/downloads |
| IntelliJ IDEA | https://www.jetbrains.com/es-es/idea/download/#section=windows |
| Maven 3+ | https://maven.apache.org |
| MySQL | https://dev.mysql.com/downloads/mysql/ |

Abrimos la "git bash here" en la ruta donde queremos clonar y copiamos esta linea:

```sh
$ git clone https://github.com/ivan31297/PruebaMeLi.git
```

Luego correr el Maven para generar el aplicativo

```sh
$ mvn clean install
```

Inicializamos el servidor de ubuntu en aws

```sh
ssh -i "mutants-aws.pem" ubuntu@ec2-3-134-100-12.us-east-2.compute.amazonaws.com
```

Copiamos luego el archivo en nuestro servidor (Esto no es necesario en el momento ya esta, solo es para documentar)

```sh
scp -i mutants-aws.pem mutants-backend-1-1.0.jar ubuntu@ec2-3-134-100-12.us-east-2.compute.amazonaws.com:/home/ubuntu/
```

Nos aseguramos que el servicio de MySQL este em ejeción para luego ejecutar el script con el nombre dna_registros.sql
que se encuentra en la carpeta recursos. Si no se esta muy seguro

```sh

```

Y corremos la aplicación

```sh
$java -jar ./target/mutants-backend-1-1.0.jar
```

------

# API

- El puerto por defecto es 8080.
- Actualmente hay una instancia de la aplicación corriendo en un servidor de AWS (ubuntu).

| DESCRIPCION     | URL                      | PETICION | HEADER                         | RESPUESTA                                         |
|-----------------|--------------------------|----------|--------------------------------|---------------------------------------------------|
| Servicio Mutant | 3.134.100.12:8080/mutant | POST     | Content-Type: application/json | Devuelve 200 si es mutant o 403 en caso contrario |
| Servicio Stats  | 3.134.100.12:8080/stats  | GET      |                                | JSON                                              |

------

# Ejemplos

	1) SERVICIO: mutant 
  	   REQUEST: [TYPE POST; HEADER Content-Type: application/json]
    	
	{
    "dna": [
            "ATGCGA",
            "CAGTGC",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"
            ]
    }
	
	RESPONSE: 200 - OK

	
	{
    "dna": [
            "ATGCGA",
            "CCGTGC",
            "TTATGT",
            "AGAAGG",
            "CTCCTA",
            "TCACTG"
            ]
    }
	
	RESPONSE: 403- Forbidden
	
	2) SERVICIO: stats
	   
	{
        "ratio": 8.5,
        "count_mutant_dna": 17,
        "count_human_dna": 2
    }	
	
	RESPONSE: 200 - OK 
	
	Se deja evidencia de estas pruebas en las imagines en la carpeta recursos IMG-POSTMAN-ISMUTANT-YES.PNG, IMG-POSTMAN-ISMUTANT-NO.PNG y IMG-POSTMAN-STATS.PNG.

------

# Cobertura

- Se realizaron los test con Junit4 con Coverage para estudiar la cobertura de los test. Nos da un 100% en total en los
  paquetes controllers y services/impl. Se encuentra las imagenes adjuntas con los nombres PRUEBA-COVERAGE-1.PNG y
  PRUEBA-COVERAGE-2.PNG.

------

# Conclusiones

- Se validaron las diferentes secuencias de una matriz de DNA.La secuencia de DNA es validada vertical, horizontal y
  diagonal respectivamente
- Se validó la estructura NxN
- Se validó que fuera más de una secuencia de cuatro letras iguales para detectar al mutante
- Test-Automáticos, Code coverage 100% > 80%.
