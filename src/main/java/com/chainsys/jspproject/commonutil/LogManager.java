package com.chainsys.jspproject.commonutil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LogManager {
	public static void logException(Exception ex, String source, String exMessage) {
		Calendar vCalendar = Calendar.getInstance();
		String logDate=vCalendar.get(Calendar.DATE)+"_"+(vCalendar.get(Calendar.MONTH)+1)+"_"+vCalendar.get(Calendar.YEAR);
		String logDateTime=logDate+"_"+vCalendar.get(Calendar.HOUR)+"_"+vCalendar.get(Calendar.MINUTE);
		if (source == null) {
			source = "Source Not Provided";
		}
		if (exMessage == null) {
			exMessage = "Costom Message Not Provided";
		}
		String message="Exception: "+logDateTime+" Message: "+ex.getMessage();
		message +="\n Source: "+source+" Custom message: "+exMessage+"\n";
		String fileName="ExceptionMessages"+logDate+".log";
		writeToFile(fileName,message);
	}

	public static void logException(Exception ex, String source) {
		Calendar vCalendar = Calendar.getInstance();
		String logDate=vCalendar.get(Calendar.DATE)+"_"+(vCalendar.get(Calendar.MONTH)+1)+"_"+vCalendar.get(Calendar.YEAR);
		String logDateTime=logDate+"_"+vCalendar.get(Calendar.HOUR)+"_"+vCalendar.get(Calendar.MINUTE);
		if (source == null) {
			source = "Source Not Provided";
		}
		String message="Exception: "+logDateTime+" Message: "+ex.getMessage();
		message +="\n Source: "+source +"\n";
		String fileName="ExceptionMessages"+logDate+".log";
		writeToFile(fileName,message);
	}

	private static void writeToFile(String fileName, String message) {
		
		fileName = "D:\\Demo\\LogManager"+fileName+".txt";
		FileWriter fileWriter=null;
		try {
			fileWriter = new FileWriter(fileName,true);
			fileWriter.write(message);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fileWriter.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
	}
		}
}
