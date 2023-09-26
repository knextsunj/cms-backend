package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>
{

    Country findByName(String name);

    List<Country> findAllCountriesByDeleted(String deleted);
}
