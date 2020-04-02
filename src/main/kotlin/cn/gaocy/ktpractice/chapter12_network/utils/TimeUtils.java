package cn.gaocy.ktpractice.chapter12_network.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class TimeUtils {
    private TimeUtils() {
    }

    public static final String DateFormat = "yyyy-MM-dd HH:mm:ss";

    public static final long ONE_DAY_MILLIS = 86400000L;

    public static long getCurrentDayMillis() {
        return getOneDayStartMillis(System.currentTimeMillis());
    }

    public static String getCurrentDayPretty() {
        Date date = new Date(getCurrentDayMillis());
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
        return sdf.format(date);
    }

    public static String getCurrentTimePretty(long currentTime) {
        Date date = new Date(currentTime);
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
        return sdf.format(date);
    }

    public static long getOneDayStartMillis(long currentTimeMillis) {
        long currentDayMillis = currentTimeMillis / ONE_DAY_MILLIS * ONE_DAY_MILLIS - TimeZone.getDefault().getRawOffset() + ONE_DAY_MILLIS;
        return currentDayMillis;
    }
}
