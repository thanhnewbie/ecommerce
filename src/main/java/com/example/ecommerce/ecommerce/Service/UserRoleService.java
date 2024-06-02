package com.example.ecommerce.ecommerce.Service;

import com.example.ecommerce.ecommerce.DAO.User;
import com.example.ecommerce.ecommerce.Exception.ResourceNotFoundException;
import com.example.ecommerce.ecommerce.Repository.UserRepository;
import com.example.ecommerce.ecommerce.Repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRepository userRepository;

    public List<String> getRoleUser(Long userId){
        return userRoleRepository.findRoleByUserId(userId);
    }
//    public List<String> getRoleUser(String username){
//        User user = userRepository.findByUsername(username);
//        if(user == null) throw new ResourceNotFoundException("not found this username");
//        return userRoleRepository.findRoleByUserId((Long)user.getId());
//    }
}
