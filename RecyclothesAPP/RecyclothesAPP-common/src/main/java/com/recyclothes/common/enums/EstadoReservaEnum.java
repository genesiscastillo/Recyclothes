package com.recyclothes.common.enums;

/**
 * Created by Cesar on 31-03-2016.
 */
public enum EstadoReservaEnum {
    PENDIENTE,
    POR_PAGAR,
    PAGADO,
    ENTREGADO,
    ANULADO;

    public static EstadoReservaEnum getEnum(int ordinal){
        for(EstadoReservaEnum estadoReservaEnum : EstadoReservaEnum.values()){
            if (estadoReservaEnum.ordinal() == ordinal) {
                return estadoReservaEnum;
            }
        }
        return null;
    }

}
