package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StateRepository extends JpaRepository<State, Long>
{

	List<State> findStateByCountryIdAndDeleted(Long countryId,String deleted);
	
	State findStateByName(String name);
}
