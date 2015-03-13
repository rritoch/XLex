package com.vnetpublishing.java.test;
import java.io.File;
import java.net.URI;

import com.vnetpublishing.java.xlex.Parser;


public class XLexTest {

    public static void main(String[] args)
    {
    	
    	URI xlexdocuri,datauri;
    	
    	xlexdocuri = (new File("spec/csv_example.xml")).toURI();
    	datauri = (new File("spec/test.csv")).toURI();
    	
    	try {
    		Parser parser = Parser.create(xlexdocuri);
			String result = parser.parse(datauri);
			System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	
    }
}
