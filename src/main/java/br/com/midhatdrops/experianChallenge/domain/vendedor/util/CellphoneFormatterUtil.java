package br.com.midhatdrops.experianChallenge.domain.vendedor.util;

public class CellphoneFormatterUtil {

    public static String formatCellphoneToSend(String cellphone) {
        return String.format("%s %s-%s",cellphone.substring(0,2),cellphone.substring(2,7),cellphone.substring(7));
    }
}
