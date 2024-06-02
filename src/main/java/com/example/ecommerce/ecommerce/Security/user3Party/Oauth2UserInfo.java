package com.example.ecommerce.ecommerce.Security.user3Party;

import java.util.Map;

public abstract class Oauth2UserInfo {
    protected Map<String, Object> attributes;
    public Oauth2UserInfo(Map<String, Object> attributes){
        this.attributes = attributes;
    }
    public Map<String, Object> getAttributes(){
        return attributes;
    }
    public abstract String getId();
    public abstract String getName();

}
