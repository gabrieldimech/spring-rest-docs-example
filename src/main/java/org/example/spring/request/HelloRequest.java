package org.example.spring.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by gabri on 10/7/2016.
 */
@JsonDeserialize(as = HelloRequest.class)
public class HelloRequest extends AbstractRequest {

 /*   public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    String fullname;*/

}
