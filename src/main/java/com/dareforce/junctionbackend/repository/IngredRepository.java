package com.dareforce.junctionbackend.repository;

import com.dareforce.junctionbackend.domain.Ingredient;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByName(String name);
}
