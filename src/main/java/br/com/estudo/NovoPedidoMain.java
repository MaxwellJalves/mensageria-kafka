package br.com.estudo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class NovoPedidoMain {

    private static final String LOCALHOST = "localhost:9091";

    public static void main(String[] args) throws ExecutionException, IOException {

        var produtor = new KafkaProducer<Integer, String>(properties());


        Map<Integer, String> publicacoes = new HashMap<>();
        publicacoes.put(1, "Guitarra");
        publicacoes.put(2, "Teclado");
        publicacoes.put(3, "Bateria");
        publicacoes.put(4, "Sax Sofone");


        for (var pub : publicacoes.entrySet()) {

            try {
                produtor.send(new ProducerRecord<Integer, String>("ECOMMERCE", pub.getKey(), pub.getValue()), (data, ex) -> {
                    if (ex != null) {
                        ex.printStackTrace();
                        return;
                    }
                    System.out.printf("Mensagem enviada: %s  :: Partição :%d offset:  %d: \n ", data.topic(), data.partition(), data.offset());

                }).get();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, LOCALHOST);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }



}

