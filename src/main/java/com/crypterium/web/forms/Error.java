package com.crypterium.web.forms;

/**
 *
 *
 * @author truesrc
 * @since 27.03.2019
 */
public class Error extends Result {
    private final String error;

    public Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
