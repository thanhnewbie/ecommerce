package com.example.ecommerce.ecommerce.Security;

import com.example.ecommerce.ecommerce.DAO.User;
import com.example.ecommerce.ecommerce.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    UserService userService;
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    MyUserDetailService myUserDetailService;
    @Override
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try{
            String authorHeader = request.getHeader("Authorization");
            String username = null;
            String jwt = null;
            if(authorHeader != null ){
                if(authorHeader.startsWith("Bear ")){
                    jwt = authorHeader.substring(7);
                    username = jwtUtil.extractUsername(jwt);
                }

            }
            if(username != null){
                UserDetails userDetail = myUserDetailService.loadUserByUsername(username);
                if(jwtUtil.validateToken(jwt, userDetail)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetail, null, jwtUtil.getRole(jwt));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    User user = userService.findByUserName(username);
                    request.setAttribute("user", user);
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        System.out.println("day la authorization: " + request.getHeader("Authorization"));
        filterChain.doFilter(request, response);
    }
}
