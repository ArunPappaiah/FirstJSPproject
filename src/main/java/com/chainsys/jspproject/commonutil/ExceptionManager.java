package com.chainsys.jspproject.commonutil;

public class ExceptionManager {
   public static String handleException(Exception e,String source,String message) {
	   LogManager.logException(e,source);
	 message += "Message: "+e.getMessage();
	   String errorPage=HtmlHelper.getHtmlTemplate("Error", message);
        return errorPage;
   }
}
