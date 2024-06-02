package com.example.ecommerce.ecommerce.Security;

import com.example.ecommerce.ecommerce.DAO.User;
import com.example.ecommerce.ecommerce.Repository.UserRepository;
import com.example.ecommerce.ecommerce.Repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;// UserRepository là lớp tương tác với cơ sở dữ liệu

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();

        List<String> roleList = userRoleRepository.findRoleByUserId(user.getId());
        for(String role : roleList){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new CustomUserDetails(user, authorities);
    }
}
