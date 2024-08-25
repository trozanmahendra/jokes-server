package com.cgi.jokes.exceptions;

public class InvalidRequestException extends RuntimeException{
	
	public InvalidRequestException(String rootCause) {
   	 super(String.format("Invalid request Root cause is >>>>  %s", rootCause));
   }

}
