package org.arthurandrade.samsungfakestorefront.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.arthurandrade.samsungfakestoreapi.domain.dto.ProductDTO;

@ApplicationScoped
public class ProductService extends EntityService<ProductDTO> {

    @Override
    protected void prepareSave(ProductDTO obj) {

    }

    @Override
    protected String getServiceUrl() {
        return "/products";
    }
}
