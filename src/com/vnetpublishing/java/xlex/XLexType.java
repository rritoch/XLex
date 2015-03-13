package com.vnetpublishing.java.xlex;

import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.types.XLexDocType;

public class XLexType 
{

	protected XLexDocType ownerDocument = null;
	protected Node domNode = null;
	
	protected XLexType(XLexDocType ownerDocument, Node domNode)
	{
		this.ownerDocument = ownerDocument;
		this.domNode = domNode;
	}
	
	XLexDocType getOwnerDocument() 
	{
		return ownerDocument;
	}
	
}
