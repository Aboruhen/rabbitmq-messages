package hello;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static hello.Application.EXCHANGE_EX_T_2;

@Controller
public class SampleController {

    Logger logger = Logger.getLogger(SampleController.class);

    @Autowired
    RabbitTemplate template;

    @RequestMapping("/queue")
    @ResponseBody
    String queue1() {
        logger.info("Emit to queue");
        for(int i = 0;i<10;i++)
            template.convertAndSend(Application.QUEUE_EX_T_2,"Message " + i);
        return "Emit to queue";
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Empty mapping";
    }

    @RequestMapping("/emit")
    @ResponseBody
    String emit() {
        logger.info("Emit to " + EXCHANGE_EX_T_2);
        template.setExchange(EXCHANGE_EX_T_2);
        template.convertAndSend("Fanout message");
        return "Emit to " + EXCHANGE_EX_T_2;
    }

}
