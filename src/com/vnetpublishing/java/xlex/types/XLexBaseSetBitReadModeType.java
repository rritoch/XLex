package com.vnetpublishing.java.xlex.types;

import org.w3c.dom.Node;

public abstract class XLexBaseSetBitReadModeType 
	extends XLexSetReadModeType
{

	protected int sizeProperty;
	protected String encodingProperty;
	
	protected XLexBaseSetBitReadModeType(XLexDocType ownerDocument, Node domNode) 
	{
		super(ownerDocument, domNode);

		Node tmpNode;
		
		tmpNode = domNode.getAttributes().getNamedItem("encoding");
		encodingProperty = tmpNode == null ? null : tmpNode.getNodeValue();
		
		tmpNode = domNode.getAttributes().getNamedItem("size");
		sizeProperty = tmpNode == null ? 0 : Integer.parseInt(tmpNode.getNodeValue());
		
	}

}
