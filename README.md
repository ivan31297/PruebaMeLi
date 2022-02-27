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
* [JMeter] - Para realizar pruebas de estres
* [Git] - Versionado
* [GitHub] - Repositorio y manual de uso

-------

# Instalación

- Se debe tener estas tecnologías instaladas en la maquina para correr el proyecto (ya sea de forma local o en el
  server).

| Requiere      |  |
|---------------| ------ |
| Java11        | https://adoptium.net/releases.html?variant=openjdk11 |
| Git           | https://git-scm.com/downloads |
| IntelliJ IDEA | https://www.jetbrains.com/es-es/idea/download/#section=windows |
| Maven 3+      | https://maven.apache.org |
| XAMPP         | https://www.apachefriends.org/es/download.html |

<h3>Local</h3>

Abrimos la "git bash here" en la ruta donde queremos clonar y copiamos esta linea:

```sh
$ git clone https://github.com/ivan31297/PruebaMeLi.git
```

Luego abrir el proyecto una vez clonado con el IDE IntelliJ IDEA, y ejecutar la siguiente instrucíón en la terminal para generar el .jar.

```sh
$ mvn clean install
```

En la carpeta resource se encuentra el script de la base de datos, para realizar la importación de la base de datos iniciamos el servicio del motor de base de datos MySQL.

Linux
```sh
$ /opt/lampp/lampp start
```

Windows
```sh
$ XAMPP Control Panel -> Hacer click en el botón Start (Apache y MySQL)
```

Para realizar ejecución de la aplicaciónabrimos la terminal en la carpeta del proyecto y accedemos a la carpeta target y escribimos lo siguiente

```sh
$ java -jar mutant-0.0.1-SNAPSHOT.jar
```


<h3>Cloud</h3>

Una vez creada la cuenta en aws, lo primero que se crea una instancia de un servidor ubuntu, luego se habilitan los 
puertos (80, 8080, 3306) y para finalizar la creación de la instancia se genera una llave para tener acceso por SSH.

```sh
ssh -i "mutants-aws.pem" ubuntu@ec2-3-134-100-12.us-east-2.compute.amazonaws.com
```

Copiamos luego el archivo en nuestro servidor (Esto no es necesario en el momento ya esta, solo es para documentar)

```sh
chmod 400 mutantes.pem
ssh -i "mutantes.pem" ubuntu@ec2-3-89-187-163.compute-1.amazonaws.com
```

Para realizar la instalación del XAMPP ejecutamos lo siguiente en la terminal

```sh
wget https://www.apachefriends.org/xampp-files/7.4.12/xampp-linux-x64-7.4.12-0-installer.run
sudo chmod a+x xampp-linux-x64-7.4.12-0-installer.run
sudo ./xampp-linux-x64-7.4.12-0-installer.run
```

Ahora se realiza la  instalación del Java JDK 11 para poder ejecutar el archivo .jar

```sh
sudo apt-get install openjdk-11-jdk
```

Copiaremos el archivo .jar en el servidor, pero debemos tener encuenta el archivo .pen para la autenticación en el
servidor.

```sh
scp -i mutantes.pem mutant-0.0.1-SNAPSHOT.jar ubuntu@ec2-3-89-187-163.compute-1.amazonaws.com
```

Y corremos la aplicación

```sh
nohup java -jar mutant-0.0.1-SNAPSHOT.jar >log.log &
```

------

# API

- El puerto por defecto es 8080.
- Actualmente hay una instancia de la aplicación corriendo en un servidor de AWS (ubuntu).

| DESCRIPCION     | URL                                          | PETICION | HEADER                         | RESPUESTA                                                                              |
|-----------------|----------------------------------------------|----------|--------------------------------|----------------------------------------------------------------------------------------|
| Servicio Mutant | http://3.89.187.163:8080/api/v1/xmen/mutant/ | POST     | Content-Type: application/json | Devuelve 200 si es mutant, 403 en caso contrario y 400 sí el formato JSON no es valido |
| Servicio Stats  | http://3.89.187.163:8080/api/v1/xmen/stats   | GET      |                                | JSON                                                                                   |

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

    {
      "dna": [
              "ATGCGA",
              "CAGTGC",
              "TCTTGT",
              "TGAAGGG",
              "CGCCTa",
              "TCACTG"
      ]
    }

    RESPONSE: 400- Badrequest
	
	2) SERVICIO: stats
	   
	{
        "ratio": 8.5,
        "count_mutant_dna": 17,
        "count_human_dna": 2
    }
	
	RESPONSE: 200 - OK 

    {
      "ratio": 0.0,
      "count_mutant_dna": 1,
      "count_human_dna": 0,
      "error_message": "El ratio es 0.0 porque no se puede dividir por cero"
    }

    RESPONSE: 200 - OK
	
	Se deja evidencia de estas pruebas en las imagines en la carpeta recursos IMG-POSTMAN-ISMUTANT-YES.PNG, IMG-POSTMAN-ISMUTANT-NO.PNG, IMG-POSTMAN-STATS.PNG, IMG-POSTMAN-STATS DIVISION CERO.PNG y IMG-POSTMAN-VAL-NxN.PNG.

------

# Cobertura

- Se realizaron los test con Junit4 con Coverage para estudiar la cobertura de los test. Nos da un 100% en total en los
  paquetes controllers y services/impl. Se encuentra las imagenes adjuntas con los nombres PRUEBA-COVERAGE-1.PNG y
  PRUEBA-COVERAGE-2.PNG.
- Se realizó prueba de estres con JMeter, se encuentra la captura con los resultados obtenidos en la carpeta resource 
con los nombres JMeter Test 1.PNG y JMeter Test 2.PNG, ademas de la configuración de la herramiento Mutants Stress Test.jmx

------

# Conclusiones

- Se validaron las diferentes secuencias de una matriz de DNA.La secuencia de DNA es validada vertical, horizontal y
  diagonal respectivamente.
- Se validó la estructura NxN
- Se validó que los caracteres solo fueran (A,T,C,G) en mayusculas.
- Se validó que fuera más de una secuencia de cuatro letras iguales para detectar al mutante
- Test-Automáticos, Code coverage 100% > 80%.
- Se realizó prueba de estres con JMeter
