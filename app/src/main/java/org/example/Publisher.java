package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher {
    private static final String EXCHANGE_NAME = "logs";  // Nombre del exchange

    public static void main(String[] argv) throws Exception {
        // Crear una nueva conexión a RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");  // Conectar al servidor RabbitMQ en localhost
        factory.setPort(5672);  // Puerto de conexión
        factory.setUsername("admin");  // Usuario de conexión
        factory.setPassword("admin");  // Contraseña de conexión
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Declarar un exchange de tipo fanout
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            // Crear un mensaje
            String message = "Hello RabbitMQ!";

            // Publicar el mensaje al exchange
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");  // Mostrar mensaje enviado
        }
    }
}
