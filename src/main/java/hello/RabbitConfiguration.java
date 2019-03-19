package hello;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

public class RabbitConfiguration {
    Logger logger = Logger.getLogger(RabbitConfiguration.class);

    //configure RabbitMQ
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("user");
        connectionFactory.setPassword("password");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue myQueueT1() {
        return new Queue(Application.QUEUE_T_1);
    }

    @Bean
    public Queue myQueueT2() {
        return new Queue(Application.QUEUE_T_2);
    }

    @Bean
    public Queue myQueueExampleT2() {
        return new Queue(Application.QUEUE_EX_T_2);
    }

    @Bean
    public FanoutExchange fanoutExchangeA(){
        return new FanoutExchange(Application.EXCHANGE_EX_T_2);
    }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(myQueueT1()).to(fanoutExchangeA());
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(myQueueT2()).to(fanoutExchangeA());
    }

}
