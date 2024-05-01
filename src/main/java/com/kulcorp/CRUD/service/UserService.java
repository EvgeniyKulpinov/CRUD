package com.kulcorp.CRUD.service;

import com.kulcorp.CRUD.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    User getUserById(long id);

    List<User> getAll();

    void deleteById(Long id);
}
