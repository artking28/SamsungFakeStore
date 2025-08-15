package org.arthurandrade.samsungfakestoreapi.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.arthurandrade.samsungfakestoreapi.domain.entity.AbstractObject;
import org.arthurandrade.samsungfakestoreapi.domain.entity.Cart;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CartDTO extends AbstractObject implements IAbstractDTO<Cart>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private UserDTO user;

    private Date date;

    private List<CartProductDTO> cartProducts = new ArrayList<>();

    @Override
    public Cart toEntity() {
        Cart ret = new Cart();
        ret.setId(id);
        ret.setDate(this.date);
        ret.setUser(user.toEntity());
        ret.setCartProducts(this.cartProducts.stream().map(CartProductDTO::toEntity).toList());
        return ret;
    }
}
