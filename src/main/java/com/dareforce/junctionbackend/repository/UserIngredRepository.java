package com.dareforce.junctionbackend.repository;

import com.dareforce.junctionbackend.domain.UserIngred;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIngredRepository extends JpaRepository<UserIngred, Long> {
}
