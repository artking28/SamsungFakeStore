package org.arthurandrade.samsungfakestoreapi.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.arthurandrade.samsungfakestoreapi.domain.dto.UserDTO;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractEntity;
import org.hibernate.Hibernate;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AbstractObject implements IAbstractEntity<User, UserDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private Date createdAt = new Date();

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Cart> carts  = new ArrayList<>();

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public UserDTO toDTO() {
        UserDTO ret = new UserDTO();
        ret.setId(this.id);
        ret.setName(this.getName());
        ret.setEmail(this.getEmail());
        ret.setCreatedAt(this.getCreatedAt());
        ret.setEmail(this.getEmail());
        ret.setUuidCheck(getUuidCheck());
        if(this.carts != null) {
            ret.setCarts(this.carts.stream().map(Cart::toDTO).toList());
        }
        return ret;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> thisClass = Hibernate.getClass(this);
        Class<?> otherClass = Hibernate.getClass(o);
        if (!thisClass.equals(otherClass)) return false;
        User other = (User) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }
}
