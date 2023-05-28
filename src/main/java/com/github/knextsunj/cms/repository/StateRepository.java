package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.State;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	List<State> findStateByCountryIdAndDeleted(Long countryId,String deleted);
	
	State findStateByName(String name);
}
