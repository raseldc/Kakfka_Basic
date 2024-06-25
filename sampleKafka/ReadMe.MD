# Sample Kafka Project

This is a sample project demonstrating the use of Kafka with Spring Boot and Java.

## Technologies Used

- Java
- Spring Boot 3
- Apache Kafka
- Maven

## Project Structure

The project is structured into several key components:

- `Controller.java`: This is the main controller for the application. It contains endpoints for sending messages to a Kafka topic, adding two values, subtracting two values, and retrieving sent messages.

- `KafkaProducerConfig.java`: This class is responsible for the configuration of the Kafka Producer. It sets up the necessary properties and creates a `KafkaTemplate` bean that can be used to send messages to Kafka.

- `MessageConsumer.java`: This class is responsible for consuming messages from a Kafka topic. It listens to a specific topic and prints out any received messages.

- `KafkaConsumerConfig.java`: This class is responsible for the configuration of the Kafka Consumer. It sets up the necessary properties and creates a `ConsumerFactory` and `ConcurrentKafkaListenerContainerFactory` beans that can be used to consume messages from Kafka.

## How to Run

1. Ensure you have Kafka running locally on port 9092.
2. Build the project using Maven: `mvn clean install`.
3. Run the application: `java -jar target/sample-kafka-0.0.1-SNAPSHOT.jar`.
4. Use the exposed endpoints to interact with the application.

Please note that this is a basic Kafka application for learning purposes and may not be suitable for production use.