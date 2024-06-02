package com.example.ecommerce.ecommerce.Security.user3Party;

import java.util.Map;

public class GithubOauth2UserInfo extends Oauth2UserInfo{
    public GithubOauth2UserInfo(Map<String, Object> attributes){
        super(attributes);
    }
    @Override
    public String getId(){
        return ((Integer) attributes.get("id")).toString();
    }
    @Override
    public String getName(){
        return (String) attributes.get("name");
    }
}
