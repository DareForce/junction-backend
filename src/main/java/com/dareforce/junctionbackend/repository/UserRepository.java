package com.dareforce.junctionbackend.repository;

import com.dareforce.junctionbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
