package org.arthurandrade.samsungfakestoreapi.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.arthurandrade.samsungfakestoreapi.domain.entity.AbstractObject;
import org.arthurandrade.samsungfakestoreapi.domain.entity.Product;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractDTO;

@Getter
@Setter
public class ProductDTO extends AbstractObject implements IAbstractDTO<Product> {

    private Long id;

    private String title;

    private Float price;

    private String description;

    private String category;

    private String image;

    @Override
    public Product toEntity() {
        Product ret = new Product();
        ret.setId(id);
        ret.setUuidCheck(getUuidCheck());
        ret.setTitle(this.title);
        ret.setPrice(this.price);
        ret.setDescription(this.description);
        ret.setCategory(this.category);
        ret.setImage(this.image);
        return ret;
    }
}
