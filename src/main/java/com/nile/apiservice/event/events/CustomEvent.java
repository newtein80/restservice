package com.nile.apiservice.event.events;

import org.springframework.context.ApplicationEvent;

// ! https://daddyprogrammer.org/post/14625/spring-boot-events/
public class CustomEvent extends ApplicationEvent {
    private String message;

    public CustomEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}