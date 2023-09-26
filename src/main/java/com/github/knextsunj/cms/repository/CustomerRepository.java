package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>
{

    Customer findByName(String name);

    List<Customer> findAllByActiveStatusAndDeleted(String activeStatus,String deleted);
}
