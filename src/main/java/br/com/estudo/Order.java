package br.com.estudo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;


public class Order {

    private static final String urlConnector = "localhost:9091";

    public static void main(String[] args) {

        var productor = new KafkaProducer<String, String>(getProperties());
        //Criando um novo topico
        productor.partitionsFor("NEW_TOPIC_MAXWELL");


        try{
            Future<RecordMetadata> send = productor.send(new ProducerRecord<String, String>("NEW_TOPIC_MAXWELL", "MAXWELL", "Adicionando produtos ao carrinho"));
            System.out.printf("Registro enviado para processamento...\nPartição | Topico = %s \nOffset = %d\n",send.get().partition(),send.get().offset());
        }catch (Exception e){
            System.out.println("Deu Ruim" + e.getCause());
        }

    }

    private static Properties getProperties() {


        var properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, urlConnector);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
