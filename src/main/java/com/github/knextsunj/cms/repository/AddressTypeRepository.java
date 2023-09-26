package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressTypeRepository extends JpaRepository<AddressType,Long>
{

    AddressType findAddressTypeByName(String name);

    List<AddressType> findAllAddressTypesByDeleted(String deleted);
}
