package com.poorjar.swagger.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastname(String lastname);
}
