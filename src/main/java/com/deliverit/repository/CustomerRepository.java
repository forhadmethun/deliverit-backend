package com.deliverit.repository;

import com.deliverit.entity.customer.Customer;
import com.deliverit.entity.order.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
