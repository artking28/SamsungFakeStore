package org.arthurandrade.samsungfakestoreapi.domain.noData;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GenericResponse {

    private Object result = null;

    private Boolean success = true;

    private List<JsonMessage> messages = new ArrayList<>();

    public GenericResponse(){
    }

    public GenericResponse(boolean success){
        this.success = success;
    }

    public GenericResponse(Object result) {
        this.success = true;
        this.result = result;
    }

    public void addMessage(JsonMessage jsonMessage){
        messages.add(jsonMessage);
    }
}
