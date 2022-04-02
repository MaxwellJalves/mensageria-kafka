package br.com.integrador.consumer;

import java.io.IOException;

public class RunConsumerEvent {
    public static void main(String[] args) throws IOException {
        var consumer = new ConsumerEvent();
        consumer.exec();
    }
}
