package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.UserPreference;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface UserPreferenceRepository extends FullEntityRepository<UserPreference,Long>
{

    @Query(named="UserPreference.findByUserId")
    List<UserPreference> findByUserId(@QueryParam("userId") Long userId);
}
