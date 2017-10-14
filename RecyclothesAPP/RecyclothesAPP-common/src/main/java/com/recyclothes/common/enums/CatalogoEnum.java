package com.recyclothes.common.enums;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public enum CatalogoEnum {

    NINOS,
    NINAS;

    public static CatalogoEnum getEnum(int ordinal){
        for(CatalogoEnum catalogoEnum : CatalogoEnum.values()){
            if(catalogoEnum.ordinal() == ordinal){
                return catalogoEnum;
            }
        }
        return null;
    }


    public JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ordinal", this.ordinal());
        jsonObject.put("name",this.name());
        return jsonObject;
    }

    public static JSONArray toJsonArray(){
        JSONArray jsonArray = new JSONArray();
        for (CatalogoEnum catalogoEnum : values()){
            jsonArray.add(catalogoEnum.toJSON());
        }
        return jsonArray;
    }

}
