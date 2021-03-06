package com.serengetti_software.scf;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class SCFReader {
	InputStream stream;
	BufferedReader reader;
	public SCFReader(String path) throws FileNotFoundException {
		this(new FileInputStream(path));
	}
	public SCFReader(InputStream stream) {
		this.stream = stream;
	}
	public void close(boolean closeStream) throws IOException {
		if(closeStream) {
			reader.close();
		}
	}
	public HashMap<String, String> process() throws IOException, SyntaxException {
		HashMap<String, String> values = new HashMap<String, String>();
		ArrayList<String> lines = new ArrayList<String>();
		reader = new BufferedReader(new InputStreamReader(stream));
		//This isn't how we're doing it.
		/*String output = "";
		Read the lines, one by one.
		while((i= reader.read()) != -1) {
			char c = (char) i;
			output += c;
		}
		String[] linesArray = output.split("\n");
		*/
		String line;
		while((line = reader.readLine()) != null) {
			lines.add(line);
		}
		int lineNum = 0;
		for(String currentLine : lines) {
			lineNum++;
			String[] parts = currentLine.split(":");
			if(parts.length < 2) {
				throw new SyntaxException(lineNum, "Error processing SCF. The name/value pair on line " + lineNum + " did not have a value.");
			} else {
				String name = "";
				String value = "";
				for(int index = 0; index < parts.length; index++) {
					if(index < 1) {
						name = parts[index];
					} else {
						value += parts[index] + (index < parts.length - 1 ? ":" : "");
					}
				}
				values.put(name, value);
			}
		}
		lines.add(line);
		return values;
	}
}
