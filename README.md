# ConsumerApp
### Session and Event Kafka Producer/Consumer
Microservice that sends and consumes messages of various types and stores the values.
The application receives sessions and while a session is open it can receive associated events. Once a new session arrives, the previous session is closed.

### Environment setup
- 1º Download Kafka:
https://www.apache.org/dyn/closer.cgi?path=/kafka/3.5.0/kafka_2.13-3.5.0.tgz 
- 2º Open a terminal and start the ZooKeeper service
bin/zookeeper-server-start.sh config/zookeeper.properties
- 3º Open another terminal and start the Kafka broker service
bin/kafka-server-start.sh config/server.properties

#### Session and Event Example:
- Session:
{"sessionID": "1", "machineId": "5", "createdDate": "2023-06-21T21:59:59.000+00:00"}
- Event:
{"sessionID": "1", "events": [{ "eventAt": "2023-06-22T19:31:59.824+00:00", "eventType": "Prueba", "numericEventValue": 65.0},{ "eventAt": "2023-06-22T19:31:59.824+00:00", "eventType": "Prueba2", "numericEventValue": 65.0}]}
