!#/bin/bash

git clone https://github.com/Bandtec/Totem-Monitoring.git
cd Totem-Monitoring
ls
cd java/TotemMonitoring
ls
mvn clean install package
cd target/
java -jar TotemMonitoring-1.0-SNAPSHOT-jar-with-dependencies.jar

