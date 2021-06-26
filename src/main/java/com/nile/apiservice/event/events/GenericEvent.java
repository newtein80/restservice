package com.nile.apiservice.event.events;

import lombok.Getter;

@Getter
public class GenericEvent <T> {
    private T result;
    protected boolean success;

    public GenericEvent(T result, boolean success) {
        this.result = result;
        this.success = success;
    }
}
