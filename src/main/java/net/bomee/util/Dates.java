package net.bomee.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作工具类
 *
 * @author bomee shiaupo@qq.com
 */
public class Dates {

    public static final String DEFAULT_ISO_CN = "yyyy-MM-dd HH:mm:ss";


    /**
     * 快速构建一个指定的日期
     *
     * @param year        年
     * @param month       月
     * @param day         日
     * @param hour        时
     * @param minute      分
     * @param second      秒
     * @param millisecond 毫秒
     * @return Date
     */
    public static Date of(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }

    /**
     * 使用默认日期格式格式化
     *
     * @param date Date
     * @return Date String
     */
    public static String formatDefault(Date date) {
        return format(date, DEFAULT_ISO_CN);
    }

    /**
     * 使用指定格式格式化
     *
     * @param date   Date
     * @param format 格式
     * @return Date String
     */
    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 使用指定格式解析日期
     *
     * @param dateStr Date String
     * @param format  格式
     * @return Date
     * @throws ParseException 指定格式无法解析
     */
    public static Date parse(String dateStr, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(dateStr);
    }

    /**
     * 使用指定格式解析日期，无法解析时返回null
     *
     * @param dateStr Date String
     * @param format  格式
     * @return Date or null
     */
    public static Date parseQuiet(String dateStr, String format) {
        try {
            return parse(dateStr, format);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 返回日期所在月的第一天
     *
     * @param date Date
     * @return Date 月的第一天
     */
    public static Date firstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 返回日期所在月的最后一天
     *
     * @param date Date
     * @return Date 月的最后一天
     */
    public static Date lastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);

        return calendar.getTime();
    }

    /**
     * 返回日期所在周的第一天（星期一）
     *
     * @param date Date
     * @return 周的第一天（星期一）
     */
    public static Date firstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 返回日期所在周的最后一天（星期日）
     *
     * @param date Date
     * @return 周的最后一天（星期日）
     */
    public static Date lastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);

        return calendar.getTime();
    }
}
