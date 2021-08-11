package com.poppy.camelspringbootshowcase;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Regularly push a new message to a kafka topic
 */
@Component
public class HttpKafkaRoute extends RouteBuilder {

    public static final AtomicInteger KEY_GENERATOR = new AtomicInteger();
    public static final String OUTPUT_TOPIC = "input-topic";

    @Override
    public void configure() {
        from("netty-http:http:0.0.0.0:8080/hello")
                .setBody().constant("Hi!")
                .log("Message generated with headers ${headers}")
                .setHeader(KafkaConstants.KEY, simple("${bean:KeyGenerator.nextKey}"))
                .to("kafka:" + OUTPUT_TOPIC);
    }
}
