package br.com.integrador.consumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConsumerEvent {

    private final KafkaConsumer<String, String> consumer;

    public ConsumerEvent() throws IOException {
        this.consumer = createConsumerEvent();
    }

    private KafkaConsumer<String, String> createConsumerEvent() throws IOException {

        if (this.consumer != null) {
            return this.consumer;
        }

        return new KafkaConsumer<String, String>(getProperties());
    }

    private Properties getProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/consumer.properties"));
        return properties;
    }


    public void exec() {
        List<String> topicos = new ArrayList<>();
        topicos.add("INTEGRADOR-WEB");
        this.consumer.subscribe(topicos);

        System.out.println("Inicializando [Consumer] ...");

        while (true) {
            var records = this.consumer.poll(Duration.ofMillis(100));
            for (var rec : records) {
                System.out.printf("Chave : %s | Valor: %s | offset : %d \n", rec.key(), rec.value(), rec.offset());
            }
        }
    }

}
