package com.example.ecommerce.ecommerce.Security;

import com.example.ecommerce.ecommerce.DAO.AuthProvider;
import com.example.ecommerce.ecommerce.DAO.User;
import com.example.ecommerce.ecommerce.DAO.UserRole;
import com.example.ecommerce.ecommerce.Exception.AppException;
import com.example.ecommerce.ecommerce.Exception.ResourceNotFoundException;
import com.example.ecommerce.ecommerce.Repository.AuthProviderRepository;
import com.example.ecommerce.ecommerce.Repository.UserRepository;
import com.example.ecommerce.ecommerce.Repository.UserRoleRepository;
import com.example.ecommerce.ecommerce.Security.user3Party.GithubOauth2UserInfo;
import com.example.ecommerce.ecommerce.Security.user3Party.GoogleOauth2UserInfo;
import com.example.ecommerce.ecommerce.Security.user3Party.OAuth2UserInfoFactory;
import com.example.ecommerce.ecommerce.Security.user3Party.Oauth2UserInfo;
import com.example.ecommerce.ecommerce.constants.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthProviderRepository authProviderRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        return processOAuth2User(oAuth2UserRequest, oAuth2User);
    }
    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User){
        Map<String, Object> attributes = oAuth2User.getAttributes();
        System.out.println(attributes);
        String providerName = oAuth2UserRequest.getClientRegistration().getRegistrationId();
        Oauth2UserInfo oauth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerName, attributes);

        System.out.println(attributes);

        String providerUserId = null;
        if(oauth2UserInfo.getClass() == GithubOauth2UserInfo.class){
            providerUserId = attributes.get("id").toString();
            System.out.println("dây là id của github: " + providerUserId);
        }
        else if(oauth2UserInfo.getClass() == GoogleOauth2UserInfo.class){
            providerUserId = attributes.get("sub").toString();
        }

        Long userId = authProviderRepository.findByProviderNameandProviderUserId(providerName, providerUserId);

        User user;
        List<GrantedAuthority> authorities = new ArrayList<>();
    if(userId != null){
        user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("not found user"));
        List<String> roleList = userRoleRepository.findRoleByUserId(user.getId());
        for(String role : roleList){
            authorities.add(new SimpleGrantedAuthority(role));
        }
    }else{
            user = new User();
            user.setUsername(UUID.randomUUID().toString().replace("-","").substring(0,10));
            User savedUser = userRepository.save(user);

            UserRole userRole = new UserRole();
            userRole.setRole(RoleEnum.MEMBER.toString());
            userRole.setUser(savedUser);
            userRoleRepository.save(userRole);

            AuthProvider authProvider = new AuthProvider();
            authProvider.setProviderUserId(providerUserId);
            authProvider.setProviderName(providerName);
            authProvider.setUser(savedUser);
            authProviderRepository.save(authProvider);
            user = savedUser;
            authorities.add(new SimpleGrantedAuthority(RoleEnum.MEMBER.toString()));
        }
        System.out.println("finish login");
    return new CustomUserDetails(user, authorities);
    }
}
