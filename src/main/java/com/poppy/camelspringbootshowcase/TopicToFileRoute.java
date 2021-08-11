package com.poppy.camelspringbootshowcase;

import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TopicToFileRoute extends RouteBuilder {

    public static final String TOPICS = TopicToTopicRoute.FROM_TOPIC + "," + TopicToTopicRoute.TO_TOPIC;

    @Override
    public void configure() {
        from("kafka:" + TOPICS + "?groupId=camel-spring-boot-showcase-file-producer")
                .setHeader(Exchange.FILE_NAME, simple("${headers['kafka.TOPIC']}/${headers['kafka.PARTITION']}/${headers['kafka.OFFSET']}-${headers['kafka.KEY']}"))
                .log("Message read in topic to route with headers ${headers}")
                .process(this::formatBody)
                .to("file:data");
    }

    private void formatBody(Exchange exchange) {
        var message = new StringBuilder();
        message.append("key: ").append(exchange.getExchangeId()).append("\n");
        separator(message);
        for (String key : exchange.getIn().getHeaders().keySet()) {
            String value = exchange.getIn().getHeader(key, String.class);
            String headerKey = key != null ? key : "no-header-key";
            message.append(headerKey).append(" - ")
                    .append(Optional.ofNullable(value).orElse("no-header-value"))
                    .append("\n");
        }
        separator(message);
        message.append(exchange.getIn().getBody(String.class));
        message.append("\n");
        exchange.getIn().setBody(message.toString());
    }

    private void separator(StringBuilder message) {
        message.append("\n");
        message.append("--------------------------------------------------------------------------------- \n");
        message.append("\n");
    }
}
