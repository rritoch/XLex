package com.vnetpublishing.xlex;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public class XLexFactory 
{
	
	
	protected HashMap<URI,XLexInstance> cache;
	
	
	XLexFactory() 
	{
		cache = new HashMap<URI, XLexInstance>();
	}
	
	public XLexInstance do_import(URI uri, boolean deep) 
			throws DOMException, IOException, SAXException, ParserConfigurationException, URISyntaxException, XLexException 
	{
		XLexInstance inst;
		
		if (cache.containsKey(uri)) {
			inst = cache.get(uri);
		} else {
		    inst = new XLexInstance(uri);
		    cache.put(uri,inst);
		}
		
		if (deep) {
			inst.loadImports(this,true);
		}
		
		return inst;
	}
	
}
