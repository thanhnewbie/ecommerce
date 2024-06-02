package com.example.ecommerce.ecommerce.DTO.Login;

import com.example.ecommerce.ecommerce.DAO.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticationResponse {
    private String jwt;
    private User user;
}
