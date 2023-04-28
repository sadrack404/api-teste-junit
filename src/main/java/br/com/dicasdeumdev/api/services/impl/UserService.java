package br.com.dicasdeumdev.api.services.impl;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;

public interface UserService {
    User findById(Long id);

    Object findAll();

    User create(UserDTO obj);

    void findByEmail(UserDTO obj);

    User update(UserDTO obj);
}