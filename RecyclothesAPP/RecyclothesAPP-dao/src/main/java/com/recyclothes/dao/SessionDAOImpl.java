package com.recyclothes.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cesar on 10-09-2016.
 */
public class SessionDAOImpl {

    public static void main(String... args) throws Exception    {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        ResultSet resultSet = null;
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("fecha", null);
        map.put("CURSOR_TABLE1", resultSet);
        sqlSessionFactory.openSession().selectOne("mapper.BlogMapper.selectDate" , map);
        System.out.println(map);
        System.out.println("result fecha  "+(Integer)map.get("fecha"));
        System.out.println("result Table1 "+(ArrayList<Table1>)map.get("CURSOR_TABLE1"));
    }
}
