package com.vnetpublishing.java.xlex.types;

import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.XLexType;

public class XLexInstruction 
	extends XLexType
{

	IXLexInstruction instruction;

	
	public XLexInstruction(XLexDocType document, Node node) 
			throws XLexInvalidDocumentException {
		super(document,node);
		
    	if (! XLexDocType.NamespaceURI.equals(node.getNamespaceURI())) {
        	throw new XLexInvalidDocumentException("Foreign node where Instruction expected");
    	}
    	
    	
    	if (XLexActionInstruction.valid(node)) {
    		instruction = (IXLexInstruction) new XLexActionInstruction(document,node);
    	} else if (XLexWriteGroup.valid(node)) {
    		instruction = (IXLexInstruction) new XLexWriteGroup(document,node);
    	} else {
    		throw new XLexInvalidDocumentException(String.format("Instruction expected, found %s",node.getNodeName()));
    	}
	}


	public void reduce(Context cctx) {
		instruction.execute(cctx);
	}
}
