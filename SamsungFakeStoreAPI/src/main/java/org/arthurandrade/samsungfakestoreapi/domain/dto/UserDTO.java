package org.arthurandrade.samsungfakestoreapi.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.arthurandrade.samsungfakestoreapi.domain.entity.AbstractObject;
import org.arthurandrade.samsungfakestoreapi.domain.entity.User;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDTO extends AbstractObject implements IAbstractDTO<User> {

    private Long id;

    private String name;

    private Date date = new Date();

    private String email;

    private List<CartDTO> carts = new ArrayList<>();

    @Override
    public User toEntity() {
        User ret = new User();
        ret.setId(this.id);
        ret.setName(this.name);
        ret.setEmail(this.email);
        ret.setDate(this.date);
        ret.setUuidCheck(getUuidCheck());
        if(this.carts != null) {
            ret.setCarts(this.carts.stream().map(CartDTO::toEntity).toList());
        }
        return ret;
    }
}
