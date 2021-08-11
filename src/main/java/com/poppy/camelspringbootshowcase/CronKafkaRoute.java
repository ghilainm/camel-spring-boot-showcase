package com.poppy.camelspringbootshowcase;

import org.apache.camel.builder.RouteBuilder;

/**
 * Regularly push a new message to a kafka topic
 */
@SuppressWarnings("unused")
public class CronKafkaRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("cron:tab?schedule=0/3 * * * * ?")
                .setBody().constant("event")
                .log("Message generated with headers ${headers}")
                .to("kafka:" + TopicToFileRoute.TOPICS);
    }
}
