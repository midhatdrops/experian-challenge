package br.com.midhatdrops.experianChallenge.domain.vendedor.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatterUtil {

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String format = dateFormat.format(date);
        return format.replace("-","/");
    }
}
