# Challange Backend Spring Boot Microservicios

Proyecto con una arquitectura de microservicios, para la gestión de movimiento en cuentas bancarias, con el manejo de clientes, cuentas y transacciones.


## Instrucciones 🚀

_Este proyecto esta desarrollado en Spring Boot sobre PostgreSQL como base de datos, cuenta con eureka para la comunicación directa entre las apis y docker para la creación y ejecución de los contenedores.

_Gracias a ello usted podrá seguir las instrucciones ahora detalladas y obtener una copia del proyecto en su equipo y posterior a ello ejecutarlo localmente.

- Desde la raíz de este repositorio ubicado en la rama principal, realice la clonación del proyecto a una rama en una ruta local de su equipo.
**git clone rutaEspecificaGit**

- Desde su equipo local, puede verificar tres rutas de este proyecto, detalladas a continuación:
    bankservices\endpoint -> Contiene los 3 endpoints necesarios para los servicios requeridos
	bankservices\infrastructure -> Contiene los dos servidores necesarios para levantar la arquitectura propuesta
	bankservices\database-config -> Contiene las configuraciones necesarias para la conexión hacia la base de datos


### Requisitos 📋

_La instalación y ejecución de este proyecto al estar basada en contenedores es bastante sencilla_

- Necesita contar con un equipo windows.
- Tener instalado java 8 o superior.
- Tener instalado maven para la compilación de las imágenes.
- Tener instalado docker para el manejo de los contenedores.
Contar con un equipo que tenga un sistema operativo Linux


### Ejecución 🔧

_Siga los pasos propuestos a fin de tener el proyecto corriendo en su equipo:

- Ingrese en el primer paquete llamado "endpoint" y dentro de el en cada una de las apis: account, customer y transaction.
- Dentro de cada una de ellos ejecute el siguiente comando a fin de construir el proyecto y la imagen asociada al mismo.
**mvn clean install -Dmaven.test.skip**

- Ingrese ahora en el paquete "infrastructure" y realice el mismo procedimiento para los servidores: config y eureka.

Valide en su equipo que disponga de cinco imagenes creadas respectivas a lo realizado anteriormente:
**docker images**


## Despliegue 📦

_Para el despliegue del proyecto utilizaremos docker compose, ya que este nos permité definir y levantar varios contenedores al tiempo.

- Nos ubicamos en la carpeta raíz del proyecto "bankservices", y ejecutamos el siguiente comando:
**docker-compose up -d**
```
Starting id-eureka ... done
Starting postgres  ... done
Starting id-config ... done
Starting id-transaction ... done
Starting id-account     ... done
Starting id-customer    ... done
```

Despues de unos 20 segundos del despliegue podemos ver que servicios se han levantado 
**docker ps**
```
CONTAINER ID   IMAGE                              COMMAND                  CREATED          STATUS          PORTS                    NAMES
b543989bd244   quisange/challenge:config-server   "sh -c 'java $JAVA_O…"   40 hours ago     Up 12 minutes   0.0.0.0:9002->9002/tcp   config_container
3e8d779d15a3   quisange/challenge:eureka-server   "sh -c 'java $JAVA_O…"   41 hours ago     Up 12 minutes   0.0.0.0:9000->9000/tcp   discovery-eureka
265cdaa7a9a2   postgres:latest                    "docker-entrypoint.s…"   41 hours ago     Up 12 minutes   0.0.0.0:3432->5432/tcp   postgres_container
```

_Como se puede observar faltan los servicios correspondientes a los endpoint, para visualizar que ocurrió, utilizamos el log de docker
**docker logs -f customer_container**
```
org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://discovery-eureka:9000/eureka/apps/": 
Connection refused (Connection refused); nested exception is java.net.ConnectException: Connection refused (Connection refused)
```

- De acuerdo a la salida de dicho comando vemos que hay una conexión rechazada por el servidor de Eureka, esto debido a que necesitan que Eureka este levantado totalmente para poder ejecutarse.

- Por ello solicitamos nuevamente el inicio de los contenedores a fin de levantar los restantes.
**docker-compose start**
```
CONTAINER ID   IMAGE                              COMMAND                  CREATED          STATUS          PORTS                    NAMES
2e31678406b8   quisange/challenge:account         "sh -c 'java $JAVA_O…"   12 minutes ago   Up 8 minutes    0.0.0.0:8082->8082/tcp   account_container
e54814f84fb0   quisange/challenge:customer        "sh -c 'java $JAVA_O…"   12 minutes ago   Up 8 minutes    0.0.0.0:8081->8081/tcp   customer_container
364d94591508   quisange/challenge:transaction     "sh -c 'java $JAVA_O…"   12 minutes ago   Up 8 minutes    0.0.0.0:8083->8083/tcp   transaction_container
b543989bd244   quisange/challenge:config-server   "sh -c 'java $JAVA_O…"   40 hours ago     Up 12 minutes   0.0.0.0:9002->9002/tcp   config_container
3e8d779d15a3   quisange/challenge:eureka-server   "sh -c 'java $JAVA_O…"   41 hours ago     Up 12 minutes   0.0.0.0:9000->9000/tcp   discovery-eureka
265cdaa7a9a2   postgres:latest                    "docker-entrypoint.s…"   41 hours ago     Up 12 minutes   0.0.0.0:3432->5432/tcp   postgres_container
```

- Como se puede observar tenemos todos los servicios levantados y corriendo


## Pruebas ⚙️

_Validar el funcionamiento de los microservicios de negocio a traves de Postman

```
Clientes creados

localhost:8081/customers
[
    {
        "id": 2,
        "name": "Marianela Montalvo ",
        "gender": "WOMAN",
        "age": 22,
        "identification": "2222222222",
        "address": "Amazonas y  NNUU",
        "phone": "097548965",
        "password": "5678",
        "status": true,
        "accounts": null
    },
    {
        "id": 3,
        "name": "Juan Osorio",
        "gender": "MAN",
        "age": 43,
        "identification": "3333333333",
        "address": "13 junio y Equinoccial",
        "phone": "098874587",
        "password": "1245",
        "status": true,
        "accounts": null
    },
    {
        "id": 1,
        "name": "Jose Lema",
        "gender": "MAN",
        "age": 31,
        "identification": "1111111111",
        "address": "Otavalo sn y principal",
        "phone": "098254785",
        "password": "1234",
        "status": true,
        "accounts": null
    }
]
```

```
Cuentas creadas
localhost:8082/accounts

[
    {
        "accountNumber": 478758,
        "accountType": "S",
        "initialBalance": 2000.00,
        "status": true,
        "transactions": null,
        "customerId": 1
    },
    {
        "accountNumber": 225487,
        "accountType": "C",
        "initialBalance": 100.00,
        "status": true,
        "transactions": null,
        "customerId": 2
    },
    {
        "accountNumber": 495878,
        "accountType": "S",
        "initialBalance": 10.00,
        "status": true,
        "transactions": null,
        "customerId": 3
    },
    {
        "accountNumber": 496825,
        "accountType": "S",
        "initialBalance": 540.00,
        "status": true,
        "transactions": null,
        "customerId": 2
    },
    {
        "accountNumber": 585545,
        "accountType": "C",
        "initialBalance": 1000.00,
        "status": true,
        "transactions": null,
        "customerId": 1
    }
]
```

```
Movimientos creados

localhost:8083/transactions

```

```
Reporte

localhost:8083/transactions

```


## Versionado 📌
* [Git](https://git-scm.com/). 


## Construcción 🛠️

* [Spring](https://spring.io/)
* [Maven](https://maven.apache.org/)
* [PostgreSQL](https://www.postgresql.org/)
* [Docker](https://www.docker.com/)
* [Postman](https://www.postman.com/)


## Autores ✒️

* **Paúl Vidal** - *Desarrollo completo* - [pvidalm](https://github.com/pvidalm/)
