package com.vnetpublishing.java.xlex;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.vnetpublishing.java.io.DynamicInputStream;

public class Context {

	Document targetDoc;
	Node curNode;
	DynamicInputStream is;
	Context parent = null;
	
	
	Context(Node curNode, DynamicInputStream is, Context parent) {
		this.targetDoc = curNode.getOwnerDocument();
		this.curNode =curNode;
		this.is = is;
		this.parent = parent;
	}
	
	public Context(URI uri) throws ParserConfigurationException, IOException {
		URL url = uri.toURL();
		is = new DynamicInputStream(url.openStream());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		targetDoc = builder.newDocument();
		curNode = targetDoc;
	}

	public Document getTargetDocument() {
		return targetDoc;
	}
	
	public void close() {
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Node getCurrentNode() {
		return curNode;
	}

	public Context createChildContext(Node n) {
		return new Context(n, is, this);
	}
}
