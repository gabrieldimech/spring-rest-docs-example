package org.example.spring.response;

/**
 * Created by gabri on 10/6/2016.
 */
public class GoodbyeResponse {


    public GoodbyeResponse() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String name) {
        this.message = "goodbye " + name;
    }

    String message;




}
