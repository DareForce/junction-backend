package com.dareforce.junctionbackend.repository;

import com.dareforce.junctionbackend.domain.Menu;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("select m from Menu m join fetch m.restaurant join fetch m.menuIngreds as mi join fetch mi.ingredient where m.restaurant.id = :restaurantId")
    List<Menu> findByRestaurantId(@Param("restaurantId") Long restaurantId);

    @Query("select m from Menu m left join fetch m.menuIngreds as mi left join fetch mi.ingredient where m.id = :menuId")
    Optional<Menu> findByMenuId(@Param("menuId") Long menuId);
}
