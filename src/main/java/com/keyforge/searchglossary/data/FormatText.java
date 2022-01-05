package com.keyforge.searchglossary.data;


import org.springframework.stereotype.Service;

@Service
public class FormatText {

	public String addFormatting(String description) {
		return replaceQuotes(description);
	}
	
	/*
	 * Replacing quotes with html friendly characters
	 */
	private String replaceQuotes(String input) {
		StringBuilder output = new StringBuilder();
			char[] inputChars = input.toCharArray();
			
			for (int i=0; i < inputChars.length; i++) {
				if(inputChars[i] == '“' || inputChars[i] == '”' || inputChars[i] == '"') {
					output.append("&quot;");
				}else if(inputChars[i] == '’' || inputChars[i] == '\'') {
					output.append("&apos;");
				}else {
					output.append(inputChars[i]);
				}
				
			}
			return output.toString();
		}
}
