package com.springbootmvc.tutorials.demo.springBootMVC.repository;

import com.springbootmvc.tutorials.demo.springBootMVC.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
