package com.example.ecommerce.ecommerce.Security.user3Party;

import java.util.Map;

public class GoogleOauth2UserInfo extends Oauth2UserInfo{
    public GoogleOauth2UserInfo(Map<String, Object> attributes){
        super(attributes);
    }
    @Override
    public String getId(){
        return (String) attributes.get("sub");
    }
    @Override
    public String getName(){
        return (String) attributes.get("name");
    }

}
