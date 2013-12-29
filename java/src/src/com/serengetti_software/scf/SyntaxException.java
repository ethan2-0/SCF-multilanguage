package com.serengetti_software.scf;

public class SyntaxException extends Exception {
	//I'm not quite sure what this does, but Eclipse yelled at me for not adding it, so I added it.
	private static final long serialVersionUID = 1L;

	public SyntaxException(int line) {
		super("A syntax error occurred on line " + line + ". THIS IS NOT A JAVA ERROR. THIS IS AN ERROR FOR THE SYNTAX OF SERENGETTI CONFIG FORMAT.");
	}
	public SyntaxException(int line, String msg) {
		super("A syntax error occurred on line " + line + ": " + msg + ". THIS IS NOT A JAVA ERROR. THIS IS AN ERROR FOR THE SYNTAX OF SERENGETTI CONFIG FORMAT.");
	}
}
