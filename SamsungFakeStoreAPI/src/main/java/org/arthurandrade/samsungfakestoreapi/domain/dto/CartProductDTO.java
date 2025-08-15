package org.arthurandrade.samsungfakestoreapi.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.arthurandrade.samsungfakestoreapi.domain.entity.AbstractObject;
import org.arthurandrade.samsungfakestoreapi.domain.entity.CartProduct;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractDTO;


@Getter
@Setter
public class CartProductDTO extends AbstractObject implements IAbstractDTO<CartProduct> {

    private Long id;

    private CartDTO cart;

    private ProductDTO product;

    private Integer quantity = 1;

    @Override
    public CartProduct toEntity() {
        CartProduct ret = new CartProduct();
        ret.setId(this.id);
        ret.setUuidCheck(getUuidCheck());
        ret.setProduct(this.product.toEntity());
        ret.setQuantity(this.quantity);
        return ret;
    }

    @Override
    public Long getId() {
        return 0L;
    }
}
