package com.vnetpublishing.java.xlex.types;

import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.XLexType;

public abstract class XLexSetReadModeType 
	extends XLexType 
{

	protected String classProperty;
	
	protected XLexSetReadModeType(XLexDocType ownerDocument, Node domNode) 
	{
		super(ownerDocument, domNode);
		Node tmpNode;
		tmpNode = domNode.getAttributes().getNamedItem("class");
		classProperty = tmpNode == null ? null : tmpNode.getNodeValue();
	}
	

}
