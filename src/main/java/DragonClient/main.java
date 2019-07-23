package DragonClient;

import Producer.ProducerFactory;
import Consumer.ConsumerFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Collections;


class main {
    public static void main(String[] args){
        System.out.println("您正在运行kafka-dragon系统的测试程序");
        System.out.println("程序将测试当前功能的有效性");

        producerThread pt = new producerThread();
        consumerThread ct = new consumerThread();
        ct.start();
        pt.start();

//        Properties consumerProp = new Properties();
//        consumerProp.put("bootstrap.servers", "dn2:9092,dn1:9092;dn3:9092");
//        consumerProp.put("group.id", "mytestID");
//        consumerProp.put("enable.auto.commit", "true");
//        consumerProp.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        consumerProp.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

//        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(consumerProp);
//        kafkaConsumer.subscribe(Arrays.asList("topicForTest"));
//
//        while (true) {
//            System.out.println("Waiting data flow");
//            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(1000);
//            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
//                System.out.printf("offset = %d , value = %s" , consumerRecord.offset(), consumerRecord.value());
//                System.out.println();
//            }

//        }

// bin/kafka-verifiable-producer.sh --topic CustomerCountry --max-messages 200 --broker-list localhost:9092
    }
}

class producerThread extends Thread {
    @Override
    public void run(){
        KafkaProducer<String, String> producer = ProducerFactory.getInstance();


        try {
            for (int i = 0; i < 100 ; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<String, String>(
                        "CustomerCountry",
                        "Precision Products",
                        "json" + i
                );
                producer.send(record);
                System.out.println("-------------------S-------------------");
                System.out.println("Sending message:    Value = json" + i);
                System.out.println("-------------------S--------------------");
                Thread.sleep(1000);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class consumerThread extends Thread {
    @Override
    public void run () {
        KafkaConsumer<String, String> consumer = ConsumerFactory.getInstance();
        consumer.subscribe(Collections.singletonList("CustomerCountry"));
//        System.out.println("Consumer created successfully");
        try {
            while (true) {
//                System.out.println("Entered Consumer generation branch");
                System.out.println("-------------------R-------------------");
                ConsumerRecords<String, String> records = consumer.poll(1000);
//                System.out.println("Completed Consumer Record generation");
                for (ConsumerRecord<String, String> record : records) {

                    System.out.println("Received : " + record.value());

                }
                System.out.println("-------------------R-------------------");

            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("Consumer closed");
            consumer.close();
        }

    }
}