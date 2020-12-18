package com.ag04smarts.sha.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    private SimpleDateFormat format;

    public Util(String pattern) {

        this.format = new SimpleDateFormat(pattern);

    }

    public Date makeDate(String date) {

        try {

            return format.parse(date);

        } catch (ParseException exception) {

            throw new RuntimeException("Problem with parsing the date.");

        }

    }

}
