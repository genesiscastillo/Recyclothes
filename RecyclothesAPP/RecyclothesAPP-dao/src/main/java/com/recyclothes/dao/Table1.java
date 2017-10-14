package com.recyclothes.dao;

import java.io.Serializable;

/**
 * Created by Cesar on 11-09-2016.
 */
public class Table1 implements Serializable {

    private String column1;
    private String column2;
    private String column3;

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    @Override
    public String toString() {
        return "Table1{" +
                "column1='" + column1 + '\'' +
                ", column2='" + column2 + '\'' +
                ", column3='" + column3 + '\'' +
                '}';
    }
}
