package com.ajsoftware.backendjwtauth.repository;

import com.ajsoftware.backendjwtauth.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);

}
