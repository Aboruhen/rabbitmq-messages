package hello;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;


@EnableRabbit
@Component
public class RabbitMqListener {

    Logger logger = Logger.getLogger(RabbitMqListener.class);
    Random random = new Random();

    @RabbitListener(queues = Application.QUEUE_EX_T_2)
    public void worker1(String message) throws InterruptedException {
        logger.info("worker 1 : " + message);
        Thread.sleep(100 * random.nextInt(20));
    }

    @RabbitListener(queues = Application.QUEUE_EX_T_2)
    public void worker2(String message) throws InterruptedException {
        logger.info("worker 2 : " + message);
        Thread.sleep(100 * random.nextInt(20));
    }

    @RabbitListener(queues = Application.QUEUE_T_1)
    public void workerEx1(String message) {
        logger.info("accepted on worker 1 : " + message);
    }

    @RabbitListener(queues = Application.QUEUE_T_2)
    public void workerEx2(String message) {
        logger.info("accepted on worker 2 : " + message);
    }

}
