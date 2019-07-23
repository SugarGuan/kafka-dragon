package Producer;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

public class ProducerFactory {
    public static KafkaProducer<String, String> getInstance(){
        Properties producerProp = new Properties();
        producerProp.put("bootstrap.servers", "dn1:9092,dn2:9092,dn3:9092");
        producerProp.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProp.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return new KafkaProducer<String, String>(producerProp);
    }
}
