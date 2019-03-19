package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@ComponentScan
@Import(RabbitConfiguration.class)
public class Application {

    public static final String QUEUE_T_1 = "queueT1";
    public static final String QUEUE_T_2 = "queueT2";
    public static final String QUEUE_EX_T_2 = "query-example-t-2";
    public static final String EXCHANGE_EX_T_2 = "exchange-example-t-3";

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
