package com.dareforce.junctionbackend.repository;

import com.dareforce.junctionbackend.domain.Restaurant;
import com.dareforce.junctionbackend.dto.RestaurantDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("select r from Restaurant r join fetch r.menus m")
    List<Restaurant> findAll();
}
