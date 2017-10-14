package com.recyclothes.common.enums;

/**
 * Created by Cesar on 11-03-2016.
 */
public enum EstadoProductoEnum {

    PENDIENTE,
    DISPONIBLE,
    RESERVADO,
    ENTREGADO;

    public static EstadoProductoEnum getEnum(int ordinal){
        for(EstadoProductoEnum estadoProductoEnum : EstadoProductoEnum.values()){
            if(estadoProductoEnum.ordinal() == ordinal){
                return estadoProductoEnum;
            }
        }
        return null;
    }

/*
    public JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ordinal", this.ordinal());
        jsonObject.put("name",this.name());
        return jsonObject;
    }

    public static JSONArray toJsonArray(){
        JSONArray jsonArray = new JSONArray();
        for (EstadoProductoEnum estadoProductoEnum : values()){
            jsonArray.add(estadoProductoEnum.toJSON());
        }
        return jsonArray;
    }
*/

}
