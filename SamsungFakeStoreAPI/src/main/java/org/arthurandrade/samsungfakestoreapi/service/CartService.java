package org.arthurandrade.samsungfakestoreapi.service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.arthurandrade.samsungfakestoreapi.domain.dto.CartDTO;
import org.arthurandrade.samsungfakestoreapi.domain.entity.Cart;
import org.arthurandrade.samsungfakestoreapi.domain.exceptions.BusinessRuleException;
import org.arthurandrade.samsungfakestoreapi.repository.ICartRepository;
import org.springframework.stereotype.Service;

@EqualsAndHashCode(callSuper = true)
@Data
@Service
public class CartService extends AbstractService<Cart, CartDTO> {

    private final ICartRepository repository;

    public CartService(ICartRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void beforeCreate(Cart cart) {
    }

    @Override
    protected void beforeSave(Cart cart) {
    }

    public void commonValidations(Cart cart) throws BusinessRuleException {

    }
}
