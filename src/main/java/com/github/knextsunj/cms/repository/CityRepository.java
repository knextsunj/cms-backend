package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.City;
import org.apache.deltaspike.data.api.*;

import java.util.List;

@Repository
public interface CityRepository extends FullEntityRepository<City, Long>
{

    @Query(named="city.findCityByStateIdAndDeleted")
    List<City> findCityByStateIdAndDeleted(@QueryParam("stateId") Long stateId,@QueryParam("deleted") String deleted);

    @Query(named="city.findCityByName",singleResult = SingleResultType.OPTIONAL)
    City findCityByName(@QueryParam("name") String name);
}
