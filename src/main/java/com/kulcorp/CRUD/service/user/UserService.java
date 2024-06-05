package com.kulcorp.CRUD.service.user;

import com.kulcorp.CRUD.model.User;

import java.util.List;

public interface UserService {

    boolean saveUser(User user);

    boolean userUpdate(User user);

    User getUserById(long id);

    List<User> getAll();

    void deleteById(Long id);

}
