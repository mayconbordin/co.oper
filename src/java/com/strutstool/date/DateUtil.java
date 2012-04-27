package com.strutstool.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * Converte uma String para um objeto Date. Caso a String seja vazia ou nula,
     * retorna null - para facilitar em casos onde formulários podem ter campos
     * de datas vazios.
     * @param date String no formato dd/MM/yyyy a ser formatada
     * @return Date Objeto Date ou null caso receba uma String vazia ou nula
     * @throws DateUtilException
     */
    public static Date formatDate(String date) {
        if (date == null || date.equals(""))
            return null;

        Date formatedDate = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            formatedDate = (java.util.Date)formatter.parse(date);
        } catch (ParseException e) {
            return null;
        }
        return formatedDate;
    }
}
