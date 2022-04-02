package com.fram.springsecurityclient.repository;

import com.fram.springsecurityclient.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {

}
