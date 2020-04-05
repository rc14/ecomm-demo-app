package com.omnicuris.exceptionhandle;

public class DataExceptionHandler extends Exception{
	
String str;
	
	public DataExceptionHandler(String str1, Throwable t) {
		super(str1,t);
		str=str1;
	}
	public String toString() {
		return("Exception in dao"+ str);
	}

}
