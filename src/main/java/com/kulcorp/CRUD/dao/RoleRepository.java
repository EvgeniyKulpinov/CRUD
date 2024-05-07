package com.kulcorp.CRUD.dao;

import com.kulcorp.CRUD.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
