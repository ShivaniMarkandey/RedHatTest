package com.redhat.parser.ciparser;

/**
* Each event of result response
*
* @author  shivani.mark
* @version 1.0
* @since   2019-03-23
*/
public class Event {
		private String operation;
		private String filename;
		private int line_number;
		private String name;
		
		Event(){
			operation = filename = name = null;
			line_number = 0;
		}
		
		Event(String input_operation, String input_filename, int input_line_number, String input_name){
			this.operation = input_operation;
			this.filename = input_filename;
			this.line_number = input_line_number;
			this.name = input_name;
		}

		public String getOperation() {
			return operation;
		}

		public void setOperation(String operation) {
			this.operation = operation;
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		public int getLine_number() {
			return line_number;
		}

		public void setLine_number(int line_number) {
			this.line_number = line_number;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		@Override
	    public String toString() 
	    { 
	        return "Event [operation="
	            + operation 
	            + ", filename="
	            + filename 
	            + ", line_number="
	            + line_number 
	            + ", name="
	            + name + "]"; 
	    } 

}
