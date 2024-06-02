package com.example.ecommerce.ecommerce.Repository;

import com.example.ecommerce.ecommerce.DAO.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query(value = "select role from user_roles where user_id = :userId", nativeQuery = true)
    List<String> findRoleByUserId(@Param("userId")Long userId);
}
