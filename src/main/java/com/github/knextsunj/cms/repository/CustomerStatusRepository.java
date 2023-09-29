package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.CustomerStatus;
import org.apache.deltaspike.data.api.*;

import java.util.List;

@Repository
public interface CustomerStatusRepository extends FullEntityRepository<CustomerStatus,Long>
{
    @Query(named="CustomerStatus.findCustomerStatusByName",singleResult = SingleResultType.OPTIONAL)
    CustomerStatus findCustomerStatusByName(@QueryParam("name") String name);

    @Query(named="CustomerStatus.findAllCustomerStatusByDeleted")
    List<CustomerStatus> findAllCustomerStatusByDeleted(@QueryParam("deleted") String deleted);

}
