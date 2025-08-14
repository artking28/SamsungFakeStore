package org.arthurandrade.samsungfakestoreapi.controller;

import org.arthurandrade.samsungfakestoreapi.domain.dto.UserDTO;
import org.arthurandrade.samsungfakestoreapi.domain.entity.User;
import org.arthurandrade.samsungfakestoreapi.service.IService;
import org.arthurandrade.samsungfakestoreapi.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractController<User, UserDTO> {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected IService<User, UserDTO> getService() {
        return this.userService;
    }
}
