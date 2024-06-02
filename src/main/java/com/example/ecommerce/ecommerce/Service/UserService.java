package com.example.ecommerce.ecommerce.Service;

import com.example.ecommerce.ecommerce.DAO.User;
import com.example.ecommerce.ecommerce.DAO.UserRole;
import com.example.ecommerce.ecommerce.DTO.Login.SignUpDTO;
import com.example.ecommerce.ecommerce.Repository.UserRepository;
import com.example.ecommerce.ecommerce.Repository.UserRoleRepository;
import com.example.ecommerce.ecommerce.constants.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    public User create (SignUpDTO signUpDTO){// lưu đồng thời user và user role
        User user = new User();
        user.setSdt(signUpDTO.getSDT());
        user.setPassword(signUpDTO.getPassword());
        user.setUsername(UUID.randomUUID().toString().replace("-","").substring(0,10));
        User savedUser = userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setRole(RoleEnum.MEMBER.toString());
        userRole.setUser(savedUser);

        userRoleRepository.save(userRole);
        return savedUser;
    }
    public User findByUserName(String username){
        return userRepository.findByUsername(username);
    }
    public User findBySDT(String sdt){
        return userRepository.findBySdt(sdt);
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
}
