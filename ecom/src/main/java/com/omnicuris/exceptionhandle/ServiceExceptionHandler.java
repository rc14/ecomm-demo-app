package com.omnicuris.exceptionhandle;

public class ServiceExceptionHandler extends Exception {
String str1;
	
	public ServiceExceptionHandler(String str2,Throwable t) {
		super(str2,t);
		str1=str2;
	}
	public String toString() {
		return("Exception in dao"+ str1);

}

}
