package br.com.dicasdeumdev.api.services.impl;

import br.com.dicasdeumdev.api.domain.User;

public interface UserService {
    User findById(Long id);

    Object findAll();
}