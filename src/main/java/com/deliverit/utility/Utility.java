package com.deliverit.utility;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class Utility {
    private Utility() {
    }

    public static final String LOCAL_TIMEZONE = "GMT+6";
    public static final ZoneId ZONE_ID_DHAKA = ZoneId.of("Asia/Dhaka");
    public static final String MW_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS Z";
    public static final DateTimeFormatter MW_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(MW_DATE_TIME_FORMAT);
    public final static SimpleDateFormat YEAR_FORMATTER = new SimpleDateFormat("yy");
    public final static SimpleDateFormat HOUR_FORMATTER = new SimpleDateFormat("HH");
    public final static SimpleDateFormat DETAILED_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss Z");
    public final static SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    public static final String HEADER_UAUTHORIZATION = "Authorization";
    public static final String HEADER_TOKEN_PREFIX = "Bearer ";
    public static final int HEADER_TOKEN_PREFIX_LENGTH = 7;
    public static final long JWT_TOKEN_VALIDITY_IN_MILLISECONDS = 1 * 60 * 60 * 1000;

    public static String formattedDateTime(Date date) {
        var sdf = new SimpleDateFormat(MW_DATE_TIME_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone(LOCAL_TIMEZONE));
        return sdf.format(date);
    }

    public static String formattedDateTime() {
        return MW_DATE_TIME_FORMATTER.format(getZonedDateTime());
    }
    private static ZonedDateTime getZonedDateTime() {
        return ZonedDateTime.ofInstant(Instant.now(), ZONE_ID_DHAKA);
    }

}
