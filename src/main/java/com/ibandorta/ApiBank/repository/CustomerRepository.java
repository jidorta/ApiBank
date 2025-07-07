package com.ibandorta.ApiBank.repository;

import com.ibandorta.ApiBank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer,Long> {
}
