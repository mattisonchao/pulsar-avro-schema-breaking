# Pulsar Avro Schema Breaking Change Example

This project demonstrates a breaking change in Apache Pulsar's Protobuf schema handling between version 4.0.x and 4.1.x.

## Project Structure

This project contains two sub-projects:

*   `pulsar-client-3-0-1`: A Pulsar client application using version 3.0.1 of the Pulsar client library.
*   `pulsar-client-4-0`: A Pulsar client application using version 4.0.0 of the Pulsar client library.

Both applications use the same Protobuf schema defined in the `pojo` sub-project.

## Prerequisites

*   Docker
*   Java Development Kit (JDK) 17 or later

## How to Reproduce the Issue

1.  **Start a Pulsar 4.0.8 standalone broker:**

    ```bash
    docker run -it -p 6650:6650 apachepulsar/pulsar:4.0.8 bin/pulsar standalone
    ```

2.  **Run the client applications:**

    You can run either the `pulsar-client-3-0-1` or `pulsar-client-4-0` application. Both will work correctly with the 4.0.8 broker.

    To run the 3.0.1 client:

    ```bash
    ./gradlew :pulsar-client-3-0-1:run
    ```

    To run the 4.0 client:

    ```bash
    ./gradlew :pulsar-client-4-0:run
    ```

3.  **Stop the 4.0.8 broker.**

4.  **Start a Pulsar 4.1.2 standalone broker:**

    ```bash
    docker run -it -p 6650:6650 apachepulsar/pulsar:4.1.2 bin/pulsar standalone
    ```

5.  **Run either of the client applications again.**

## Expected Outcome

When running against the 4.1.2 broker, the producer will fail to be created and you will see an exception in the broker logs similar to the following:

```
org.apache.pulsar.broker.service.schema.exceptions.InvalidSchemaDataException: Invalid schema definition data for PROTOBUF schema
```

This demonstrates the breaking change in schema handling introduced in Pulsar 4.1.x.
