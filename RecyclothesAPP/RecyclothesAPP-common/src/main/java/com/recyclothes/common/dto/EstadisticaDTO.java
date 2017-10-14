package com.recyclothes.common.dto;

import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.EstadoProductoEnum;
import com.recyclothes.common.enums.TallaEnum;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 09-08-2016.
 */
public class EstadisticaDTO implements Serializable {

    private CatalogoEnum catalogoEnum;
    private EstadoProductoEnum estadoProductoEnum;
    private List<KeyValueDTO<TallaEnum, Long>> keyValueDTOs = new ArrayList<KeyValueDTO<TallaEnum, Long>>();

    public EstadisticaDTO(CatalogoEnum catalogoEnum, EstadoProductoEnum estadoProductoEnum) {
        this.catalogoEnum = catalogoEnum;
        this.estadoProductoEnum = estadoProductoEnum;
    }

    public CatalogoEnum getCatalogoEnum() {
        return catalogoEnum;
    }

    public EstadoProductoEnum getEstadoProductoEnum() {
        return estadoProductoEnum;
    }

    public List<KeyValueDTO<TallaEnum, Long>> getKeyValueDTOs() {
        return keyValueDTOs;
    }

    public void addKeyValueDTOs(KeyValueDTO<TallaEnum, Long> keyValueDTO) {
        this.keyValueDTOs.add(keyValueDTO);
    }

    @Override
    public String toString() {
        return "EstadisticaDTO{" +
                "catalogoEnum=" + catalogoEnum +
                ", estadoProductoEnum=" + estadoProductoEnum +
                '}';
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("catalogoEnum" , catalogoEnum.name());
        jsonObject.put("estadoProductoEnum" , estadoProductoEnum.name());
        JSONArray jsonArray = new JSONArray();
        for(KeyValueDTO<TallaEnum, Long> keyValueDTO : keyValueDTOs){
            jsonArray.add(keyValueDTO.toJSONObject());
        }
        jsonObject.put("keyValueDTOs" , jsonArray.toJSONString());
        return jsonObject;
    }

    public EstadisticaDTO(JSONObject jsonObject){
        catalogoEnum = CatalogoEnum.valueOf((String)jsonObject.get("catalogoEnum"));
        estadoProductoEnum = EstadoProductoEnum.valueOf((String)jsonObject.get("estadoProductoEnum"));
        try {
            JSONArray jsonArray2 = (JSONArray) new JSONParser().parse(new StringReader((String) jsonObject.get("keyValueDTOs")));
            for (int i = 0 ; i < jsonArray2.size() ; i++)   {
                String key = (String)((JSONObject) jsonArray2.get(i)).get("key");
                String value = (String)((JSONObject) jsonArray2.get(i)).get("value");
                KeyValueDTO<TallaEnum,Long> keyValueDTO = new KeyValueDTO<TallaEnum, Long>( TallaEnum.valueOf(key) , Long.valueOf( value));
                this.addKeyValueDTOs(keyValueDTO);
            }
        }catch(Exception e){

        }
    }
}
