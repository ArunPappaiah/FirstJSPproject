package com.chainsys.jspproject.commonutil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	public static boolean checkStringForParseInt(String data) throws InvalidInputDataException {
		boolean result = false;
		for (int i = 0; i < data.length(); i++) {
			int c = (int) data.charAt(i);
			if (!(c >= 48 && c <= 58)) {
				throw new InvalidInputDataException("The value in string must contain only numbers ");
			}
		}
		return result;
	}
	public static boolean checkSalaryLessThanTenDigit(float salary) throws InvalidInputDataException {
		boolean result = false;
		String d = Float.toString(salary);
		int count = 0;
		for (int i = 0; i < d.length(); i++) {
			if (d.charAt(i) != ' ') {
				count++;
			}
			if (count >= 10) {
				throw new InvalidInputDataException("The Digits of Number must be less then ten digit");
			}
		}
		return result;
	}

	public static boolean checkNumberForGreaterThanZero(float salary) throws InvalidInputDataException {
		boolean result = false;
		if(salary <= 0) {
			throw new InvalidInputDataException("The value must be greater then zero");
		}
		return result;
	} 

	public static boolean checkCharLessThanTwenty(String data) throws InvalidInputDataException {
		boolean result = false;
		int count = 0;
		for (int i = 0; i < data.length(); i++) {
			if (data.charAt(i) != ' ') {
				count++;
			}
			if (count >= 20) {
				throw new InvalidInputDataException("The value of character must be less then twenty digit");
			}
		}
		return result;
	}

	public static boolean checkMailContainsAtsymbol(String data) throws InvalidInputDataException {
		boolean result = false;
		Pattern p = Pattern.compile("^(.+)@(.+)$");
		Matcher m = p.matcher(data);

		if (!m.find()) {
			throw new InvalidInputDataException("The Input Data is not valid ");
		}
		return result;
	}

	
    public static boolean checkNumberLessThanTenDigits(int data) throws InvalidInputDataException{
    	boolean result =false;
    	int count =0;
    	while(data !=0) {
    		data =data/10;
    		count++;
    	}
    	if(count >10) {
    		throw new InvalidInputDataException("The Digits of Number must be less then ten digit");
    	}
    	return result;
    }
    
    public static boolean checkPhoneNumberLessThanTenDigits(String data) throws InvalidInputDataException {
    	boolean result = false;
		int len= data.length();
			if (!(len ==10)) {
				throw new InvalidInputDataException("The value of Numbers must be Equal of ten digit");
			}
		return result;
	}
    
	public static boolean checkDataOnlyString(String data) throws InvalidInputDataException {
		boolean result = false;
		for (int i = 0; i < data.length(); i++) {
			int c = (int) data.charAt(i);
			if (!((c >= 65 && c <= 90) || (c >= 97 && c < 122))) {
				throw new InvalidInputDataException("The value in string must contain only characters ");
			}
		}
		return result;
	}

	public static boolean checkSalaryOnlyNumbers(int data) throws InvalidInputDataException {
		boolean result = false;
		String d = Integer.toString(data);
		for (int i = 0; i < d.length(); i++) {
			int c = (int) d.charAt(i);
			if (!(c >= 48 && c <= 58)) {
				throw new InvalidInputDataException("The value in value should must contain only numbers ");
			}
		}
		return result;
	}
	
	public static void checkDate(String date) throws InvalidInputDataException {

		String pattern1 = "^((\\d{1})|(\\d{2}))/((\\d{1})|(\\d{2}))/\\d{4}$";
		if (!match(pattern1, date))
			throw new InvalidInputDataException("date must be number");

		String pattern2 = "^((\\d{1})|(\\d{2}))/(1[3-9]|[2-9][0-9])/\\d{4}$";
		if (match(pattern2, date))
			throw new InvalidInputDataException("month must be below 12");

		String pattern3 = "^((\\d{1})|(\\d{2}))/(0[13578]|1[02])/\\d{4}$";
		String pattern4 = "^(3[01]|[12][0-9]|0?[1-9])/(0?[13578]|1[02])/[0-9]{4}$";
		if (match(pattern3, date)) {
			if (!match(pattern4, date))
				throw new InvalidInputDataException("In this month day should be below 31");
		}

		String pattern5 = "^((\\d{1})|(\\d{2}))/(0?[469]|11)/(\\d{4})$";
		String pattern6 = "^((3[0]|[12][0-9]|0?[1-9]))/(0?[469]|11)/\\d{4}$";
		if (match(pattern5, date)) {
			if (!match(pattern6, date))
				throw new InvalidInputDataException("In this month day should be below 30");
		}

		String pattern7 = "((\\d{1})|(\\d{2}))/(2|02)/(([0-9][0-9][24680][048])|([0-9][0-9][13579][26]))$";
		String pattern8 = "^(([1][0-9]|0?[1-9])|(2[0-9]))/(2|02)/(([0-9][0-9][24680][048])|([0-9][0-9][13579][26]))$";
		if (match(pattern7, date))
			if (!match(pattern8, date))
				throw new InvalidInputDataException("In this month day should be below 29");

		String pattern9 = "((\\d{1})|(\\d{2}))/(2|02)/([0-9][0-9](([24680][1235679])|([13579][48013579])))$";
		String pattern10 = "^(([1][0-9]|0?[1-9])|(2[0-8]))/(2|02)/([0-9][0-9](([24680][1235679])|([13579][48013579])))$";
		if (match(pattern9, date))
			if (!match(pattern10, date))
				throw new InvalidInputDataException("In this month day should below 28");

	}

	public static void checkHireDate(String date) throws InvalidInputDataException {
		String pattern11 = "^((\\d{1})|(\\d{2}))/((\\d{1})|(\\d{2}))/((19[89][0-9])|(20([01][0-9])|20(2[0-2])))$";
		if (!match(pattern11, date))
			throw new InvalidInputDataException("hire date must be between 1980-2022");
	}

	public static void checkDOB(String date) throws InvalidInputDataException {
		String pattern12 = "^((\\d{1})|(\\d{2}))/((\\d{1})|(\\d{2}))/(((19(6[5-9])|(19[7-9][0-9]))|(200[0-2])))$";
		if (!match(pattern12, date))
			throw new InvalidInputDataException("Date of birth must between 1965 to 2002");
	}
	public static void checkAppointmentDate(String date) throws InvalidInputDataException {
		String pattern13 = "^((\\d{1})|(\\d{2}))/((\\d{1})|(\\d{2}))/(([0-1][0-9][0-9][0-9])|(20[01][0-9])|(202[01]))$";
		if (match(pattern13, date))
			throw new InvalidInputDataException("Appointment date must above 2022S");
	}

	public static boolean match(String pattern, String date) {
		Pattern patt = Pattern.compile(pattern);
		Matcher match = patt.matcher(date);
		return match.matches();
	   }
}
