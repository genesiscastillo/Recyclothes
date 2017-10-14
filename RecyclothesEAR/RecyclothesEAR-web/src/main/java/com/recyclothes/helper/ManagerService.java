package com.recyclothes.helper;

import com.recyclothes.common.enums.AccionEnum;

import javax.websocket.Session;

/**
 * Created by Cesar on 01-03-2016.
 */
public interface ManagerService {

    void service(Session session, AccionEnum accionEnum, Object... obj);
}
