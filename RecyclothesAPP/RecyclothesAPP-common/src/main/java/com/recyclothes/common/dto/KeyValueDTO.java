package com.recyclothes.common.dto;

import org.json.simple.JSONObject;

import java.io.Serializable;

public class KeyValueDTO<K,V> implements Serializable  {

    K key;
    V value;

    public KeyValueDTO(K k, V v){
        this.key = k;
        this.value = v;
    }

    public <K> K getKey(){
        return (K) this.key;
    }
    public <V> V getValue(){
        return  (V) this.value;
    }


    public JSONObject toJSONObject()    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", key.toString() );
        jsonObject.put("value", value.toString());
        return jsonObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyValueDTO<?, ?> that = (KeyValueDTO<?, ?>) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        return !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public java.lang.String toString() {
        return "KeyValueDTO{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
