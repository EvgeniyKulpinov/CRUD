package com.kulcorp.CRUD.service;

import com.kulcorp.CRUD.model.User;

import java.util.List;

public interface UserService {

    boolean saveUser(User user);

    void userUpdate(User user);

    User getUserById(long id);

    List<User> getAll();

    void deleteById(Long id);

    User getUserByName(String s);
}
