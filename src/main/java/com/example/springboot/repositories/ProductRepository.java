package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.model.ProductModel;
import java.util.UUID;

public interface ProductRepository extends  JpaRepository<ProductModel, UUID>{

}
