package com.itv.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by sakibchoudhury on 05/12/17.
 */

public class MonetaryFormatUtil {

    private MonetaryFormatUtil() {
    }

    public static final String TWO_DECIMAL_PATTERN = "#.00";

    public static String productFormattedMoneyByPattern(BigDecimal money, String pattern) {

        if(money == null)
            throw new IllegalArgumentException("Pattern is Empty");

        if(pattern == null || pattern.isEmpty())
            throw new IllegalArgumentException("Pattern is Empty");

        if(money.doubleValue() == 0)
            return "0.00";



        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String format = decimalFormat.format(money.doubleValue());
        return format;
    }


}
