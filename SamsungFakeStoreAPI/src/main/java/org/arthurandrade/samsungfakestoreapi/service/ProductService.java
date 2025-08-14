package org.arthurandrade.samsungfakestoreapi.service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.arthurandrade.samsungfakestoreapi.domain.dto.ProductDTO;
import org.arthurandrade.samsungfakestoreapi.domain.entity.Product;
import org.arthurandrade.samsungfakestoreapi.domain.exceptions.BusinessRuleException;
import org.arthurandrade.samsungfakestoreapi.repository.IProductRepository;
import org.springframework.stereotype.Service;

@EqualsAndHashCode(callSuper = true)
@Data
@Service
public class ProductService extends AbstractService<Product, ProductDTO> {

    private final IProductRepository repository;

    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void beforeCreate(Product product) {
    }

    @Override
    protected void beforeSave(Product product) {
    }

    public void commonValidations(Product product) throws BusinessRuleException {

    }
}
