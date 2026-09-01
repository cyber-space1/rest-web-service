package com.example.restservice;

import java.util.Objects;

public record Greeting(long id, String contents) {
    public Greeting {
        Objects.requireNonNull(contents);
    }
}
