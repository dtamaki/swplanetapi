package com.dtamaki.swplanetapi.responses;

import java.util.List;

public class Response<T>
{
    private T data;
    private List<String> errors;
    
    public Response(T data) {
        this.data = data;
    }
    
    public Response(List<String> errors) {
        this.errors = errors;
    }
    
    public T getData() {
        return this.data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public List<String> getErrors() {
        return this.errors;
    }
    
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
