package com.valkyrie.authentication_service.model;

import org.springframework.http.HttpStatus;

public class Store<Instance> {
    private final HttpStatus status;
    private final Instance instance;

    private Store(HttpStatus status, Instance instance) {
        this.instance = instance; this.status = status;
    }

    public static <Instance> Store<Instance> initialize(HttpStatus status, Instance instance) {
        return new Store<>(status, instance);
    }

    public HttpStatus getStatus() {return status;}

    public Instance getInstance() {return instance;}
}
