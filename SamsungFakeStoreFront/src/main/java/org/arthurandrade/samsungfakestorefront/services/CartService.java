package org.arthurandrade.samsungfakestorefront.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.arthurandrade.samsungfakestoreapi.domain.dto.CartDTO;

@ApplicationScoped
public class CartService extends EntityService<CartDTO> {

    @Override
    protected void prepareSave(CartDTO obj) {

    }

    @Override
    protected String getServiceUrl() {
        return "";
    }
}
