package org.example.spring.response;

/**
 * Created by gabri on 10/6/2016.
 */
public class HelloResponse {


    public HelloResponse() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String name) {
        this.message = "hello " + name;
    }

    String message;


}
