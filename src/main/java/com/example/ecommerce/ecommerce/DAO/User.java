package com.example.ecommerce.ecommerce.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String username;
    @JsonIgnore
    String password;
    @Column(unique = true)
    String email;
    String sdt;

    @OneToMany(mappedBy = "user",cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY)
    List<UserRole> userRoleList;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY)
    List<AuthProvider> authProviderList;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createAt;


    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updateAt;
}
