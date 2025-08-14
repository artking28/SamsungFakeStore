package org.arthurandrade.samsungfakestoreapi.controller;

import org.arthurandrade.samsungfakestoreapi.domain.dto.CartDTO;
import org.arthurandrade.samsungfakestoreapi.domain.entity.Cart;
import org.arthurandrade.samsungfakestoreapi.service.IService;
import org.arthurandrade.samsungfakestoreapi.service.CartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController extends AbstractController<Cart, CartDTO> {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    protected IService<Cart, CartDTO> getService() {
        return this.cartService;
    }
}
