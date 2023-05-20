package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerStatusRepository extends JpaRepository<CustomerStatus,Long> {

    CustomerStatus findCustomerStatusByName(String name);

    List<CustomerStatus> findAllCustomerStatus();

}
