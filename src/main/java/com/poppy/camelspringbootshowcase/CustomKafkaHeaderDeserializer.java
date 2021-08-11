package com.poppy.camelspringbootshowcase;

import org.apache.camel.component.kafka.serde.KafkaHeaderDeserializer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Set;

@Component("CustomKafkaHeaderDeserializer")
public class CustomKafkaHeaderDeserializer implements KafkaHeaderDeserializer {

    private static final Set<String> STRING_HEADERS = Set.of("traceparent");

    @Override
    public Object deserialize(String key, byte[] value) {
        if (STRING_HEADERS.contains(key)) {
            return new String(value, StandardCharsets.UTF_8);
        } else {
            return value;
        }
    }

}
