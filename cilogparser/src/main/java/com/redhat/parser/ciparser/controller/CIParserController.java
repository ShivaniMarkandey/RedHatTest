package com.redhat.parser.ciparser.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redhat.parser.ciparser.CIParser;
import com.redhat.parser.ciparser.Event;
import com.redhat.parser.ciparser.Result;

/**
* CIParserController implements MVC Controller 
* Entry Point for CI log parsing
* Input: Event Log file path
* Output: Result response of type Result
*
* @author  shivani.mark
* @version 1.0
* @since   2019-03-23
*/

@RestController
public class CIParserController {

	@GetMapping("/parse")
	public Result parse(@RequestParam (name = "filepath" , defaultValue= "/Users/ryaduvanshi/Documents/Shivani/EclipseWorkspace_New/cilogparser/input_log") String filepath){
		Result result = new Result();
		
		File log_file = new File(filepath);
		try {
			Scanner sc = new Scanner(log_file);
			CIParser ciparser = new CIParser();
			while(sc.hasNextLine()){
				String nextLine = sc.nextLine();
				if(ciparser.isValidResultString(nextLine)){
					Event newEvent = ciparser.parseEvent(nextLine);
					if(newEvent!=null){
						result.addEvent(newEvent);
					}
				}
			}
			return result;
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File Not Found");
		}
	}
	
	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value());
	}

}
