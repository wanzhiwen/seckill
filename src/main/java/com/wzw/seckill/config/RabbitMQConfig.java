package com.wzw.seckill.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wanzhiwen
 * @Date 2022/4/12
 */
@Configuration
public class RabbitMQConfig {
    private static final String QUEUE01 = "seckillQueue";
    private static final String EXCHANGE = "seckillExchange";


    @Bean
    public Queue queue() {
        return new Queue(QUEUE01);
    }


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with("seckill.#");
    }


}
