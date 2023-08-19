package com.dareforce.junctionbackend.repository;

import com.dareforce.junctionbackend.domain.UserIngred;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserIngredRepository extends JpaRepository<UserIngred, Long> {

    @Query("select ui from UserIngred ui left join fetch ui.ingredient left join fetch ui.user where ui.user.id = :userId")
    List<UserIngred> findAllByUserId(@Param("userId") Long userId);
}
