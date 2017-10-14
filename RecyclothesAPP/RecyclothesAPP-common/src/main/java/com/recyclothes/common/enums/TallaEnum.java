package com.recyclothes.common.enums;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by Cesar on 11-03-2016.
 */
public enum TallaEnum {

    RN_0_3,
    MESES_6,
    MESES_9,
    MESES_12,
    MESES_18,
    MESES_24,
    DOS,
    TRES,
    CUATRO,
    CINCO,
    SEIS,
    SIETE,
    OCHO,
    DIEZ;

    public static TallaEnum getEnum(int ordinal){
        for(TallaEnum tallaEnum : TallaEnum.values()){
            if (tallaEnum.ordinal() == ordinal) {
                return tallaEnum;
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
        for (TallaEnum tallaEnum: values()){
            jsonArray.add(tallaEnum.toJSON());
        }
        return jsonArray;
    }

}
