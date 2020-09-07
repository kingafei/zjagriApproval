package cn.com.ecenter.common.utils;

import cn.com.ecenter.common.entity.FebsConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 *
 * @author MrBird
 */
public class DateUtil {

    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String CST_TIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";

    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_PATTERN);
    }

    public static String formatFullTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    public static String getDateFormat(Date date, String dateFormatType) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatType, Locale.CHINA);
        return simpleDateFormat.format(date);
    }

    public static String formatCstTime(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CST_TIME_PATTERN, Locale.US);
        Date usDate = simpleDateFormat.parse(date);
        return DateUtil.getDateFormat(usDate, format);
    }

    public static String formatInstant(Instant instant, String format) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 判断当前日期为星期几
     * @param date
     * @return
     */
    public static int dayOfWeek(Date date){
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        int weekDay = aCalendar.get(Calendar.DAY_OF_WEEK);
        return weekDay;
    }

    /**
     * 获取指定日期指定工作日后的日期
     * @param currentDate 指定日期
     * @param days 指定工作日
     */
    public static String getAfterWorkDay(Date currentDate,int days){
        Date date = currentDate;
        //设置循环次数,如果含最后一天则循环 days + 1 天，不需要含最后一天，则循环 days次
        for(int i = 0; i < days + 1; i++){
            Date nextDate = getAfterDay(date,1,false); //获取下一天的日期
            int weekDay = dayOfWeek(nextDate); //下一天日期为星期几
            if(weekDay == 1){ //为星期六
                date = getAfterDay(date,2,false);
            }else if(weekDay == 6){ //为星期天
                date = getAfterDay(date,3,false);
            }else{
                date = nextDate;
            }
        }
        return getDateFormat(date, FULL_TIME_SPLIT_PATTERN);
    }


    /**
     * 获取指定日期指定天数后的日期
     * @param date 指定日期
     * @param index 指定天数
     * @param flag 是否将时分秒归0
     * @return
     */
    public static Date getAfterDay(Date date, int index, boolean flag){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// 获得当前时间
        if(flag){
            // 日期不变，把时间设定为00：00：00
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 00);
            cal.set(Calendar.SECOND, 00);
        }
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + index);
        return cal.getTime();
    }


    /**
     * 获取指定日期指定月后的日期
     * @param currentDate 指定日期
     * @param number
     * @return
     */
    public static String  getAfterMonth(Date currentDate,int number) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat(FULL_TIME_SPLIT_PATTERN);
        Date date = currentDate;
        c.setTime(date);//设置日历时间
        c.add(Calendar.MONTH,number);//在日历的月份上增加N个月
        String strDate = sdf.format(c.getTime());//的到你想要得N个月后的日期
        return strDate;
    }

    /**
     * 获取指定日期指定月后的日期
     * @param currentDate 指定日期
     * @param number
     * @return
     */
    public static String  getAfterYear(Date currentDate,int number) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat(FULL_TIME_SPLIT_PATTERN);
        Date date = currentDate;
        c.setTime(date);//设置日历时间
        c.add(Calendar.YEAR,number);//在日历的月份上增加N年
        String strDate = sdf.format(c.getTime());//的到你想要得N年后的日期
        return strDate;
    }

    /**
     * 获取指定时间后的时间
     * @param currentDate
     * @param dateType
     * @param number
     * @return
     */
    public static String getAfterDate(Date currentDate, String dateType,int number) {
        String promiseEtime = "";
        switch(dateType){
            case FebsConstant.CN_WORK_DAY :
                promiseEtime = getAfterWorkDay(currentDate, number);
                break;
            case FebsConstant.CN_DAY :
                promiseEtime = getDateFormat(getAfterDay(currentDate, number,false), FULL_TIME_SPLIT_PATTERN);
                break;
            case FebsConstant.CN_MONTH :
                promiseEtime = getAfterMonth(currentDate, number);
                break;
            case FebsConstant.CN_YEAR :
                promiseEtime = getAfterYear(currentDate, number);
                break;
            default :
        }
        return promiseEtime;

    }
}
