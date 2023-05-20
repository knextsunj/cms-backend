package com.github.knextsunj.cms.repository;

import com.github.knextsunj.cms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

   User findByUsername(String username);
}
