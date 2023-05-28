package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.City;
import com.github.knextsunj.cms.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findCityByStateIdAndDeleted(Long stateId,String deleted);

    City findCityByName(String name);
}
