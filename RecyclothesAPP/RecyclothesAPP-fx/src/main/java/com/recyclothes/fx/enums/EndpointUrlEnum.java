package com.recyclothes.fx.enums;

/**
 * Created by Cesar on 23-08-2016.
 */
public enum EndpointUrlEnum {

    LOCALHOST_8080("ws://localhost:8080/RecyclothesEAR-web/adminWebSocket"),
    WWW_RECYCLOTHES_CL("ws://web-babycaprichitos.rhcloud.com:8000/RecyclothesEAR-web/adminWebSocket");

    private String url;

    EndpointUrlEnum(String url){
        this.url = url;
    }

    public String getUtl(){
        return this.url;
    }
}
