package com.vnetpublishing.xlex;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public class Parser 
{
		
	protected URI src_uri;
	protected XLexFactory factory = null;
	protected XLexInstance rootDoc;
	
	public Parser(URI uri) 
		throws DOMException, IOException, SAXException, ParserConfigurationException, URISyntaxException, XLexException  
	{
		src_uri = uri;
		factory = new XLexFactory();
		rootDoc = factory.do_import(src_uri,true);
	}
	
	public static Parser create(URI uri) 
		throws DOMException, IOException, SAXException, ParserConfigurationException, URISyntaxException, XLexException 
	{
		return new Parser(uri);
	}
	
	public String parse(URI src) throws Exception
	{
		
		rootDoc.getRules();
		
		// Todo: Execute Parse
		
		throw new Exception("Not implemented");
	}
	
	
	
}
