package ru.dexsys.telegrambot.domain.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    public static java.sql.Date prepareDate(String stringDate) {
        java.sql.Date sqlDate = null;
        try {
            sqlDate = new java.sql.Date(new SimpleDateFormat(DATE_PATTERN).parse(stringDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sqlDate;
    }
}
