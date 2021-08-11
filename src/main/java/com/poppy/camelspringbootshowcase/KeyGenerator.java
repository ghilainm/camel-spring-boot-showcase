package com.poppy.camelspringbootshowcase;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component("KeyGenerator")
public class KeyGenerator {

    public static final AtomicInteger KEY_GENERATOR = new AtomicInteger();

    int nextKey() {
        return KEY_GENERATOR.get();
    }
}
