package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.User;
import org.apache.deltaspike.data.api.*;

@Repository
public interface UserRepository extends FullEntityRepository<User,Long>
{

   @Query(named="User.findByUsername",singleResult = SingleResultType.OPTIONAL)
   User findByUsername(@QueryParam("username") String username);
}
