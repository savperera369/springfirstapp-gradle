package com.saviru;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository takes two values into Generic constructor, a class and data type of primary key
// can perform all CRUD operations on a class
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
