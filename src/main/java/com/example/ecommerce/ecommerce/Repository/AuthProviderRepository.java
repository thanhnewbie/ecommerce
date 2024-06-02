package com.example.ecommerce.ecommerce.Repository;

import com.example.ecommerce.ecommerce.DAO.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthProviderRepository extends JpaRepository<AuthProvider, Long> {
    @Query(value = "select user_id from auth_providers where provider_name = :providerName and provider_user_id = :providerUserId", nativeQuery = true)
    public Long findByProviderNameandProviderUserId(@Param("providerName") String providerName, @Param("providerUserId") String providerUserId);

}
