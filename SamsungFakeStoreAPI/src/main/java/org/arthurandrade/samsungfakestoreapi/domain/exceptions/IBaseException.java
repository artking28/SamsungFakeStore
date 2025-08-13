package org.arthurandrade.samsungfakestoreapi.domain.exceptions;


import org.arthurandrade.samsungfakestoreapi.domain.enums.ResponseCodeEnum;
import org.arthurandrade.samsungfakestoreapi.domain.noData.JsonMessage;

import java.util.*;

public interface IBaseException {

    void addMessage(String message, ResponseCodeEnum type);

    void addMessage(String message, ResponseCodeEnum type, Map<String, Object> parameters);

    void addMessages(JsonMessage...messages);

    List<JsonMessage> getMessages();
}
