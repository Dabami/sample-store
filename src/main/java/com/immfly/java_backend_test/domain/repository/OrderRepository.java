package com.immfly.java_backend_test.domain.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.immfly.java_backend_test.domain.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {

}
