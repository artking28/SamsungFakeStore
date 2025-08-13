package org.arthurandrade.samsungfakestoreapi.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.arthurandrade.samsungfakestoreapi.domain.dto.CartDTO;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractEntity;
import org.hibernate.Hibernate;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart extends AbstractObject implements IAbstractEntity<Cart, CartDTO> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", updatable = false)
	private User user;

	public Cart() {
	}

	public Cart(Long id) {
		this.id = id;
	}

	public CartDTO toDTO() {
		CartDTO ret = new CartDTO();
		ret.setId(id);
		return ret;
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		Class<?> thisClass = Hibernate.getClass(this);
		Class<?> otherClass = Hibernate.getClass(o);
		if (!thisClass.equals(otherClass)) return false;
		Cart other = (Cart) o;
		return id != null && id.equals(other.id);
	}

	@Override
	public final int hashCode() {
		return Objects.hashCode(getId());
	}
}
