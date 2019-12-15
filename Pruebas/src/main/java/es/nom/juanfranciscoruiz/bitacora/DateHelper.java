package es.nom.juanfranciscoruiz.bitacora;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {

	public static final String FIRST_MONTH_DAY = "FIRST_DAY";
	public static final String LAST_MONTH_DAY = "LAST_DAY";
	
	public static final String FORMAT_COMPLETE = "yyyyMMddHHmmss";
	public static final String FORMAT_CYCLE = "yyyyMM";
	public static final String FORMAT_DAY = "yyyyMMdd";
	
	private static SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
	
	/*
	private static final String ENDING_DAY_HOUR = "235959";
	private static final String BEGINING_DAY_HOUR = "000000";
	
	private static SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
	private static SimpleDateFormat formatComplete = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	*/
	
	public static Date getDefaultDate() {
		GregorianCalendar gc = new GregorianCalendar();
    	gc.set(Calendar.YEAR, 1900);
    	gc.set(Calendar.MONTH, 0);
    	gc.set(Calendar.DATE, 1);
    	return beginningDay(gc.getTime());
	}

	public static boolean isDateAttribute(String date) {
		// format XX/YY/ZZZZ
		if (date.length()!=10) return false;
		String[] s = date.split("/");
		if (s.length!=3) return false;
		if (s[0].length()!=2) return false;
		if (s[1].length()!=2) return false;
		if (s[2].length()!=4) return false;
		return true;
	}

	public static String translateDate(Date date, String stringFormat) {
		if 	(date==null) {
			return "";
		} else {
			SimpleDateFormat formateador = new SimpleDateFormat(stringFormat);
			return formateador.format(date);
		}
	}
	
	public static Integer translate2EffectiveDate(Date date, String stringFormat) {
		if 	(date==null) {
			return 0;
		} else {
			SimpleDateFormat formateador = new SimpleDateFormat(stringFormat);
			return translateAttributeDate2IntegerDate(formateador.format(date));
		}
	}
	
	public static Integer translateAttributeDate2IntegerDate(String date) throws NumberFormatException {
		// Received format yyyyMMdd
    	return Integer.parseInt(date);
	}
	
	public static Integer getMonthsBetween(Date maxDate, Date minDate) {
		GregorianCalendar max = new GregorianCalendar();
		GregorianCalendar min = new GregorianCalendar();
		max.setTime(maxDate);
		min.setTime(minDate);
		return ((((max.get(Calendar.YEAR))-(min.get(Calendar.YEAR)))*12)+
				((max.get(Calendar.MONTH))-(min.get(Calendar.MONTH))));
	}
	
	public static Date beginningDay(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		return gc.getTime();
	}
	
	public static Date beginningMonth(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.set(Calendar.DATE, 1);
		return beginningDay(gc.getTime());
	}
	
	public static Date endingDay(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.set(Calendar.HOUR_OF_DAY, 23);
		gc.set(Calendar.MINUTE, 59);
		gc.set(Calendar.SECOND, 59);
		gc.set(Calendar.MILLISECOND, 0);
		return gc.getTime();
	}
	
	public static Date endingMonth(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.set(Calendar.DATE, 1);
		gc.add(Calendar.MONTH, 1);
		gc.add(Calendar.DATE, -1);
		return endingDay(gc.getTime());
	}
	
	public static boolean isLastDayOfMonth(Date date) {
		if (date!=null && formatDate.format(date).equals(formatDate.format(endingMonth(date)))) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Date addYears(Date date, Integer years) {
		if ((years==null)||(years.equals(new Integer(0)))) return date;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.YEAR, years);
		return gc.getTime();
	}
	
	public static Date addMonths(Date date, Integer months) {
		if ((months==null)||(months.equals(new Integer(0)))) return date;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.MONTH, months);
		return gc.getTime();
	}
	
	public static Date addDays(Date date, Integer days) {
		if ((days==null)||(days.equals(new Integer(0)))) return date;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.DATE, days);
		return gc.getTime();
	}
	
	public static Date setDay(Date date, Integer day) {
		if ((day==null)||(day.equals(new Integer(0)))) return date;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.set(Calendar.DATE, day);
		return gc.getTime();
	}
	
	public static Date setNextDay(Date date, Date dayToSet) {
		GregorianCalendar gcDate = new GregorianCalendar();
		gcDate.setTime(date);
		GregorianCalendar gcDayToSet = new GregorianCalendar();
		gcDayToSet.setTime(dayToSet);
		if (gcDate.get(Calendar.DATE)<=gcDayToSet.get(Calendar.DATE)) {
			gcDate.set(Calendar.MONTH, (gcDayToSet.get(Calendar.MONTH)+1));
		}
		gcDate.set(Calendar.DATE, gcDayToSet.get(Calendar.DATE));
		return gcDate.getTime();
	}
	
	public static int getDateField(Date date, int field) {
		GregorianCalendar gcDate = new GregorianCalendar();
		gcDate.setTime(date);
		return gcDate.get(field);
	}
	
}
