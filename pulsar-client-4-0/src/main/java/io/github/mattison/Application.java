package io.github.mattison;


import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

public class Application {


    public static void main(String[] args) throws PulsarClientException {
        final PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        final Producer<DataRecordOuterClass.DataRecord> producer =
                client.newProducer(Schema.PROTOBUF(DataRecordOuterClass.DataRecord.class))
                        .topic("test")
                        .create();
        client.close();
    }
}
