package org.arthurandrade.samsungfakestoreapi.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.arthurandrade.samsungfakestoreapi.domain.dto.CartProductDTO;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractEntity;

@Getter
@Setter
@Entity
@Table(name = "cartProducts")
public class CartProduct extends AbstractObject implements IAbstractEntity<CartProduct, CartProductDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity = 1;

    @Override
    public CartProductDTO toDTO() {
        CartProductDTO ret = new CartProductDTO();
        ret.setId(this.id);
        ret.setUuidCheck(getUuidCheck());
        ret.setProduct(this.product.toDTO());
        ret.setQuantity(this.quantity);
        return ret;
    }
}
