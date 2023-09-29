package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.Country;
import org.apache.deltaspike.data.api.*;

import java.util.List;

@Repository
public interface CountryRepository extends FullEntityRepository<Country, Long>
{

    @Query(named="Country.findByName",singleResult = SingleResultType.OPTIONAL)
    Country findByName(@QueryParam("name") String name);

    @Query(named="Country.findAllCountriesByDeleted")
    List<Country> findAllCountriesByDeleted(@QueryParam("deleted") String deleted);
}
