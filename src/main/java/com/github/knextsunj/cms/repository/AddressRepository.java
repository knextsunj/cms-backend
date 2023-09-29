package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.Address;
import org.apache.deltaspike.data.api.*;


import java.util.List;

@Repository
public interface AddressRepository extends FullEntityRepository<Address, Long>
{
    @Query(named="Address.findAddressByCustomerIdAndDeleted")
    List<Address> findAddressByCustomerIdAndDeleted(@QueryParam("customerId") Long customerId,@QueryParam("deleted") String deleted);
}
