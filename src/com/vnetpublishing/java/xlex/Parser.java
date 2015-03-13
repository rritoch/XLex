package com.vnetpublishing.java.xlex;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.types.XLexRuleListType;
import com.vnetpublishing.java.xlex.types.XLexRuleType;

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
		
		XLexRuleType startrule = rootDoc.getRules().getRule("__start__");

		Context ctx = new Context(src);
		startrule.reduce(ctx);
		Document doc = ctx.getTargetDocument();
		
        StringWriter sw = new StringWriter();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.transform(new DOMSource(doc), new StreamResult(sw));
        
        ctx.close();
        
        return sw.toString();
	}
	
	
	
}
