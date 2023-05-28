package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAddressByCustomerIdAndDeleted(Long customerId,String deleted);
}
