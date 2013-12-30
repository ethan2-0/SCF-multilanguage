package com.serengetti_software.scf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class SCFReader {
	InputStream stream;
	public SCFReader(String path) throws FileNotFoundException {
		this.stream = new FileInputStream(path);
	}
	public SCFReader(InputStream stream) {
		this.stream = stream;
	}
	public void close(boolean closeStream) throws IOException {
		if(closeStream) {
			stream.close();
		}
	}
	public HashMap<String, String> process() throws IOException, SyntaxException {
		InputStreamReader reader = new InputStreamReader(stream);
		HashMap<String, String> values = new HashMap<String, String>();
		ArrayList<String> lines = new ArrayList<String>();
		int i;
		String line = "";
		//Read the lines, one by one.
		while((i= reader.read()) != -1) {
			char c = (char) i;
			if(c == '\n') {
				lines.add(line);
			} else {
				line += c;
			}
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
					if(i < 2) {
						name = parts[index];
					} else {
						value += parts[index];
					}
				}
				values.put(name, value);
			}
		}
		lines.add(line);
		return values;
	}
}
