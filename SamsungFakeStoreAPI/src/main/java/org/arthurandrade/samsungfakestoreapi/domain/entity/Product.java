package org.arthurandrade.samsungfakestoreapi.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.arthurandrade.samsungfakestoreapi.domain.dto.ProductDTO;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractEntity;
import org.hibernate.Hibernate;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends AbstractObject implements IAbstractEntity<Product, ProductDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "category", unique = true, nullable = false)
    private String category;

    @Column(name = "image", nullable = false)
    private String image;

    @OneToMany(mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CartProduct> cartProducts = new ArrayList<>();

    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    public ProductDTO toDTO() {
        ProductDTO ret = new ProductDTO();
        ret.setId(this.id);
        ret.setUuidCheck(getUuidCheck());
        ret.setTitle(this.title);
        ret.setPrice(this.price);
        ret.setDescription(this.description);
        ret.setCategory(this.category);
        ret.setImage(this.image);
        return ret;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> thisClass = Hibernate.getClass(this);
        Class<?> otherClass = Hibernate.getClass(o);
        if (!thisClass.equals(otherClass)) return false;
        Product other = (Product) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }
}
