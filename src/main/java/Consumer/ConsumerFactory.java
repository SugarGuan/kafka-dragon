package Consumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

public class ConsumerFactory {
    public static KafkaConsumer<String, String> getInstance (){
        Properties consumerProp = new Properties();
        consumerProp.put("bootstrap.servers", "dn1:9092, :9092;dn3:9092");
        consumerProp.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProp.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProp.put("group.id", "CountryCounter");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(consumerProp);
        return consumer;
    }
}
