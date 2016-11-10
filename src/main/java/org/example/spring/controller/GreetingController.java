package org.example.spring.controller;

import org.example.spring.request.AbstractRequest;
import org.example.spring.request.GoodbyeRequest;
import org.example.spring.response.GoodbyeResponse;
import org.example.spring.response.HelloResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")//value specifies the base url for our REST calls
public class GreetingController {

    //single endpoint accepting an abstract object hence allowing the user to send a json request
    // representing a java object that extends AbstractRequest
    @RequestMapping(value =  "/greeting",  method = RequestMethod.POST)
    public ResponseEntity<Object> greeting(@RequestBody AbstractRequest abstractRequest) {

        String type = abstractRequest.getClass().getTypeName() ;
        Object res;

        switch (type){
            case "org.example.spring.request.GoodbyeRequest" :
                GoodbyeResponse goodbyeResponse  = new GoodbyeResponse();
                goodbyeResponse.setMessage(((GoodbyeRequest) abstractRequest).getFullname());
                res = goodbyeResponse;
                break;
            default:
                HelloResponse helloResponse = new HelloResponse();
                helloResponse.setMessage(abstractRequest.getName());
                res = helloResponse;
                break;
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
