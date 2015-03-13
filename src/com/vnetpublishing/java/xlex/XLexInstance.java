package com.vnetpublishing.java.xlex;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.vnetpublishing.java.xlex.types.XLexDocType;
import com.vnetpublishing.java.xlex.types.XLexImportType;

public class XLexInstance
    extends XLexDocType
{

	URI src_uri,targetNamespace;
	protected HashMap<URI,XLexInstance> inventory;
	
	
	XLexInstance(URI src) 
		throws IOException, SAXException, ParserConfigurationException, DOMException, URISyntaxException, XLexException 
	{
		super(src);
		src_uri = src;
		Node tns = doc.getDocumentElement().getAttributes().getNamedItem("targetNamespace");
		
		if (null != tns) {
			targetNamespace = new URI(tns.getNodeValue());
		} else {
			targetNamespace = null;
		}
	}
	
    void loadImports(XLexFactory f, boolean deep) 
    	throws DOMException, URISyntaxException, IOException, SAXException, ParserConfigurationException, XLexException 
    {
    	XLexInstance ob;
    	URI ns;
    	URI[] item;
    
    	Iterator<XLexImportType> i = imports.iterator();
    	while(i.hasNext()) {
    		item = i.next().toArray();
    		if (item.length > 0) {
    		    ob = f.do_import(item[0],deep);
    		    if (item.length > 1) {
    		    	ns = item[1];
    		    } else {
    		        ns = ob.getTargetNamespaceURI();
    		    }
    		    
    			inventory.put(ns, ob);
    		}
    	}
    }
	
	
	public URI getTargetNamespaceURI() 
	{
		return targetNamespace; 
	}
	
}
