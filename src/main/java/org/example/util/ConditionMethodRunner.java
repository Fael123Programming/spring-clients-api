package org.example.util;

import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ConditionMethodRunner {
    public static void runIfStringIsNotNullAndNotEmpty(String string, Consumer<String> consumer) {
        if (string != null && !string.isEmpty()) {
            consumer.accept(string);
        }
    }
}
