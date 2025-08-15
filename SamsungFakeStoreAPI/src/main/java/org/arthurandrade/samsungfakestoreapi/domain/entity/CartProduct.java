package org.arthurandrade.samsungfakestoreapi.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "cartProducts")
public class CartProduct {

    @EmbeddedId
    private CartProductId id = new CartProductId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cartId")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @Column(nullable = false)
    private Integer quantity = 1;


    @Embeddable
    @Getter
    @Setter
    public static class CartProductId {

        private Long cartId;
        private Long productId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CartProductId)) return false;
            CartProductId that = (CartProductId) o;
            return Objects.equals(cartId, that.cartId) && Objects.equals(productId, that.productId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cartId, productId);
        }
    }
}
