package com.serengetti_software.scf_test;

import java.util.HashMap;
import java.util.Map;

import com.serengetti_software.scf.*;

public class Main {
	public static void main(String[] args) throws Exception {
		//You will need to change the String argument to the constructor.
		SCFReader reader = new SCFReader("C:\\Users\\EthanReg\\Documents\\GitHub\\SCF-multilanguage-2\\example.scf");
		HashMap<String, String> values = reader.process();
		reader.close(true);
		for(Map.Entry<String, String> pair : values.entrySet()) {
			System.out.println("Key \"" + pair.getKey() + "\" has value \"" + pair.getValue() + "\".");
		}
		SCFWriter writer = new SCFWriter("C:\\Users\\EthanReg\\Documents\\GitHub\\SCF-multilanguage-2\\example.scf");
		writer.write(values);
		
	}
}
