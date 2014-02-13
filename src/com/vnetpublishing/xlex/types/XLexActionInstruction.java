package com.vnetpublishing.xlex.types;

import org.w3c.dom.Node;

public class XLexActionInstruction 
{

	Object instruction;
	
	public XLexActionInstruction(Object instruction) {
		this.instruction = instruction;
	}
	
	public XLexActionInstruction(Node node, XLexDocType doc) 
			throws XLexInvalidDocumentException {
    	if (! XLexDocType.NamespaceURI.equals(node.getNamespaceURI())) {
        	throw new XLexInvalidDocumentException("Foreign node where Action expected");
    	}
    	
    	switch(node.getLocalName()) 
    	{
    	
    		default:
    			throw new XLexInvalidDocumentException(String.format("Action expected, found %s",node.getNodeName()));
    	}
	}
}
