cd hsqldb\bin
start cmd /k runServer.bat
cd ..\..\bin\kafka_2.11-1.0.1\bin\windows
rmdir /s /q C:\tmp\kafka-logs
start cmd /k zookeeper-server-start.bat ..\..\config\zookeeper.properties
timeout /t 10
kafka-server-start.bat ..\..\config\server.properties