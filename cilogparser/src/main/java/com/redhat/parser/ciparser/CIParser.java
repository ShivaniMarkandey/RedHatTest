package com.redhat.parser.ciparser;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
* CIParser implements business logic for log parsing
*
* @author  shivani.mark
* @version 1.0
* @since   2019-03-23
*/
public class CIParser {
	// Literals
	private static final String ENTRY_STR = "ENTER";
	private static final String ENTRY_OPERATION = "ENTRY";
	private static final String EXIT_STR = "EXIT";
	private static final String EXIT_OPERATION = "EXIT";
	private static final String ANONYMOUS_USER = "anonymous";
	
	// Parse each event from log file
	public Event parseEvent(String event){

		Pattern entryPattern = Pattern.compile(".*"+ENTRY_STR+".*");
		Pattern exitPattern = Pattern.compile(".*"+EXIT_STR+".*");
		
		Event result = new Event();
	    if(entryPattern.matcher(event).matches()){
	    	populateResultInstance(event, entryPattern,result);
	    }else if(exitPattern.matcher(event).matches()){
	    	populateResultInstance(event, exitPattern,result);
	    }
	    return result;
	}
	
	// Checks whether log entry contains enter/exit condition or not for response genreation
	public boolean isValidResultString(String input){
		if(input.matches(".*("+ENTRY_STR+"|"+EXIT_STR+").*"))
			return true;
		else return false;
	}
	
	// For a valid entry/exit event update result response
	private void populateResultInstance(String event, Pattern pattern, Event resultObject){
		Matcher matcher = pattern.matcher(event);
		matcher.matches();
		int index = matcher.start();
		if(event.contains(ENTRY_STR))
			index+=event.indexOf(ENTRY_STR)+ENTRY_STR.length();
		else if(pattern.pattern().contains(EXIT_STR))
			index+=event.indexOf(EXIT_STR)+EXIT_STR.length();

		if(index>=event.length()){
			resultObject = null;
			return;
		}
		String str[] = event.substring(index+2).trim().split("\\s+");
		if(str.length<2){
			resultObject = null;
			return;
		}
	
		if(pattern.pattern().contains(ENTRY_STR))
			resultObject.setOperation(ENTRY_OPERATION);
		else if(pattern.pattern().contains(EXIT_STR))
			resultObject.setOperation(EXIT_OPERATION);
		resultObject.setFilename(str[0].trim().split(":")[0]);
		resultObject.setLine_number(Integer.parseInt((str[0].trim().split(":")[1])));
		if(str[1].matches("0"))
			resultObject.setName(ANONYMOUS_USER);
		else if (str[1].matches("^([a-z]|[A-Z]|_).*([a-z]|[A-Z]|_|[0-9])$")){
			resultObject.setName(str[1]);
		}
		// TBD [As per use-case]
		// when name is invalid
	}

}
