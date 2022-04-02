package br.com.integrador.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ExecuteProducer {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {


        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/producer.properties"));
        System.out.println("Executando properties");
        properties.entrySet().forEach(System.out::println);


        var producer = new KafkaProducer<String, String>(properties);


        var response = producer.send(new ProducerRecord<String, String>(properties.getProperty("topico"), "Front End", "React Next Vue Angular "),
                (registro, two) -> {
                    try {
                        System.out.printf("Registro de número  %s , incluido no tópico %f", registro.topic(), registro.offset());
                    } catch (Exception e) {

                        System.out.println(e.getMessage());
                    }
                }).get();
        producer.flush();
        producer.close();
        System.out.println(response.partition() + "offset :" + response.offset());
    }
}

