package com.recyclothes.common.utils;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static void main(String... args) {
        Long monto = new Long("199998890");
        System.out.println("monto = "+getMonto(monto));
    }

    public static String getMonto(Long monto)   {
        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String output = myFormatter.format(monto);
        return output;
    }

    public static Date getDate(String dateInString){
        Date date = null;
        if(dateInString != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "CL"));
            date = new Date();
            try {
                date = formatter.parse(dateInString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static String getFecha(final Date date){
        if(date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("es" , "CL")); // Set your date format
            String currentData = sdf.format(date);
            return currentData;
        }
        return null;
    }
    public static String getFechaHora(final Date date){
        if(date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm" , new Locale("es" , "CL")); // Set your date format
            String currentData = sdf.format(date);
            return currentData;
        }
        return null;
    }
    public static String getFecha_Hora_Formato_Fichero(final Date date){
        if(date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm" , new Locale("es" , "CL")); // Set your date format
            String currentData = sdf.format(date);
            return currentData;
        }
        return null;
    }

    public static String getDiaFecha(final Date date){
        if(date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("EE dd/MM/yyyy" , new Locale("es" , "CL")); // Set your date format
            String currentData = sdf.format(date);
            return currentData;
        }
        return null;
    }
    public static String getDiaFechaMes(final Date date){
        if(date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("EE dd MMM yyyy" , new Locale("es" , "CL")); // Set your date format
            String currentData = sdf.format(date);
            return currentData;
        }
        return null;
    }

    public static String generatedHash(String variable)  {
        String generatedVariable = "ce123";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(variable.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedVariable = sb.toString();
        }catch(Exception e){
        }
        return generatedVariable;
    }

    public static String generarCodigoReserva() {
        return generatedHash(Calendar.getInstance().toString()).substring(0, 5);
    }

}
