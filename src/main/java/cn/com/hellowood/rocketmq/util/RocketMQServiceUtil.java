package cn.com.hellowood.rocketmq.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author HelloWood
 * @create 2017-09-01 21:46
 * @email hellowoodes@outlook.com
 **/

public class RocketMQServiceUtil {
    private static Logger logger = LoggerFactory.getLogger(RocketMQServiceUtil.class);

    /**
     * Transfer date string to timestamp
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Long getTimestampByDateString(String dateString) throws ParseException {
        Long time = null;
        if (StringUtils.isNotEmpty(dateString)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(dateString);
            time = date.getTime();
        }
        return time;
    }

    /**
     * Format date to String
     *
     * @param date
     * @return
     */
    public static String formatDateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
