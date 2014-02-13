package com.vnetpublishing.xlex.types;

import org.w3c.dom.Node;

import com.vnetpublishing.xlex.XLexType;

public class XLexParseType 
	extends XLexType
{

	String toolProperty;
	String src_encrefProperty;
	Integer sizeProperty;
	
	protected XLexParseType(XLexDocType ownerDocument, Node domNode) {
		super(ownerDocument, domNode);
		
		Node tmpNode;
		tmpNode = domNode.getAttributes().getNamedItem("tool");
		toolProperty = tmpNode == null ? null : tmpNode.getNodeValue();
		
		tmpNode = domNode.getAttributes().getNamedItem("src_encref");
		src_encrefProperty = tmpNode == null ? null : tmpNode.getNodeValue();
		
		tmpNode = domNode.getAttributes().getNamedItem("size");
		sizeProperty = tmpNode == null ? null : Integer.parseInt(tmpNode.getNodeValue());
		
	}
	

}
