package com.serengetti_software.scf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SCFReader {
	InputStream stream;
	public SCFReader(String path) throws FileNotFoundException {
		this.stream = new FileInputStream(path);
	}
	public SCFReader(InputStream stream) {
		this.stream = stream;
	}
	public void close(boolean closeStream) {
		
	}
}
