package com.example.webkh.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webkh.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
