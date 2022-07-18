package com.chainsys.jspproject.commonutil;

public class HtmlHelper {
   public static String getHtmlTemplate(String title,String body) {
	   String htmlOutput ="<html><head><title>"+title+"</title></head><body>";
	    htmlOutput += "<div>"+body+"</div></body></html>";
	   return htmlOutput;
   }
}