package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference,Long>
{

    List<UserPreference> findByUserId(Long userId);
}
