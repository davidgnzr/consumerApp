# consumerApp
Session and Event Consumer
Environment setup

KAFKA
First, it is necessary to download Kafka, start it and create the two topics the application is waiting for.
https://www.apache.org/dyn/closer.cgi?path=/kafka/3.5.0/kafka_2.13-3.5.0.tgz 
1º Start the ZooKeeper service
bin/zookeeper-server-start.sh config/zookeeper.properties
2º Start the Kafka broker service
bin/kafka-server-start.sh config/server.properties
3º Create the topics
bin/kafka-topics.sh --create --topic sessionTopic --bootstrap-server localhost:9092
bin/kafka-topics.sh --create --topic eventTopic --bootstrap-server localhost:9092
4º Add a session and some events
Session:
bin/kafka-console-producer.sh --topic sessionTopic --bootstrap-server localhost:9092
{"sessionID": "1", "machineId": "5", "createdDate": "2023-06-21T21:59:59.000+00:00"}
Event:
bin/kafka-console-producer.sh --topic eventTopic --bootstrap-server localhost:9092
{"sessionID": "1", "events": [{ "eventAt": "2023-06-22T19:31:59.824+00:00", "eventType": "Prueba", "numericEventValue": 65.0},{ "eventAt": "2023-06-22T19:31:59.824+00:00", "eventType": "Prueba2", "numericEventValue": 65.0}]}

RUN THE APPLICATION
I used IntellIJ. To run it, no configuration is required. The project needs to be imported and executed.
