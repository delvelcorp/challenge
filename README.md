# Challange Backend Spring Boot Microservicios

Proyecto con una arquitectura de microservicios, para la gesti贸n de movimiento en cuentas bancarias, con el manejo de clientes, cuentas y transacciones.


## Instrucciones 馃殌

_Este proyecto esta desarrollado en Spring Boot sobre PostgreSQL como base de datos, cuenta con eureka para la comunicaci贸n directa entre las apis y docker para la creaci贸n y ejecuci贸n de los contenedores.

_Gracias a ello usted podr谩 seguir las instrucciones ahora detalladas y obtener una copia del proyecto en su equipo y posterior a ello ejecutarlo localmente.

- Desde la ra铆z de este repositorio ubicado en la rama principal, realice la clonaci贸n del proyecto a una rama en una ruta local de su equipo.
**git clone rutaEspecificaGit**

- Desde su equipo local, puede verificar tres rutas de este proyecto, detalladas a continuaci贸n:
    bankservices\endpoint -> Contiene los 3 endpoints necesarios para los servicios requeridos
	bankservices\infrastructure -> Contiene los dos servidores necesarios para levantar la arquitectura propuesta
	bankservices\database-config -> Contiene las configuraciones necesarias para la conexi贸n hacia la base de datos


### Requisitos 馃搵

_La instalaci贸n y ejecuci贸n de este proyecto al estar basada en contenedores es bastante sencilla_

- Necesita contar con un equipo windows.
- Tener instalado java 8 o superior.
- Tener instalado maven para la compilaci贸n de las im谩genes.
- Tener instalado docker para el manejo de los contenedores.
Contar con un equipo que tenga un sistema operativo Linux


### Ejecuci贸n 馃敡

_Siga los pasos propuestos a fin de tener el proyecto corriendo en su equipo:

- Ingrese en el primer paquete llamado "endpoint" y dentro de el en cada una de las apis: account, customer y transaction.
- Dentro de cada una de ellos ejecute el siguiente comando a fin de construir el proyecto y la imagen asociada al mismo.
**mvn clean install -Dmaven.test.skip**

- Ingrese ahora en el paquete "infrastructure" y realice el mismo procedimiento para los servidores: config y eureka.

Valide en su equipo que disponga de cinco imagenes creadas respectivas a lo realizado anteriormente:
**docker images**


## Despliegue 馃摝

_Para el despliegue del proyecto utilizaremos docker compose, ya que este nos permit茅 definir y levantar varios contenedores al tiempo.

- Nos ubicamos en la carpeta ra铆z del proyecto "bankservices", y ejecutamos el siguiente comando:
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
b543989bd244   delvelcorp/challenge:config-server   "sh -c 'java $JAVA_O鈥?   40 hours ago     Up 12 minutes   0.0.0.0:9002->9002/tcp   config_container
3e8d779d15a3   delvelcorp/challenge:eureka-server   "sh -c 'java $JAVA_O鈥?   41 hours ago     Up 12 minutes   0.0.0.0:9000->9000/tcp   discovery-eureka
265cdaa7a9a2   postgres:latest                    "docker-entrypoint.s鈥?   41 hours ago     Up 12 minutes   0.0.0.0:3432->5432/tcp   postgres_container
```

_Como se puede observar faltan los servicios correspondientes a los endpoint, para visualizar que ocurri贸, utilizamos el log de docker
**docker logs -f customer_container**
```
org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://discovery-eureka:9000/eureka/apps/": 
Connection refused (Connection refused); nested exception is java.net.ConnectException: Connection refused (Connection refused)
```

- De acuerdo a la salida de dicho comando vemos que hay una conexi贸n rechazada por el servidor de Eureka, esto debido a que necesitan que Eureka este levantado totalmente para poder ejecutarse.

- Por ello solicitamos nuevamente el inicio de los contenedores a fin de levantar los restantes.
**docker-compose start**
```
CONTAINER ID   IMAGE                              COMMAND                  CREATED          STATUS          PORTS                    NAMES
2e31678406b8   delvelcorp/challenge:account         "sh -c 'java $JAVA_O鈥?   12 minutes ago   Up 8 minutes    0.0.0.0:8082->8082/tcp   account_container
e54814f84fb0   delvelcorp/challenge:customer        "sh -c 'java $JAVA_O鈥?   12 minutes ago   Up 8 minutes    0.0.0.0:8081->8081/tcp   customer_container
364d94591508   delvelcorp/challenge:transaction     "sh -c 'java $JAVA_O鈥?   12 minutes ago   Up 8 minutes    0.0.0.0:8083->8083/tcp   transaction_container
b543989bd244   delvelcorp/challenge:config-server   "sh -c 'java $JAVA_O鈥?   40 hours ago     Up 12 minutes   0.0.0.0:9002->9002/tcp   config_container
3e8d779d15a3   delvelcorp/challenge:eureka-server   "sh -c 'java $JAVA_O鈥?   41 hours ago     Up 12 minutes   0.0.0.0:9000->9000/tcp   discovery-eureka
265cdaa7a9a2   postgres:latest                    "docker-entrypoint.s鈥?   41 hours ago     Up 12 minutes   0.0.0.0:3432->5432/tcp   postgres_container
```

- Como se puede observar tenemos todos los servicios levantados y corriendo


## Pruebas 鈿欙笍

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
[
    {
        "id": 7,
        "date": "2022-09-02",
        "transactionType": "WITHDRAWAL",
        "value": 575.00,
        "balance": 1425.00,
        "status": true,
        "message": "Remove of 575",
        "accountId": 478758
    },
    {
        "id": 9,
        "date": "2022-09-02",
        "transactionType": "DEPOSIT",
        "value": 600.00,
        "balance": 700.00,
        "status": true,
        "message": "Deposit of  600",
        "accountId": 225487
    },
    {
        "id": 10,
        "date": "2022-09-02",
        "transactionType": "DEPOSIT",
        "value": 150.00,
        "balance": 160.00,
        "status": true,
        "message": "Deposit of 150",
        "accountId": 495878
    },
    {
        "id": 11,
        "date": "2022-09-02",
        "transactionType": "WITHDRAWAL",
        "value": 540.00,
        "balance": 0.00,
        "status": true,
        "message": "Remove of 540",
        "accountId": 496825
    }
]
```

```
Reporte por cuenta

localhost:8083/transactions/report?cliente=478758&desde=2022-09-02&hasta=2022-09-02
[
    {
        "id": 7,
        "date": "2022-09-02",
        "transactionType": "WITHDRAWAL",
        "value": 575.00,
        "balance": 1425.00,
        "status": true,
        "message": "Remove of 575",
        "accountId": 478758
    }
]
```

```
Reporte por cliente

localhost:8082/accounts/report?customer=2&from=2022-09-02&to=2022-09-02
[
    {
        "date": "2022-09-02T00:00:00.000+00:00",
        "account": "Marianela Montalvo ",
        "customer": null,
        "accountNumber": 225487,
        "accountType": "CURRENT",
        "initialBalance": 100.00,
        "status": true,
        "transactionValue": 600.0,
        "transactionBalance": 700.0
    },
    {
        "date": "2022-09-02T00:00:00.000+00:00",
        "account": "Marianela Montalvo ",
        "customer": null,
        "accountNumber": 496825,
        "accountType": "SAVINGS",
        "initialBalance": 540.00,
        "status": true,
        "transactionValue": -540.0,
        "transactionBalance": 0.0
    }
]
```

## Versionado 馃搶
* [Git](https://git-scm.com/). 


## Construcci贸n 馃洜锔?

* [Spring](https://spring.io/)
* [Maven](https://maven.apache.org/)
* [PostgreSQL](https://www.postgresql.org/)
* [Docker](https://www.docker.com/)
* [Postman](https://www.postman.com/)


## Autores 鉁掞笍

* **Pa煤l Vidal** - *Desarrollo completo* - [pvidalm](https://github.com/pvidalm/)