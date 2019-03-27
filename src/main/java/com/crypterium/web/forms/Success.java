package com.crypterium.web.forms;

/**
 *
 *
 * @author truesrc
 * @since 27.03.2019
 */
public class Success<T> extends Result {
    private final T value;

    public Success(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
