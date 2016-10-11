package org.example.spring.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

/**
 * Created by gabri on 10/7/2016.
 */
//@JsonDeserialize(using = InstanceDeserializer.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GoodbyeRequest.class, name = "goodbye"),
        @JsonSubTypes.Type(value = HelloRequest.class, name = "hello")
})
public abstract class AbstractRequest implements Serializable {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String name;

}
