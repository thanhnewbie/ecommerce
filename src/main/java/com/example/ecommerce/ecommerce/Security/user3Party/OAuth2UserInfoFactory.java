package com.example.ecommerce.ecommerce.Security.user3Party;

import com.example.ecommerce.ecommerce.DAO.AuthProvider;
import com.example.ecommerce.ecommerce.Exception.AppException;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static Oauth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(Provider.google.toString())) {
            return new GoogleOauth2UserInfo(attributes);
        }
        else if (registrationId.equalsIgnoreCase(Provider.github.toString())) {
            return new GithubOauth2UserInfo(attributes);
        } else {
            throw new AppException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
