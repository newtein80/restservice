package com.nile.apiservice.event.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class GenericEventPublisher<T> {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(final T message, final boolean success) {
        System.out.println("Publishing generic event. ");
        GenericEvent<T> genericEvent = new GenericEvent<>(message, success);
        applicationEventPublisher.publishEvent(genericEvent);
    }
}