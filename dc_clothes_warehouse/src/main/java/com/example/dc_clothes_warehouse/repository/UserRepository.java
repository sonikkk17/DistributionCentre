package com.example.dc_clothes_warehouse.repository;

import com.example.dc_clothes_warehouse.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);
}
