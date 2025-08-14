package org.arthurandrade.samsungfakestorefront.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.arthurandrade.samsungfakestoreapi.domain.dto.UserDTO;

@ApplicationScoped
public class UserService extends EntityService<UserDTO> {

    @Override
    protected void prepareSave(UserDTO obj) {

    }

    @Override
    protected String getServiceUrl() {
        return "users";
    }
}
