package org.arthurandrade.samsungfakestorefront.services;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public abstract class AbstractService {

    protected static final String BASE_URL = "http://localhost:3000";

    protected Client client = ClientBuilder.newClient();

    // Base URL do serviço
    protected abstract String getServiceUrl();

    public boolean ping() {
        Response response = client.target(getServiceUrl() + "/ping")
                .request(MediaType.APPLICATION_JSON)
                .get();
        return response.getStatus() == 200;
    }
}
