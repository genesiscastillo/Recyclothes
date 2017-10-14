package com.recyclothes.enums;

/**
 * Created by Cesar on 21-02-2016.
 */
public enum TestAccionEnum {
    LOAD("load"),
    ADD("add"),
    RESERVE("reserve"),
    VALIDAR_USUARIO("VALIDAR_USUARIO"),
    REGISTRAR_USUARIO("REGISTRAR_USUARIO");

    private String value;
    TestAccionEnum(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}
