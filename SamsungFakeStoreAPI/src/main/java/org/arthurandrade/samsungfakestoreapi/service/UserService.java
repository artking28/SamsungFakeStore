package org.arthurandrade.samsungfakestoreapi.service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.arthurandrade.samsungfakestoreapi.domain.dto.UserDTO;
import org.arthurandrade.samsungfakestoreapi.domain.entity.User;
import org.arthurandrade.samsungfakestoreapi.domain.exceptions.BusinessRuleException;
import org.arthurandrade.samsungfakestoreapi.repository.IUserRepository;
import org.springframework.stereotype.Service;

@EqualsAndHashCode(callSuper = true)
@Data
@Service
public class UserService extends AbstractService<User, UserDTO> {

    private final IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void beforeCreate(User user) {
    }

    @Override
    protected void beforeSave(User user) {
    }

    public void commonValidations(User user) throws BusinessRuleException {

    }
}
