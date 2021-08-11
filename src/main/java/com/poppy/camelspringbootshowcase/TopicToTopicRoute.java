package com.poppy.camelspringbootshowcase;

import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static com.poppy.camelspringbootshowcase.HttpKafkaRoute.OUTPUT_TOPIC;

@Component
@RequiredArgsConstructor
public class TopicToTopicRoute extends RouteBuilder {

    public static final String FROM_TOPIC = OUTPUT_TOPIC;
    public static final String TO_TOPIC = "output-topic";

    @Override
    public void configure() {
        from("kafka:"+ FROM_TOPIC  + "?groupId=camel-spring-boot-showcase-topic-producer")
                .to("kafka:"+ TO_TOPIC);
    }
}
