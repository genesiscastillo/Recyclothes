package com.recyclothes.dao.impl;

import com.recyclothes.common.dto.KeyValueDTO;
import com.recyclothes.dao.DatabaseDAO;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 27-04-2016.
 */
@Singleton
@Startup
public class DatabaseDAOImpl implements DatabaseDAO {

    static final Logger LOGGER = Logger.getLogger(DatabaseDAOImpl.class);

    @Resource(mappedName="java:jboss/datasources/MySQLDS")
    DataSource dataSource;

    @Override
    public List<KeyValueDTO<String,Integer>> getTotalSpaceTable()  {
        LOGGER.info("INICIANDO ESTADO ESPACIOS...");
        List<KeyValueDTO<String,Integer>> keyValueDTOs  = new ArrayList<>();
        Statement ps = null;
        Connection con = null;
        try
        {
            con = dataSource.getConnection();
            for(String tabla : listaTablasDatabase()) {

                ps = con.createStatement();
                String cadena = "SELECT (data_length+index_length)/power(1024,2) tablesize_mb " +
                        "FROM information_schema.tables " +
                        "WHERE table_schema='web' and table_name='"+tabla+"'";
                ResultSet resultSet = ps.executeQuery(cadena);
                if (resultSet.next()) {
                    int total = resultSet.getInt("tablesize_mb");
                    keyValueDTOs.add(new KeyValueDTO<String, Integer>(tabla , total));
                }
            }
        }
        catch (SQLException sql)        {
            LOGGER.error("SQLException ",sql);
        }
        finally        {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return keyValueDTOs;
    }

    private List<String> listaTablasDatabase(){
        List<String> stringList = new ArrayList<>();
        stringList.add("tb_foto_producto");
        stringList.add("tb_detalle_reserva");
        stringList.add("tb_cliente");
        stringList.add("tb_producto");
        stringList.add("tb_reserva");
        return stringList;
    }
}
