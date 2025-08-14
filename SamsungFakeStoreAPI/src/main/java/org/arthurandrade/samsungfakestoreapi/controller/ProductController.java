package org.arthurandrade.samsungfakestoreapi.controller;

import org.arthurandrade.samsungfakestoreapi.domain.dto.ProductDTO;
import org.arthurandrade.samsungfakestoreapi.domain.entity.Product;
import org.arthurandrade.samsungfakestoreapi.service.IService;
import org.arthurandrade.samsungfakestoreapi.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController extends AbstractController<Product, ProductDTO> {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected IService<Product, ProductDTO> getService() {
        return this.productService;
    }
}
