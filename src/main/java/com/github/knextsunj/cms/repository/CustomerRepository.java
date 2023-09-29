package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.Customer;
import org.apache.deltaspike.data.api.*;

import java.util.List;

@Repository
public interface CustomerRepository extends FullEntityRepository<Customer, Long>
{
    @Query(named="Customer.findByName",singleResult = SingleResultType.OPTIONAL)
    Customer findByName(@QueryParam("name") String name);

    @Query(named="Customer.findAllByActiveStatusAndDeleted")
    List<Customer> findAllByActiveStatusAndDeleted(@QueryParam("activeStatus") String activeStatus,@QueryParam("deleted") String deleted);
}
