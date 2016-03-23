Public Transport Monitor
========================

Build
-----
Simply:
> mvn clean install

Run mocked Warsaw Trams Api to work offline
-------------------------------------------
> java -jar apps\mock-warsaw-trams-app\target\mock-warsaw-trams-app-0.0.1-SNAPSHOT.jar

Server starts on localhsot:8099 and exposes two resources:

> http://localhost:8099/trams

> http://localhost:8099/withPossibleEmptyResult?emptyResultEveryN=50

Run data store app connected to mocked api
------------------------------------------
> java -jar apps\data-store-app\target\data-store-app-0.0.1-SNAPSHOT.jar --spring.profiles.active=mock-api

Server starts on localhsot:8080