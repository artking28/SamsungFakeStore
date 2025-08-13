package org.arthurandrade.samsungfakestoreapi.domain.exceptions;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.arthurandrade.samsungfakestoreapi.domain.enums.ResponseCodeEnum;
import org.arthurandrade.samsungfakestoreapi.domain.noData.JsonMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.CONFLICT)
public class BusinessRuleException extends Exception implements IBaseException {

    private List<JsonMessage> messages = new ArrayList<>();

    public BusinessRuleException(String message, ResponseCodeEnum responseCodeEnum) {
        this.messages.add(new JsonMessage(message, responseCodeEnum));
    }

    public BusinessRuleException(JsonMessage...messages) {
        this.messages.addAll(List.of(messages));
    }

    public void addMessage(String message, ResponseCodeEnum type) {
        addMessages(new JsonMessage(message, type));
    }

    public void addMessage(String message, ResponseCodeEnum type, Map<String, Object> parameters) {
        addMessages(new JsonMessage(message, type, parameters));
    }

    public void addMessages(JsonMessage...messages) {
        this.messages.addAll(List.of(messages));
    }

}
