package cn.gaocy.authcode.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class TimeUtils {
    private TimeUtils() {
    }

    public static final String DateFormat = "yyyy-MM-dd_HH:mm:ss:SSS";

    public static final long ONE_DAY_MILLIS = 86400000L;

    public static long getCurrentDayMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return zero.getTime();

    }

    public static String getCurrentTimePretty(long currentTime) {
        Date date = new Date(currentTime);
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
        return sdf.format(date);
    }
}
