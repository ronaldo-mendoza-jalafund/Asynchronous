package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Subscriber {
    private static final String EXCHANGE_NAME = "logs";  // Nombre del exchange

    public static void main(String[] argv) throws Exception {
        // Crear una nueva conexión a RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");  // Conectar al servidor RabbitMQ en localhost
        factory.setPort(5672);  // Puerto de conexión
        factory.setUsername("admin");  // Usuario de conexión
        factory.setPassword("admin");  // Contraseña de conexión
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Declarar un exchange de tipo fanout
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // Crear una cola temporal que será utilizada para recibir los mensajes
        String queueName = channel.queueDeclare().getQueue();

        // Vincular la cola al exchange
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // Crear un callback para recibir los mensajes
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");  // Mostrar mensaje recibido
        };

        // Comenzar a consumir mensajes
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
