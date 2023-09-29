package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.AddressType;
import org.apache.deltaspike.data.api.*;

import java.util.List;

@Repository
public interface AddressTypeRepository extends FullEntityRepository<AddressType,Long>
{

    @Query(named="AddressType.findAddressTypeByName",singleResult = SingleResultType.OPTIONAL)
    AddressType findAddressTypeByName(@QueryParam("name") String name);

    @Query(named="AddressType.findAllAddressTypesByDeleted")
    List<AddressType> findAllAddressTypesByDeleted(@QueryParam("deleted") String deleted);
}
