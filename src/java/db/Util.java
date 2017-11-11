/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author gerar
 */

//Clase destinada a hacer operaciones frecuentes como calcular fecha, etc
public class Util {
    
    //metodo que regresa un string con la fecha actual ano-mes-dia
    public static String getFecha(){
        Calendar c = new GregorianCalendar();
        String dia, mes, annio;
        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH)+1);
        annio = Integer.toString(c.get(Calendar.YEAR));
        return (annio+"-"+mes+"-"+dia);
    }
    
    
    
}
