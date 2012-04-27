package com.strutstool.currency;

import java.text.DecimalFormat;

/**
 *
 * @author maycon
 */
public class CurrencyUtils {
    public static String formatToReais(double value) {
        DecimalFormat df = new DecimalFormat("R$ ###,###,##0.00");
        return df.format(value);
    }
}
