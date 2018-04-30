[![Build Status](https://travis-ci.org/Arquisoft/InciManager_e2b.svg?branch=master)](https://travis-ci.org/Arquisoft/InciManager_e2b)
# InciManagement_e2b
Incimanagement e2b

# Authors 2018

- Fernando Sánchez Canella (UO252469)
- Pelayo Rodriguez Garcia (UO251634)
- Jorge Rionda (UO245934)

# Proyecto Agents
Este proyecto es necesario que se este ejecutando para la comprobación de usuarios

https://github.com/Arquisoft/Agents_e2b

# Pasos

- Ejecutar el proyecto Agents, especificado anteriormente
- Ejecutar Kafka para el envio de mensajes (https://kafka.apache.org/) para ello iniciar el zookeper con bin\windows\zookeeper-server-start.bat config\zookeeper.properties y Kafka con bin\windows\kafka-server-start.bat config\server.properties (en el caso de Windows)
- Iniciar el inciManager
- Enviar incidencias mediante http://localhost:8090/ o realiza a la URL http://localhost:8090/incidence/send una peticion POST
