package com.vnetpublishing.java.xlex.types;

import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.XLexException;
import com.vnetpublishing.java.xlex.XLexType;

public class XLexActionInstruction 
	extends XLexType
{

	IXLexActionInstruction instruction;
	
	/*
	public XLexActionInstruction(Object instruction) {
		this.instruction = instruction;
	}
	*/
	
	public XLexActionInstruction(XLexDocType document, Node node) 
			throws XLexInvalidDocumentException {
		super(document,node);
    	if (! XLexDocType.NamespaceURI.equals(node.getNamespaceURI())) {
        	throw new XLexInvalidDocumentException("Foreign node where Action expected");
    	}
    	
    	
    	
    	switch(node.getLocalName()) 
    	{
    	
    		default:
    			throw new XLexInvalidDocumentException(String.format("Action expected, found %s",node.getNodeName()));
    	}
	}

	public void execute(Context ctx) throws XLexException {
		instruction.execute(ctx);
	}

	public static boolean valid(Node node) {
		// TODO Auto-generated method stub
		return false;
	}
}
