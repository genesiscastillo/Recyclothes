package com.recyclothes.common.enums;

/**
 * Created by Cesar on 15-08-2016.
 */
public enum HoraEntregaEnum {

    H_10("10:00am"),
    H_11("11:00am"),
    H_12("12:00am"),
    H_13("01:00pm"),
    H_14("02:00pm"),
    H_15("03:00pm"),
    H_16("04:00pm"),
    H_17("05:00pm"),
    H_18("06:00pm"),
    H_19("07:00pm");

    private String descripcion;

    HoraEntregaEnum(String descripcion){
        this.descripcion = descripcion;
    }

    @Override
    public String toString(){
        return descripcion;
    }

    public static HoraEntregaEnum valueOfString(String var0){
        HoraEntregaEnum horaEntregaEnum = null;
        if(var0 != null) {
            for (HoraEntregaEnum horaEntregaEnumTmp : HoraEntregaEnum.values()) {
                if (horaEntregaEnumTmp.toString().equals(var0)) {
                    return horaEntregaEnumTmp;
                }
            }
        }
        return horaEntregaEnum;
    }

}
