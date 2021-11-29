package com.example.sia.tacocloud.web.repository;

import com.example.sia.tacocloud.web.pojo.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Coulomb
 * @since 2021/11/5 16:32
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
