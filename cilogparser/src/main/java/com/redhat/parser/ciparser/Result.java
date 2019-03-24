package com.redhat.parser.ciparser;

import java.util.ArrayList;
import java.util.List;

/**
* Result object to be returned as a response
*
* @author  shivani.mark
* @version 1.0
* @since   2019-03-23
*/
public class Result {

	private List<Event> result;
	
	public Result(){
		result = new ArrayList<Event>();
	}
	
	
	public List<Event> getResult() {
		return result;
	}


	public void setResult(List<Event> result) {
		this.result = result;
	}


	public void addEvent(Event event){
		result.add(event);
	}
	
	@Override
    public String toString() 
    { 
        return "Result [ result="
            + result + "]"; 
    } 
	
}
