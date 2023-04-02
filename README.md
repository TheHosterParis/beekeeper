# beekeeper

## Environnement
The environment of this app is fully using docker containers
In order to use the full local environnent follow the steps for each component
NB: Watch out with the binding port
### Oracle
checkout docker oracle project
./buildContainerImage.sh -e -v 19.3.0
docker run -d --name oracle -p 1521:1521 -p 5500:5500 -e ORACLE_PWD=1789Yvan oracle/database:19.3.0-ee
sqlplus system/1789Yvan@//localhost:1521/ORCLCDB

### Jenkins
docker pull jenkins/jenkins
run container
http://localhost:8080/secure/RapidBoard.jspa?rapidView=1&projectKey=BEEK&view=detail

### Kafka
docker pull kafka,,,,
use dockerfile to initiate the kafa enironment

### Postman (newman)
docker pull postman/newman

### Graphite 
docker run -d --name graphite --restart=always -p 80:80 -p 2003-2004:2003-2004 -p 2023-2024:2023-2024 -p 8125:8125/udp -p 8126:8126 graphiteapp/graphite-statsd

### scrapper for  meteo data

### JIRA
to simulate and trace back the project with an agile approach

docker volume create --name jiraVolume
docker run -v jiraVolume:/var/atlassian/application-data/jira --name="jira" -d -p 8080:8080 atlassian/jira-software

username: dev
pwd: dev

### Cassandra
docker pull cassandra
docker run --name cassandra -p 9042:9042 -d cassandra:latest
docker exec -it cassandra cqlsh

### Hadop
docker pull apache/hadoop
docker exec -it hadoop /bin/bash
docker run -it apache/hadoop /bin/bash