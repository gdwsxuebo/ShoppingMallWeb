package com.smw.common.util;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description：日期常用处理工具类
 *
 * @author yumaochun
 *
 */
public class DateUtil {
	/* 日志 */
	private static Logger logger = LoggerFactory.getLogger( DateUtil.class );


	/**
	 * 日期和时间24小时格式
	 */
	public static final String DATE_TIME_24HOURS_FORMAT = "yyyy-MM-dd HH:mm:ss";


	/**
	 * 日期和时间12小时格式
	 */
	public static final String DATE_TIME_12HOURS_FORMAT = "yyyy-MM-dd hh:mm:ss";


	/**
	 * 常用日期格式
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";


	/**
	 * 日期格式化_日期
	 */
	private static final SimpleDateFormat _sdf = new SimpleDateFormat( DATE_FORMAT );


	/**
	 * 日期格式化_24小时分秒
	 */
	private static final SimpleDateFormat _sdf24 = new SimpleDateFormat( DATE_TIME_24HOURS_FORMAT );


	/**
	 * 一周的天数
	 */
	private static final int _weekDays = 7;


	/**
	 * 获取当前日期同上周日相差的天数(负值)
	 *
	 * @return
	 */
	private static int getDofTodayToPreSun()
	{
		Calendar	c		= Calendar.getInstance();
		int		dayOfWeek	= c.get( Calendar.DAY_OF_WEEK ) - 1;
		return(dayOfWeek == 0 ? (1 - 7) : (1 - dayOfWeek) );
	}


	/**
	 * 获取当前日期同周日相差的天数(正值)
	 *
	 * @return
	 */
	private static int getDofTodayToSun()
	{
		Calendar	c		= Calendar.getInstance();
		int		dayOfWeek	= c.get( Calendar.DAY_OF_WEEK ) - 1;
		return(7 - dayOfWeek);
	}


	/**
	 * 根据给定的日期和日期格式返回字符串
	 *
	 * @param date
	 * @param fmts , 默认format "yyyy-MM-dd"
	 * @return
	 */
	public static String format( Date date, String...fmts )
	{
		SimpleDateFormat sdf;
		if ( fmts.length == 0 )
			sdf = _sdf;
		else
			sdf = new SimpleDateFormat( fmts[0] );
		if ( date != null )
			return(sdf.format( date ) );
		else
			return("");
	}


	public static String TimestampToString( Timestamp smp, String sFormat )
	{
		SimpleDateFormat sf = new SimpleDateFormat( sFormat );
		return(sf.format( smp ) );
	}


	public static String TimestampToString( Timestamp smp )
	{
		SimpleDateFormat sf = new SimpleDateFormat( "yyyy-MM-dd hh:ss:mm" );
		return(sf.format( smp ) );
	}


	/**
	 * 将给定字符串转换成日期
	 *
	 * @param dateString
	 * @param fmts
	 * @return
	 */
	public static Date parse( String dateString, String...fmts )
	{
		SimpleDateFormat sdf;
		if ( fmts.length == 0 )
			sdf = _sdf;
		else
			sdf = new SimpleDateFormat( fmts[0] );
		if ( dateString == null || "".equals( dateString ) )
			return(null);
		else
			try {
				return(sdf.parse( dateString ) );
			} catch ( ParseException e ) {
				return(null);
			}
	}


	/**
	 * 两个时间之间相差距离多少天
	 * @param one 时间参数 1：
	 * @param two 时间参数 2：
	 * @return 相差天数
	 */
	public static long getDistanceDays( Date one, Date two )
	{
		try {
			long	days	= 0;
			long	time1	= one.getTime();
			long	time2	= two.getTime();
			long	diff;
			if ( time1 < time2 )
			{
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			days = diff / (1000 * 60 * 60 * 24);
			return(days);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}


	/**
	 * 获取本周星期一的日期
	 *
	 * @return
	 */
	public static Date getMondayOfCurrentWeek()
	{
		int			dValue		= getDofTodayToPreSun();
		GregorianCalendar	currentDate	= new GregorianCalendar();
		currentDate.add( GregorianCalendar.DATE, dValue );
		Date	monday	= currentDate.getTime();
		String	source	= _sdf.format( monday );
		try {
			monday = _sdf.parse( source );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return(monday);
	}


	/**
	 * 获取本周星期一的日期字符串
	 *
	 * @return
	 */
	public static String getMondayStringOfCurrentWeek()
	{
		return(_sdf.format( getMondayOfCurrentWeek() ) );
	}


	/**
	 * 获取本周日的日期
	 *
	 * @return
	 */
	public static Date getSundayOfCurrentWeek()
	{
		int			dValue		= getDofTodayToSun();
		GregorianCalendar	currentDate	= new GregorianCalendar();
		currentDate.add( GregorianCalendar.DATE, dValue );
		Date	sunday	= currentDate.getTime();
		String	source	= _sdf.format( sunday );
		try {
			sunday = _sdf.parse( source );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return(sunday);
	}


	/**
	 * 获取本周日的日期字符串
	 *
	 * @return
	 */
	public static String getSundayStringOfCurrentWeek()
	{
		return(_sdf.format( getSundayOfCurrentWeek() ) );
	}


	/**
	 * 获取上周一的日期
	 *
	 * @return
	 */
	public static Date getMondayOfPreviousWeek()
	{
		int			dValue		= getDofTodayToPreSun() - _weekDays;
		GregorianCalendar	currentDate	= new GregorianCalendar();
		currentDate.add( GregorianCalendar.DATE, dValue );
		Date	monday	= currentDate.getTime();
		String	source	= _sdf.format( monday );
		try {
			monday = _sdf.parse( source );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return(monday);
	}


	/**
	 * 获取上周一的日期字符串
	 *
	 * @return
	 */
	public static String getMondayStringOfPreviousWeek()
	{
		return(_sdf.format( getMondayOfPreviousWeek() ) );
	}


	/**
	 * 获取上周日的日期
	 *
	 * @return
	 */
	public static Date getSundayOfPreviousWeek()
	{
		int			dValue		= getDofTodayToSun() - _weekDays;
		GregorianCalendar	currentDate	= new GregorianCalendar();
		currentDate.add( GregorianCalendar.DATE, dValue );
		Date	sunday	= currentDate.getTime();
		String	source	= _sdf.format( sunday );
		try {
			sunday = _sdf.parse( source );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return(sunday);
	}


	/**
	 * 获取上周日的日期字符串
	 *
	 * @return
	 */
	public static String getSundayStringOfPreviousWeek()
	{
		return(_sdf.format( getSundayOfPreviousWeek() ) );
	}


	/**
	 * 获取下周一的日期
	 *
	 * @return
	 */
	public static Date getMondayOfNextWeek()
	{
		int			dValue		= getDofTodayToPreSun() + _weekDays;
		GregorianCalendar	currentDate	= new GregorianCalendar();
		currentDate.add( GregorianCalendar.DATE, dValue );
		Date	monday	= currentDate.getTime();
		String	source	= _sdf.format( monday );
		try {
			monday = _sdf.parse( source );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return(monday);
	}


	/**
	 * 获取下周一的日期字符串
	 *
	 * @return
	 */
	public static String getMondayStringOfNextWeek()
	{
		return(_sdf.format( getMondayOfNextWeek() ) );
	}


	/**
	 * 获取下周日的日期
	 *
	 * @return
	 */
	public static Date getSundayOfNextWeek()
	{
		int			dValue		= getDofTodayToSun() + _weekDays;
		GregorianCalendar	currentDate	= new GregorianCalendar();
		currentDate.add( GregorianCalendar.DATE, dValue );
		Date	sunday	= currentDate.getTime();
		String	source	= _sdf.format( sunday );
		try {
			sunday = _sdf.parse( source );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return(sunday);
	}


	/**
	 * 获取下周日的日期字符串
	 *
	 * @return
	 */
	public static String getSundayStringOfNextWeek()
	{
		return(_sdf.format( getSundayOfNextWeek() ) );
	}


	/**
	 * 获取今天的日期
	 *
	 * @return
	 */
	public static Date getToday()
	{
		Date	date	= new Date();
		String	dateStr = _sdf.format( date );
		Date	today	= null;
		try {
			today = _sdf.parse( dateStr );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return(today);
	}


	/**
	 * 获取今天的日期字符串
	 *
	 * @return
	 */
	public static String getTodayString()
	{
		return(_sdf.format( getToday() ) );
	}


	/**
	 * 获取昨天的日期
	 *
	 * @return
	 */
	public static Date getYesterday()
	{
		Calendar currentDate = Calendar.getInstance();
		currentDate.add( Calendar.DATE, -1 );
		Date	yesterday	= currentDate.getTime();
		String	source		= _sdf.format( yesterday );
		try {
			yesterday = _sdf.parse( source );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return(yesterday);
	}


	/**
	 * 获取昨天的日期字符串
	 *
	 * @return
	 */
	public static String getYesterdayString()
	{
		return(_sdf.format( getYesterday() ) );
	}


	/**
	 * 获取明天的日期
	 *
	 * @return
	 */
	public static Date getTomorrow()
	{
		Calendar currentDate = Calendar.getInstance();
		currentDate.add( Calendar.DATE, 1 );
		Date	tomorrow	= currentDate.getTime();
		String	source		= _sdf.format( tomorrow );
		try {
			tomorrow = _sdf.parse( source );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return(tomorrow);
	}


	/**
	 * 获取明天的日期字符串
	 *
	 * @return
	 */
	public static String getTomorrowString()
	{
		return(_sdf.format( getTomorrow() ) );
	}


	/**
	 * 获取本月第一天的日期
	 *
	 * @return
	 */
	public static Date getFirstDayOfCurrentMonth()
	{
		Calendar currentDate = Calendar.getInstance();
		currentDate.set( Calendar.DATE, 1 );
		Date	day	= currentDate.getTime();
		String	source	= _sdf.format( day );
		try {
			day = _sdf.parse( source );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return(day);
	}


	/**
	 * 获取本月第一天日期字符串
	 *
	 * @return
	 */
	public static String getFirstDayStringOfCurrentMonth()
	{
		return(_sdf.format( getFirstDayOfCurrentMonth() ) );
	}


	/**
	 * 获取本月最后一天的日期
	 *
	 * @return
	 */
	public static Date getLastDayOfCurrentMonth()
	{
		Calendar currentDate = Calendar.getInstance();
		currentDate.add( Calendar.MONTH, 1 );
		currentDate.set( Calendar.DATE, 1 );
		currentDate.add( Calendar.DATE, -1 );
		Date	day	= currentDate.getTime();
		String	source	= _sdf.format( day );
		try {
			day = _sdf.parse( source );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return(day);
	}


	/**
	 * 获取本月最后一天日期字符串
	 *
	 * @return
	 */
	public static String getLastDayStringOfCurrentMonth()
	{
		return(_sdf.format( getLastDayOfCurrentMonth() ) );
	}


	/**
	 * 获取上个月第一天的日期
	 *
	 * @return
	 */
	public static Date getFirstDayOfPreviousMonth()
	{
		Calendar currentDate = Calendar.getInstance();
		currentDate.add( Calendar.MONTH, -1 );
		currentDate.set( Calendar.DATE, 1 );
		Date	day	= currentDate.getTime();
		String	source	= _sdf.format( day );
		try {
			day = _sdf.parse( source );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return(day);
	}


	/**
	 * 获取上个月第一天的日期字符串
	 *
	 * @return
	 */
	public static String getFirstDayStringOfPreviousMonth()
	{
		return(_sdf.format( getFirstDayOfPreviousMonth() ) );
	}


	/**
	 * 获取上个月最后一天的日期
	 *
	 * @return
	 */
	public static Date getLastDayOfPreviousMonth()
	{
		Calendar currentDate = Calendar.getInstance();
		currentDate.set( Calendar.DATE, 1 );
		currentDate.add( Calendar.DATE, -1 );
		Date	day	= currentDate.getTime();
		String	source	= _sdf.format( day );
		try {
			day = _sdf.parse( source );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return(day);
	}


	/**
	 * 获取上个月最后一天日期字符串
	 *
	 * @return
	 */
	public static String getLastDayStringOfPreviousMonth()
	{
		return(_sdf.format( getLastDayOfPreviousMonth() ) );
	}


	/**
	 * 获取下个月第一天的日期
	 *
	 * @return
	 */
	public static Date getFirstDayOfNextMonth()
	{
		Calendar currentDate = Calendar.getInstance();
		currentDate.add( Calendar.MONTH, 1 );
		currentDate.set( Calendar.DATE, 1 );
		Date	day	= currentDate.getTime();
		String	source	= _sdf.format( day );
		try {
			day = _sdf.parse( source );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return(day);
	}


	/**
	 * 获取下个月第一天的日期字符串
	 *
	 * @return
	 */
	public static String getFirstDayStringOfNextMonth()
	{
		return(_sdf.format( getFirstDayOfNextMonth() ) );
	}


	/**
	 * 获取下个月最后一天的日期
	 *
	 * @return
	 */
	public static Date getLastDayOfNextMonth()
	{
		Calendar currentDate = Calendar.getInstance();
		currentDate.add( Calendar.MONTH, 2 );
		currentDate.set( Calendar.DATE, 1 );
		currentDate.add( Calendar.DATE, -1 );
		Date	day	= currentDate.getTime();
		String	source	= _sdf.format( day );
		try {
			day = _sdf.parse( source );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return(day);
	}


	/**
	 * 获取下个月最后一天日期字符串
	 *
	 * @return
	 */
	public static String getLastDayStringOfNextMonth()
	{
		return(_sdf.format( getLastDayOfNextMonth() ) );
	}


	/**
	 * 获得给定日期的星期字符串，如"星期三"
	 *
	 * @param date
	 * @return
	 */
	public static String getWeekString( Date date )
	{
		String week = "";
		if ( date != null )
		{
			Calendar c = Calendar.getInstance();
			c.setTime( date );
			int n = c.get( Calendar.DAY_OF_WEEK );
			switch ( n )
			{
			case 1:
				week = "星期日";
				break;
			case 2:
				week = "星期一";
				break;
			case 3:
				week = "星期二";
				break;
			case 4:
				week = "星期三";
				break;
			case 5:
				week = "星期四";
				break;
			case 6:
				week = "星期五";
				break;
			case 7:
				week = "星期六";
				break;
			default:
				break;
			}
		}
		return(week);
	}


	/**
	 * 获取两个日期内的天数
	 *
	 * @param fromDate
	 * @param toDate
	 * @param containSat
	 *            包含周六?
	 * @param containSun
	 *            包含周日?
	 * @return
	 */
	public static long daysBetween( Date fromDate, Date toDate,
					boolean containSat, boolean containSun )
	{
		long days = 0;
		if ( fromDate != null && toDate != null )
		{
			long times = toDate.getTime() - fromDate.getTime();
			if ( times == 0 ) /* 同一天 */
			{
				return(days);
			}
			days = times / (3600 * 24 * 1000);
			if ( !containSat )
			{
				days -= getSaturdays( fromDate, toDate );
			}
			if ( !containSun )
			{
				days -= getSundays( fromDate, toDate );
			}
		}
		return(days);
	}


	/**
	 * 获取两个日期内的天数
	 *
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static long daysBetween( Date fromDate, Date toDate )
	{
		long days = 0;
		if ( fromDate != null && toDate != null )
		{
			long times = toDate.getTime() - fromDate.getTime();
			if ( times == 0 ) /* 同一天 */
			{
				return(days);
			}
			days = times / (3600 * 24 * 1000);
		}
		return(days);
	}


	/**
	 * 给定日期是否为星期六
	 *
	 * @param date
	 * @return
	 */
	public static boolean isSaturday( Date date )
	{
		boolean		is	= false;
		Calendar	c	= Calendar.getInstance();
		c.setTime( date );
		int day = c.get( Calendar.DAY_OF_WEEK );
		if ( 7 == day )
			is = true;
		return(is);
	}


	/**
	 * 给定日期是否为星期日
	 *
	 * @param date
	 * @return
	 */
	public static boolean isSunday( Date date )
	{
		boolean		is	= false;
		Calendar	c	= Calendar.getInstance();
		c.setTime( date );
		int day = c.get( Calendar.DAY_OF_WEEK );
		if ( 1 == day )
			is = true;
		return(is);
	}


	/**
	 * 返回给定日期的下一天日期
	 *
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDays( Date date, int amount )
	{
		Calendar c = Calendar.getInstance();
		c.setTime( date );
		c.add( Calendar.DATE, amount );
		return(c.getTime() );
	}


	/**
	 * 获取给定时间段内的周六天数
	 *
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static long getSaturdays( Date fromDate, Date toDate )
	{
		long	days		= 0;
		Date	currentDate	= fromDate;
		String	toDateStr	= _sdf.format( toDate );
		try {
			toDate = _sdf.parse( toDateStr );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		if ( isSaturday( toDate ) )
		{
			days += 1;
		}
		do
		{
			if ( isSaturday( currentDate ) )
			{
				days += 1;
			}
			currentDate = addDays( currentDate, 1 );
		}
		while ( currentDate.before( toDate ) );
		return(days);
	}


	/**
	 * 获取给定时间段内的周日天数
	 *
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static long getSundays( Date fromDate, Date toDate )
	{
		long	days		= 0;
		Date	currentDate	= fromDate;
		String	toDateStr	= _sdf.format( toDate );
		try {
			toDate = _sdf.parse( toDateStr );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		if ( isSunday( toDate ) )
		{
			days += 1;
		}
		do
		{
			if ( isSunday( currentDate ) )
			{
				days += 1;
			}
			currentDate = addDays( currentDate, 1 );
		}
		while ( currentDate.before( toDate ) );
		return(days);
	}


	/**
	 * 获得两个日期相隔的毫秒数
	 *
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static long millisBetween( Date fromDate, Date toDate )
	{
		long millis = 0;
		if ( fromDate != null && toDate != null )
		{
			if ( toDate.after( fromDate ) )
			{
				millis = toDate.getTime() - fromDate.getTime();
			}
		}
		return(millis);
	}


	/**
	 * 将给定的毫秒转换成XX天XX小时XX分钟XX秒
	 *
	 * @param millis
	 * @return
	 */
	public static String getDetailTimeByMillis( long millis )
	{
		int	second	= 1000;
		int	minute	= second * 60;
		int	hour	= minute * 60;
		int	day	= hour * 24;
		long	days	= millis / day;
		long	hours	= (millis - days * day) / hour;
		long	minutes = (millis - days * day - hours * hour) / minute;
		long	seconds = (millis - days * day - hours * hour - minutes * minute)
				  / second;
		return(days + "天" + hours + "小时" + minutes + "分钟" + seconds + "秒");
	}


	/**
	 * 获取给定日期的最后时刻，返回如：2010-11-11 23:59:59 的日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastTimeOfDay( Date date )
	{
		Date rtn = new Date();
		if ( date != null )
		{
			String		source	= _sdf.format( date );
			Calendar	c	= Calendar.getInstance();
			try {
				c.setTime( _sdf.parse( source ) );
			} catch ( ParseException e ) {
				e.printStackTrace();
			}
			c.set( Calendar.HOUR, 23 );
			c.set( Calendar.MINUTE, 59 );
			c.set( Calendar.SECOND, 59 );
			rtn = c.getTime();
		}
		return(rtn);
	}


	/**
	 * 根据给定日期字符串生成当天最后时刻的日期，如给定"2010-11-11"，则返回的日期为"2010-11-11 23:59:59"
	 *
	 * @param dateStr
	 * @return
	 */
	public static Date getLastTimeOfDay( String dateString )
	{
		Date date = new Date();
		if ( dateString != null && !"".equals( dateString ) )
		{
			try {
				date = _sdf24.parse( dateString );
			} catch ( ParseException e ) {
				e.printStackTrace();
			}
		}
		Calendar c = Calendar.getInstance();
		c.setTime( date );
		c.set( Calendar.HOUR, 23 );
		c.set( Calendar.MINUTE, 59 );
		c.set( Calendar.SECOND, 59 );
		return(date);
	}


	/**
	 * 获取给定日期字符串的最后时刻串，返回如：2010-11-11 23:59:59 的字符串
	 *
	 * @param dateStr
	 * @return
	 */
	public static String getLastTimeStringOfDay( String dateString )
	{
		return(_sdf24.format( getLastTimeOfDay( dateString ) ) );
	}


	/**
	 * 在原有时间的基础上添加相应的时间,返回一个新的时间
	 * @param aoDate
	 * @param aiAddYear
	 * @param aiAddMonth
	 * @param aiAddDays
	 * @param aiHours
	 * @return
	 */
	public static java.util.Date addTime( java.util.Date aoDate, int aiAddYear, int aiAddMonth, int aiAddDays, int aiHours, int aiMinute, int aiSecond )
	{
		Calendar oCalendar = Calendar.getInstance();
		oCalendar.setTime( aoDate );
		oCalendar.add( Calendar.YEAR, aiAddYear );
		oCalendar.add( Calendar.MONTH, aiAddMonth );
		oCalendar.add( Calendar.DATE, aiAddDays );
		oCalendar.add( Calendar.HOUR, aiHours );
		oCalendar.add( Calendar.MINUTE, aiMinute );
		oCalendar.add( Calendar.SECOND, aiSecond );
		return(oCalendar.getTime() );
	}


	/* 用于把timeinmil的时间戳转换为   xx时：xx分：xx秒  的计时 */
	public String parseTime( long time )
	{
		time = time / 1000;
		long	hour	= time / 3600;          /* !小时 */
		long	minute	= time % 3600 / 60;     /* !分钟 */
		long	second	= time % 60;            /* !秒 */
		return(hour + ":" + minute + ":" + second);
	}


	/* ----------------------yumaochun  自定义日期开始----------------------------- */

	public static synchronized String getPartString()
	{
		SimpleDateFormat	sdf	= new SimpleDateFormat( "yyyy/MM/dd" );
		String			res	= sdf.format( new Date() );
		sdf = null;
		return(res);
	}


	public static synchronized String getPartString( Date date )
	{
		SimpleDateFormat	sdf	= new SimpleDateFormat( "yyyy/MM/dd" );
		String			res	= sdf.format( date );
		sdf = null;
		return(res);
	}


	/**
	 *
	 * fun:获取当前系统时间date  如：2015-01-01  "-"表示变量split
	 *
	 * @author   yumaochun
	 * @date     2015-5-7
	 *
	 * @param split  日期分隔符 如："-","/"
	 * @return String 返回当前格式化后的日期
	 */
	public static synchronized String getCurrentDate( String split )
	{
		SimpleDateFormat	sdf	= new SimpleDateFormat( "yyyy" + split + "MM" + split + "dd" );
		String			res	= sdf.format( new Date() );
		sdf = null;
		return(res);
	}


	public static synchronized String getPartString( String split, int daycount )
	{
		SimpleDateFormat	sdf	= new SimpleDateFormat( "yyyy" + split + "MM" + split + "dd" );
		long			now	= System.currentTimeMillis();
		now -= 1L * daycount * 24 * 60 * 60 * 1000;
		String res = sdf.format( new Date( now ) );
		sdf = null;
		return(res);
	}


	public static synchronized String getPartString( Date date, String split )
	{
		SimpleDateFormat	sdf	= new SimpleDateFormat( "yyyy" + split + "MM" + split + "dd" );
		String			res	= sdf.format( date );
		sdf = null;
		return(res);
	}


	/**
	 *
	 * fun:获取当前系统日期，自定义分割符，格式化为："yyyy"+split+"MM"+split+"dd HH:mm:ss"
	 *
	 * @author   yumaochun
	 * @date     2015-5-7
	 *
	 * @param split 日期分割符  如("-")：2015-01-01
	 * @return String 返回当前格式化的日期  如：2015-01-01
	 */
	public static synchronized String getCurrentDatetime( String split )
	{
		SimpleDateFormat	sdf	= new SimpleDateFormat( "yyyy" + split + "MM" + split + "dd HH:mm:ss" );
		String			res	= sdf.format( new Date() );
		sdf = null;
		return(res);
	}


	/**
	 *
	 * fun:获取当前系统日期，自定义分割符，格式化为："yyyy"+split+"MM"+split+"dd HH:mm:ss"
	 *     以及自定义时间：new Date();
	 *
	 * @author   yumaochun
	 * @date     2015-5-7
	 *
	 * @param date   时间 如：new Date();
	 * @param split  日期分割符  如("-")：2015-01-01
	 * @return string 返回当前格式化的日期  如：2015-01-01
	 */
	public static synchronized String getDatetime( Date date, String split )
	{
		SimpleDateFormat	sdf	= new SimpleDateFormat( "yyyy" + split + "MM" + split + "dd HH:mm:ss" );
		String			res	= sdf.format( date );
		sdf = null;
		return(res);
	}


	public static synchronized String getDatetime()
	{
		SimpleDateFormat	sdf	= new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
		String			res	= sdf.format( new Date() );
		sdf = null;
		return(res);
	}


	public static synchronized String getDatetime( Date date )
	{
		SimpleDateFormat	sdf	= new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
		String			res	= sdf.format( date );
		sdf = null;
		return(res);
	}


	public static synchronized int getYear()
	{
		Date		date	= new Date();
		Calendar	c	= Calendar.getInstance();
		c.setTime( date );
		int res = c.get( Calendar.YEAR );
		c	= null;
		date	= null;
		return(res);
	}


	public static synchronized int getYear( Date date )
	{
		Calendar c = Calendar.getInstance();
		c.setTime( date );
		int res = c.get( Calendar.YEAR );
		c = null;
		return(res);
	}


	public static synchronized String getYear( String date, String split )
	{
		String result = "";
		if ( date != null )
		{
			String[] dts = date.split( split );
			if ( dts.length >= 1 )
				result = dts[0];
			else
				result = "";
			dts = null;
		}else
			result = "";
		return(result);
	}


	public static synchronized String getMonth( String date, String split )
	{
		String result = "";
		if ( date != null )
		{
			String[] dts = date.split( split );
			if ( dts.length >= 2 )
				result = dts[1];
			else
				result = "";
			dts = null;
		}else
			result = "";
		return(result);
	}


	public static synchronized String getDay( String date, String split )
	{
		String result = "";
		if ( date != null )
		{
			String[] dts = date.split( split );
			if ( dts.length >= 3 )
				result = dts[2];
			else
				result = "";
			dts = null;
		}else
			result = "";
		return(result);
	}


	public static synchronized String getCurMonthFrom( String split )
	{
		Calendar	c	= Calendar.getInstance();
		int		y	= c.get( Calendar.YEAR );
		int		m	= 1 + c.get( Calendar.MONTH );
		c = null;
		String res = y + split + (m < 10 ? "0" + m : m + "") + split + "01";
		c = null;
		return(res);
	}


	public static synchronized String getCurMonthTo( String split )
	{
		Calendar	c	= Calendar.getInstance();
		int		y	= c.get( Calendar.YEAR );
		int		m	= 1 + c.get( Calendar.MONTH );
		int		nm, ny, nd;
		if ( m == 12 )
		{
			nm	= 0;
			ny	= y + 1;
		}else  {
			ny	= y;
			nm	= m + 1;
		}
		c.set( ny, nm, 1 );
		long l = c.getTimeInMillis();
		l -= 1L * 24 * 60 * 60 * 1000;
		c.setTimeInMillis( l );
		ny	= c.get( Calendar.YEAR );
		nm	= c.get( Calendar.MONTH ) + 1;
		nd	= c.get( Calendar.DAY_OF_MONTH );
		c	= null;
		return(ny + split + (nm < 10 ? "0" + nm : nm + "") + split + (nd < 10 ? "0" + nd : nd + "") );
	}


	/**
	 * 两时间对比, 相差是否超过XX秒
	 *
	 * @param dateA
	 * @param dateB
	 * @return
	 * @throws ParseException
	 */
	public static boolean compare( String dateA, String dateB, int miao ) throws ParseException
	{
		DateFormat df = new SimpleDateFormat( DATE_TIME_24HOURS_FORMAT );
		return(Math.abs( df.parse( dateA ).getTime() - df.parse( dateB ).getTime() ) > miao);
	}


	/**
	 * 比较两个日期之差
	 * @param dateA  较大的日期
	 * @param dateB  较小的日期
	 * @return  返回：两个日期之差
	 */
	public static Long compare( String dateA, String dateB )
	{
		Long mes = (long) 0;
		if ( dateA.length() > 2 && dateA.length() > 2 )
		{
			DateFormat df = new SimpleDateFormat( DATE_TIME_24HOURS_FORMAT );
			try {
				mes = Math.abs( df.parse( dateA ).getTime() - df.parse( dateB ).getTime() );
			} catch ( ParseException e ) {
				/* TODO Auto-generated catch block */
				return(mes = (long) 0);
			}
		}
		return(mes);
	}


	/**
	 *
	 * fun:获取指定日期之后多少天的日期
	 *
	 * @author   yumaochun
	 * @date     2015-9-15
	 *
	 * @param specified      指定日期
	 * @param d              需增加的天数
	 * @return  string       返回日期
	 */
	public static String getSpecifiedDayAfter( String specified, int d )
	{
		Calendar	c	= Calendar.getInstance();
		Date		date	= null;
		try {
			date = new SimpleDateFormat( "yy-MM-dd" ).parse( specified );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		c.setTime( date );
		int day = c.get( Calendar.DATE );
		c.set( Calendar.DATE, day + d );
		String dayAfter = new SimpleDateFormat( "yyyy-MM-dd" ).format( c.getTime() );
		return(dayAfter);
	}


	/**
	 *
	 * fun:获取指定日期之前多少天的日期
	 *
	 * @author   yumaochun
	 * @date     2015-9-15
	 *
	 * @param specified      指定日期
	 * @param d              需减少的天数
	 * @return  string       返回日期
	 */
	public static String getSpecifiedDayBefore( String specified, int d )
	{
		Calendar	c	= Calendar.getInstance();
		Date		date	= null;
		try {
			date = new SimpleDateFormat( "yy-MM-dd" ).parse( specified );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		c.setTime( date );
		int day = c.get( Calendar.DATE );
		c.set( Calendar.DATE, day - d );
		String dayAfter = new SimpleDateFormat( "yyyy-MM-dd" ).format( c.getTime() );
		return(dayAfter);
	}


	/**
	 *
	 * fun:获取自定义格式化的日期
	 *
	 * @author   yumaochun
	 * @date     2015-9-28
	 *
	 * @param format         格式化规格
	 * @return   返回日期字符串
	 */
	public static String getSpecifiedDateFormat( String format )
	{
		SimpleDateFormat	sdf	= new SimpleDateFormat( format );
		String			res	= sdf.format( new Date() );
		sdf = null;
		return(res);
	}



	/**
	 *
	 * addOrReduceMonth:日期月份的添加和减少
	 *
	 * @date 2016年3月30日
	 * @param date         待处理的日期 ，如：2016-03-21
	 * @param month        需要增加或减少的月份数，添加分月数-》1，减少月份数-》-1
	 * @return  返回：2016-04-21
	 * @throws ParseException
	 */
	public static String addOrReduceMonth( String date, int month ) throws ParseException
	{
		SimpleDateFormat	sdf		= new SimpleDateFormat( "yyyy-MM-dd" );
		Date			dt		= sdf.parse( date );
		Calendar		rightNow	= Calendar.getInstance();
		rightNow.setTime( dt );
		//增加年份
		int year=month/12;
		//增加月份
		month=month%12;
		rightNow.add( Calendar.YEAR, year );
		rightNow.add( Calendar.MONTH, month );

		Date	dt1	= rightNow.getTime();
		String	reStr	= sdf.format( dt1 );
		return(reStr);
	}


    /**
     * 
     * getMonthAndDaysBetweenDate:获取两个日期之间的月和天数
     *     
     * @date 2016年4月1日
     * @param date1          起始日期
     * @param date2          结束日期
     * @return   返回：   map<月份数，天数>(months,days)
     * @throws ParseException
     */
	public static Map<String, Integer> getMonthAndDaysBetweenDate( String date1, String date2 ) throws ParseException
	{
		//结束日期往后延迟一天，避免日期见面多一天
		date2=getSpecifiedDayAfter(date2,1);
		
		Map<String, Integer>  map	= new HashMap();
		SimpleDateFormat sd	= new SimpleDateFormat( "yyyy-MM-dd" );
		Date d1	= null;
		try {
			d1 = sd.parse( date1 );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		Date d2 = null;
		try {
			d2 = sd.parse( date2 );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
        //相差月份 
		int	months	= 0;
		//相差天数
		int	days	= 0;
		int	y1	= d1.getYear();
		int	y2	= d2.getYear();
		int	dm1	= d2.getMonth();        /* 起始日期月份 */
		int	dm2	= d2.getMonth();        /* 结束日期月份 */
		int	dd1	= d1.getDate();         /* 起始日期天 */
		int	dd2	= d2.getDate();         /* 结束日期天 */
		if ( d1.getTime() < d2.getTime() )
		{
			//月份数
			months = d2.getMonth() - d1.getMonth() + (y2 - y1) * 12;
            
			if ( dd2 < dd1 )
			{
				//当起始日期天数大于结束日期天数，则月份要减1
				months = months - 1;
			}
			else{
				String newDate=getSpecifiedDayAfter(date2,-1);
				//自然月结尾（当月最后一天）
				if(isMonthLastDay(newDate)){
					//起始日期是1号
					if(dd1==1){
						//months = months;
					}else{
						months = months - 1;
					}
				}
			}
			
			//增加月份数后的日期
			String date = addOrReduceMonth( date1, months );
           
			//System.out.println( "新生产的日期："+date );
			//获取两日期内的天数
			days=daysBetween(date,date2);
			//System.out.println( "相差月数："+months+"，相差天数："+days );
			map.put( "months", months );
			map.put( "days", days );
		}
		return(map);
	}

    /**
     * 
     * parseDate:将日期字符串转换为new date()
     *
     * @date 2016年4月1日
     * @param dateString               日期字符串  如：2016-03-02
     * @param dateFormate              日期格式化格式  如：yyyy-MM-dd
     * @return  返回：new Date()
     */
	public final static Date parseDate( String dateString, String dateFormate )
	{
		SimpleDateFormat	sd	= new SimpleDateFormat( dateFormate );
		Date			date	= null;
		try {
			date = sd.parse( dateString );
		} catch ( Exception ex ) {
			logger.error( "Pase the Date(" + dateString + ") occur errors:"
				      + ex.getMessage() );
		}
		return(date);
	}
	
	/**
	 * 
	 * getDate:根据日期字符串，获取日期
	 *
	 * @date 2016年4月1日
	 * @param str    日期字符串 如：2016-03-02
	 * @return  new Date()
	 */
	public static Date getDate(String str){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date=simpleDateFormat.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.info("将日期字符串转换为日期异常");
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 
	 * getDate:根据日期字符串，获取日期
	 *
	 * @date 2016年4月1日
	 * @param str    日期字符串 如：2016-03-02
	 * @param fmt 指定格式
	 * @return  new Date()
	 */
	public static Date getDate(String str,String fmt){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(fmt);
		Date date=null;
		try {
			date=simpleDateFormat.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.info("将日期字符串转换为日期异常");
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 
	 * dateConvertToStr:将date转换为日期字符串格式（格式化日期）
	 *
	 * @date 2016年4月1日
	 * @param date         日期时间
	 * @param fmt          日期格式化规则（yyyy-MM-dd）
	 * @return  返回：日期字符串（2016-03-21）
	 */
	public static String dateConvertToStr(Date date,String fmt){
		//格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        String dateStr = sdf.format(date);
		return dateStr;
	}
	
	/**
	 * 
	 * getLastDay:根据指定日期获取当月最后一天的日期
	 *
	 * @date 2016年4月1日    
	 * @param str      指定的日期         2016-03-23
	 * @param fmt      返回日期的格式化（yyyy-MM-dd）
	 * @return         返回：最后一天的日期(2016-03-31)
	 */
	public static String getLastDay(String str,String fmt) {
		
        Calendar calendar = Calendar.getInstance();
        Date date=getDate(str);
        //设置日期
        calendar.setTime(date);
        /**
         * Calendar.Date:表示一个月中的某天
         * calendar.getActualMaximum(int field):返回指定日历字段可能拥有的最大值
         */
        int maxDay = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, maxDay);
        //格式化日期
        String dateStr=dateConvertToStr(calendar.getTime(),"yyyy-MM-dd");
		return dateStr;
	}
	/**
	 * 
	 * isMonthLastDay:判断是否是当前日期月份最后一天
	 *
	 * @date 2016年4月1日
	 * @param str                  日期字符串
	 * @return  返回：true-是月份最后一天，false-不是月份最后一天
	 */
	public static boolean isMonthLastDay(String str){
		boolean flag=false;
		Calendar calendar = Calendar.getInstance();
        Date date=getDate(str);
        //当前日期号数
        int currentDay=date.getDate();
        //设置日期
        calendar.setTime(date);
        /**
         * Calendar.Date:表示一个月中的某天
         * calendar.getActualMaximum(int field):返回指定日历字段可能拥有的最大值
         */
        int maxDay = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, maxDay);
        if(currentDay==maxDay){
        	//是最后一天
        	return true;
        }
		
		return flag;
	}
	/**
	 * 
	 * getMonthLastDate:获取当前日期当月最后一天的日期
	 *
	 * @date 2016年4月5日
	 * @param str          日期字符串：2016-03-11
	 * @return  return 2016-03-31
	 */
	public static String getMonthLastDate(String str){
		Calendar calendar = Calendar.getInstance();
        Date date=getDate(str);
        //设置日期
        calendar.setTime(date);
        /**
         * Calendar.Date:表示一个月中的某天
         * calendar.getActualMaximum(int field):返回指定日历字段可能拥有的最大值
         */
        int maxDay = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, maxDay);
        
        SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd");
        String newDate=smf.format(calendar.getTime());
		return newDate;
	}
    

	/**
	 * 
	 * daysBetween:计算两日期间隔的天数
	 *
	 * @date 2016年4月1日
	 * @param smdate       起始日期
	 * @param bdate        结束日期
	 * @return     返回：日期间隔的天数
	 * @throws ParseException
	 */
	public static int daysBetween( String smdate, String bdate ){
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance();
		int days=0;
		try {
			cal.setTime( sdf.parse( smdate ) );
			long time1 = cal.getTimeInMillis();
			cal.setTime( sdf.parse( bdate ) );
			long time2= cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			//相差天数
			days=Integer.parseInt( String.valueOf( between_days ) );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return days;
	}
	/**
	 * 
	 * convertToDate:将日期字符串转换为时间搓
	 *
	 * @date 2016年4月1日
	 * @param str     待转换的日期字符串
	 * @return  返回：日期时间搓
	 */
	public static long convertToDate(String str){
		Date date=getDate(str);
		long time=date.getTime();
		return time;
	}
	/**
	 * 
	 * dateStrIsDateRange:判断日期是否在指定的日期范围内
	 *
	 * @date 2016年4月1日
	 * @param str              要判断的日期
	 * @param startDate        日期范围起始日期
	 * @param endDate          日期范围结束日期
	 * @return   返回：true-在日期范围中，false-不在日期范围中
	 */
	public static boolean dateStrIsDateRange(String str,String start,String end){
		long strDate=convertToDate(str);
		long startDate=convertToDate(start);
		long endDate=convertToDate(end);
		
		if(strDate>=startDate&&strDate<=endDate){
			//日期在指定日期范围内
			//System.out.println("日期"+str+"是在指定范围内["+start+","+end+"]");
			return true;
		}
		//System.out.println("日期"+str+"不在指定范围内["+start+","+end+"]");
		return false;
	}

	public static void main( String[] args ) throws ParseException
	{
        
		//Map<String, Integer>	map	= DateUtil.getMonthAndDaysBetweenDate( "2015-01-01", "2016-03-31" );
		//int	months = map.get("months");      /* 月数 */
		//int	days = map.get("days").intValue();      /* 天数 */
		//System.out.println( months + "月," + days );
		//boolean flag=dateStrIsDateRange("2016-03-31","2015-01-01", "2016-03-31" );
		//System.out.println(addOrReduceMonth("2015-11-30",3));
		//System.err.println(getDate("20160531","yyyyMMdd"));
		//System.out.println(getSqlTime("10:00:01","hh:mm:ss"));
		System.out.println(compareDate(new Date(), getDate("2016-06-24 05:39:36", "yyyy-MM-dd HH:mm:ss")));

	}

	/**
	 * 时间戳转换为日期
	 * @param beginDate 时间戳
	 * @param fat 日期格式
	 * @return
	 */
	public static Date getDateByDateLong(long beginDate,String fat){
		SimpleDateFormat sdf=new SimpleDateFormat(fat);
		try {
			return sdf.parse(sdf.format(beginDate));
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return null;
	}
	
	/**
	 * 时间戳转换为日期字符串
	 * @param beginDate 时间戳
	 * @param fat 日期格式
	 * @return
	 */
	public static String getDateByDateLongStr(long beginDate,String fat){
		SimpleDateFormat sdf=new SimpleDateFormat(fat);
		try {
			return sdf.format(beginDate);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return null;
	}
	
	/**
	 * 转为sqlTime
	 * @param time 字符串时间
	 * @return
	 */
	public static Time getSqlTime(String time,String fmt){
		try {
			SimpleDateFormat dateFormat=new SimpleDateFormat(fmt);
			Date date=dateFormat.parse(time);
			//java.sql.Time sqltime时间格式的转换
		    return new Time(date.getTime()).valueOf(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Time(new Date().getTime());
	}
	
	/**
	 * 获取格式化日期
	 * @param date
	 * @param fmt
	 * @return
	 */
	public static Date formatDate(Date date,String fmt){
		try {
			SimpleDateFormat format=new SimpleDateFormat(fmt);
			return format.parse(format.format(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	/**
	 * 比较两个时间大小 1-time1大  -1-time2大  0相等
	 * @param time1 时间一
	 * @param time2 时间二
	 * @param replace 替换符号
	 * @return
	 */
	public static int compareTime(Time time1,Time time2){
		try {
			return time1.compareTo(time2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 比较两个日期大小
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return
	 */
	public static int compareDate(Date date1,Date date2){
		return date1.compareTo(date2);
	}
	
	/**
	 * 获取时间戳+6位随机数
	 * @return
	 */
	public static BigDecimal getCurrRan(){
		Long currentTimeMillis = System.currentTimeMillis();
		Random random=new Random();
		Integer r=random.nextInt(5)+1;
		return new BigDecimal(currentTimeMillis.toString()+r.toString());
	}
	
	/**
	 * 获取时间戳+6位随机数
	 * @return
	 */
	public static String getCurrRanStr(){
		Long currentTimeMillis = System.currentTimeMillis();
		Random random=new Random();
		Integer r=random.nextInt(5)+1;
		return currentTimeMillis.toString()+r.toString();
	}
	
	public static boolean getTilidState(Date d){
		PropertiesUtil p = new PropertiesUtil("systemConfig.properties");
		String updateTimeStr=p.readProperty("tilidUpDateTime");
		Date curDate=new Date();
		long curTime=curDate.getTime();
		long updateTime=Long.parseLong(updateTimeStr)*60*1000+30*1000;
		if(!((curTime-d.getTime())>updateTime))return true;
		return  false;
	}
	public static Date newSalesRule(Date txtdateD,Time txtimeD){
		PropertiesUtil p = new PropertiesUtil("systemConfig.properties");
		String newSalesRule=p.readProperty("newSalesRule");
		if("1".equals(newSalesRule)){
				Long txtime=Long.parseLong(txtimeD.toString().replaceAll(":", ""));
				if(txtime<60000){
					Calendar calendar = Calendar.getInstance();    
					calendar.setTime(txtdateD);
				    calendar.set(Calendar.HOUR,0);//小时设置为0  
			        calendar.set(Calendar.MINUTE, 0);//分钟设置为0  
			        calendar.set(Calendar.SECOND, 0);//秒设置为0
					calendar.add(Calendar.DAY_OF_MONTH, -1);
					txtdateD=calendar.getTime();
				}
			return txtdateD;
		}else{
			return txtdateD;
		}
	}
	
	/**
     * 
     * getDateStr:根据date，获取时间字符串
     *
     * @author yumaochun
     * @date 2016年7月29日
     * @param date   date
     * @param split  分割符
     * @return   返回：格式化的时间
     */
	public static synchronized String getDateStr( Date date, String split )
	{
		SimpleDateFormat sdf	= new SimpleDateFormat( "yyyy" + split + "MM" + split + "dd" );
		String	res	= sdf.format( date );
		sdf = null;
		return(res);
	}


}
