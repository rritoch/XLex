package com.vnetpublishing.java.xlex.types;

import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.XLexException;

public class BitModeParse 
	extends XLexParseType
{

	protected String classProperty;
	
	protected BitModeParse(XLexDocType ownerDocument, Node domNode) 
		throws XLexException 
	{
		super(ownerDocument, domNode);
		
		Node tmpNode;
		tmpNode = domNode.getAttributes().getNamedItem("class");
		classProperty = tmpNode == null ? null : tmpNode.getNodeValue();
		
		if (classProperty == null) {
			classProperty = "bit";
		}
		
		if (toolProperty == null) {
			throw new XLexException("Tool not defined");
		}
		
		if (null == sizeProperty) {
			throw new XLexException("Size not defined");
		}
	}

	
}
