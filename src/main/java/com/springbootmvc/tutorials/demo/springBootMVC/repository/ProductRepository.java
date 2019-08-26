package com.springbootmvc.tutorials.demo.springBootMVC.repository;

import com.springbootmvc.tutorials.demo.springBootMVC.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
