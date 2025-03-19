package com.example.webkh.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webkh.Model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
