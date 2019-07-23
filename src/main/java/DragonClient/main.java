package DragonClient;

import Producer.JSONParser;
import Producer.ProducerFactory;
import Consumer.ConsumerFactory;
import Producer.User;
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
    }
}

class producerThread extends Thread {
    @Override
    public void run(){
        KafkaProducer<String, String> producer = ProducerFactory.getInstance();

        User Liu = new User("Wang5",38);
        User Xin = new User("Jelly",250);

        try {
            for (int i = 0; i < 10 ; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<String, String>(
                        "CustomerCountry",
                        "Precision Products",
                        JSONParser.toJSONString(Liu)
                );
                producer.send(record);
//                System.out.println("-------------------SS------------------");
                System.out.println("Send:" + JSONParser.toJSONString(Liu));
//                System.out.println("-------------------SF------------------");
                Thread.sleep(1000);

//                ProducerRecord<String, String> record2 = new ProducerRecord<String, String>(
//                        "CustomerCountry",
//                        "Precision Products",
//                        JSONParser.toJSONString(Xin)
//                );
//                producer.send(record2);
////                System.out.println("-------------------SS------------------");
//                System.out.println("Send: " +JSONParser.toJSONString(Xin));
////                System.out.println("-------------------SF------------------");
//                Thread.sleep(1000);



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
//                System.out.println("-------------------RS------------------");
                ConsumerRecords<String, String> records = consumer.poll(1000);
//                System.out.println("Completed Consumer Record generation");
                for (ConsumerRecord<String, String> record : records) {

                    System.out.println("Received : " + record.value());

                }
//                System.out.println("-------------------RF------------------");

            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("Consumer closed");
            consumer.close();
        }

    }
}