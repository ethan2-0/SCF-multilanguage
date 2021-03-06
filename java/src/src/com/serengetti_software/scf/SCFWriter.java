package com.serengetti_software.scf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class SCFWriter {
	private OutputStreamWriter writer;
	public SCFWriter(OutputStream stream) {
		this.writer = new OutputStreamWriter(stream);
	}
	public SCFWriter(String path) throws FileNotFoundException {
		this(new FileOutputStream(path));
	}
	public void close() throws IOException {
		writer.close();
	}
	public void write(HashMap<String, String> nameValue) throws IOException {
		for(Map.Entry<String, String> pair : nameValue.entrySet()) {
			System.out.println("Writing: " + pair.getKey() + ":" + pair.getValue() + "\n");
			writer.write(pair.getKey() + ":" + pair.getValue() + "\n");
			writer.flush();
		}
	}
}
