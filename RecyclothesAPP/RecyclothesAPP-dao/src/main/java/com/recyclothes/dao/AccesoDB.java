package com.recyclothes.dao;

import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.EstadoProductoEnum;
import com.recyclothes.common.enums.TallaEnum;

import java.sql.*;
import java.time.LocalDate;
import java.util.Random;

/**
 * Created by Cesar on 14-04-2017.
 */
public class AccesoDB {

    public static AccesoDB accesoDB;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/web";
    static final String USER = "adminvlRnXQp";
    static final String PASS = "3isUfI-lcxI7";

    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public static AccesoDB getInstance(){
        if(accesoDB == null){
            accesoDB = new AccesoDB();
        }
        return accesoDB;
    }

    public Connection getConnection() throws  Exception {
        return DriverManager.getConnection(DB_URL,USER,PASS);
    }

    public static void main(String... args) throws Exception    {
        Connection connection = AccesoDB.getInstance().getConnection();
        String sql = "INSERT INTO tb_producto(id_producto,descripcion,estado_producto,fec_ingreso,catalogo_producto" +
                ",talla_producto,precio_producto,id_foto_producto) " +
                "VALUES( ? ,? ,? , ? , ? , ? , ? , ? )";
        int j = 7;
        PreparedStatement preparedStatement = connection.prepareStatement( sql );
        for( CatalogoEnum catalogoEnum : CatalogoEnum.values()) {
            for(TallaEnum tallaEnum : TallaEnum.values())   {
                for(int i = 0 ; i < 100 ; i++) {
                    preparedStatement.setLong(1, j++);
                    preparedStatement.setString(2, "Descripcion " + catalogoEnum.name() + " " + tallaEnum.name());
                    preparedStatement.setLong(3, EstadoProductoEnum.DISPONIBLE.ordinal());
                    preparedStatement.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                    preparedStatement.setLong(5, catalogoEnum.ordinal());
                    preparedStatement.setLong(6, tallaEnum.ordinal());
                    preparedStatement.setLong(7, new Random().nextInt(10000));
                    preparedStatement.setLong(8, 1297);
                    System.out.println(preparedStatement);
                    preparedStatement.addBatch();
                }
            }
        }
        preparedStatement.executeBatch();
    }
}
