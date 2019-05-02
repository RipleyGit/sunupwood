package cn.chainof.sunup.utils;


import com.github.pagehelper.util.StringUtil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    /**
     * 获取系统时间 转换为yyyy-MM-dd HH:mm:ss格式，返回字符串类型
     *
     * @return
     */
    public static String getStringDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDate = format.format(new Date());
        return newDate;
    }

    /**
     * 获取系统时间 转换为自定义格式，返回字符串类型
     *
     * @param strformat
     *            时间格式（yyyyMMdd）
     * @return
     */
    public static String getStringDateFormat(String strformat) {
        SimpleDateFormat format = new SimpleDateFormat(strformat);
        String newDate = format.format(new Date());
        return newDate;
    }

    /**
     * 获取系统时间 转换为yyyy-MM-dd HH:mm:ss格式,返回Date类型
     *
     * @return
     * @throws ParseException
     */
    public static Date getCurrentDate() {
        Date date = null;
        try {
            SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();
            String str = yyyyMMddHHmmss.format(date);
            date = yyyyMMddHHmmss.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    public static Date getDateFormat(Date date, String format) {

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            date = new Date();
            String str = formatter.format(date);
            date = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    public static String getDateStr(Date date) {
        SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return yyyyMMddHHmmss.format(date);
    }

    public static String getDateStr(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat(format);
        return yyyyMMddHHmmss.format(date);
    }

    /**
     * 获取系统时间 转换为yyyy-MM-dd格式
     *
     * @return
     */
    public static String getStringDateYMD() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = format.format(new Date());
        return newDate;
    }

    /**
     * 获取系统时间 转换为yyyy-MM-dd HH:mm:ss格式
     *
     * @return
     */
    public static String getStringDateYMDS() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDate = format.format(new Date());
        return newDate;
    }

    /**
     * 通过指定转换类型参数 返回String当前时间 转换为默认格式
     *
     * @return
     */
    public static String getStringDate(String sdf) {
        SimpleDateFormat format = new SimpleDateFormat(sdf);
        String newDate = format.format(new Date());
        return newDate;
    }

    /**
     * 通过指定转换类型和日期参数 返回字符类型 转换为默认格式
     *
     * @return
     */
    public static String getStringDate(String sdf, String date) throws ParseException {
        if (StringUtil.isEmpty(date)) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(sdf);
        String newDate = format.format(getDate(date));
        return newDate;
    }

    /**
     * 通过参数转换时间返回date类型 转换为默认格式
     *
     * @return
     */
    public static Date getDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }

    /**
     * 通过参数转换时间返回date类型 转换为默认格式
     *
     * @return
     */
    public static Date getDateTime(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(date);
    }

    /**
     * 获取系统时间 转换为yyyyMMddHHmmss格式，返回字符串类型
     *
     * @return
     */
    public static String getStringTimeSn() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = format.format(new Date());
        return newDate;
    }

    /**
     * 获取后缀带时间戳的字符串
     *
     * @param content
     * @return
     */
    public static String getTimeSuffixString(String content) {
        if (content == null) {
            return "副本";
        }
        String flag1 = "_";
        String flag2 = "-";
        String flag3 = ":";

        // 获取【2018-03-08 14:07】格式的字符串
        String stringDate = DateUtil.getStringDate();
        stringDate = stringDate.substring(0, stringDate.lastIndexOf(flag3));

        // 拼装
        if (content.contains(flag1) && content.contains(flag2) && content.contains(flag3)) {
            int indexOf = content.indexOf("_ 2");
            content = content.substring(0, indexOf);
        }

        content = content + flag1 + stringDate;
        return content;
    }

    /**
     * 获取当前星期
     *
     * @param dt
     *            日期
     * @return
     */
    public static String getWeekOfDate(String strDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strDate);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;
        try {
            dt = simpleDateFormat.parse(strDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] weekDays = { "0", "1", "2", "3", "4", "5", "6" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 判断当前日期是星期几
     *
     *
     * @param pTime
     *            修要判断的时间
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */
    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 获取昨天日期
     *
     * @param args
     * @return
     */
    public static String getYestardayStr() {

        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date time = cal.getTime();
        return DateUtil.getDateStr(time, "yyyy-MM-dd");
    }

    // 获取当日开始时间
    public static Date getStartTime() {
        Calendar todayStart = new GregorianCalendar();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    // 获取当日结束时间
    public static Date getEndTime() {
        Calendar todayEnd = new GregorianCalendar();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    // 获取昨日开始时间
    public static Date getYesterdayStartTime() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getStartTime());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    // 获取昨日结束时间
    public static Date getYesterdayEndTime() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getEndTime());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    public static Date getTodayZero() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        ca.set(Calendar.DATE, ca.get(Calendar.DATE));
        Date today = ca.getTime();
        return today;
    }

    // 获取当月开始时间
    public static Date getMonthStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    // 获取当月结束时间
    public static Date getMonthEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    // 获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    // 获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    // 获取本周的开始时间
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return cal.getTime();
    }

    // 获取本周的结束时间
    public static String getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return DateUtil.getDateStr(weekEndSta, "yyyy-MM-dd");
    }

    // 获取今年是哪一年
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    // 获取本月是哪一月
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    public static String getNowYearAndMonth() {
        String month = String.valueOf(getNowMonth());
        return getNowYear() + "-" + (month.length() == 1 ? "0" + month : month);
    }

    // 获取某年某月最后一天
    public static Date getMaxDateOfMonth(Integer year, Integer month) {
        Calendar calendarQuarter = Calendar.getInstance();
        calendarQuarter.set(Calendar.YEAR, year);
        calendarQuarter.set(Calendar.MONTH, month);
        int endDay = calendarQuarter.getActualMaximum(Calendar.DATE);

        Calendar endQuarterCalendar = Calendar.getInstance();

        endQuarterCalendar.set(year, month, endDay, 23, 59, 59);

        return endQuarterCalendar.getTime();
    }

    // 获取某年某季度最后一天
    public static Date getMaxDateOfQuarter(Integer year, Integer quarter) {
        int endMonth = 0;

        if (quarter == 1) {
            endMonth = 2;
        } else if (quarter == 2) {
            endMonth = 5;
        } else if (quarter == 3) {
            endMonth = 8;
        } else if (quarter == 4) {
            endMonth = 11;
        }

        return getMaxDateOfMonth(year, endMonth);
    }

    // 获取某年某季度第一天
    public static Date getMinDateOfQuarter(Integer year, Integer quarter) {
        int beginMonth = 0;

        if (quarter == 1) {
            beginMonth = 0;
        } else if (quarter == 2) {
            beginMonth = 3;
        } else if (quarter == 3) {
            beginMonth = 6;
        } else if (quarter == 4) {
            beginMonth = 9;
        }

        Calendar beginQuarterCalendar = Calendar.getInstance();
        beginQuarterCalendar.set(year, beginMonth, 1, 0, 0, 0);

        return beginQuarterCalendar.getTime();
    }
}