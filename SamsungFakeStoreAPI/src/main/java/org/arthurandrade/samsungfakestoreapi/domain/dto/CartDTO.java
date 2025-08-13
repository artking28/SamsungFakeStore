package org.arthurandrade.samsungfakestoreapi.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.arthurandrade.samsungfakestoreapi.domain.entity.AbstractObject;
import org.arthurandrade.samsungfakestoreapi.domain.entity.Cart;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractDTO;

@Getter
@Setter
public class CartDTO extends AbstractObject implements IAbstractDTO<Cart> {

    private Long id;

    @Override
    public Cart toEntity() {
        Cart ret = new Cart();
        ret.setId(id);
        return ret;
    }
}
