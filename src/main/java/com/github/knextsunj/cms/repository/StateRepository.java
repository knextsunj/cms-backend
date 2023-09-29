package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.State;
import org.apache.deltaspike.data.api.*;

import java.util.List;


@Repository
public interface StateRepository extends FullEntityRepository<State, Long>
{

	@Query(named="state.findStateByCountryIdAndDeleted")
	List<State> findStateByCountryIdAndDeleted(@QueryParam("countryId") Long countryId,@QueryParam("deleted") String deleted);

	@Query(named="state.findStateByName",singleResult = SingleResultType.OPTIONAL)
	State findStateByName(@QueryParam("name") String name);
}
