package com.juliasoft.rest;

public class RestResult<T> {
    public RestResult(T data) {
        this.data = data;
    }

    public final T data;
}
