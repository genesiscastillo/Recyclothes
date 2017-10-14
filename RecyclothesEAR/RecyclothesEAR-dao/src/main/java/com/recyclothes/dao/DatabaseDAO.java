package com.recyclothes.dao;

import com.recyclothes.common.dto.KeyValueDTO;

import java.util.List;

/**
 * Created by Cesar on 27-04-2016.
 */
public interface DatabaseDAO {
    List<KeyValueDTO<String,Integer>> getTotalSpaceTable()  ;
}
