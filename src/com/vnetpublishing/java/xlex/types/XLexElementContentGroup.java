package com.vnetpublishing.java.xlex.types;

import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.XLexException;
import com.vnetpublishing.java.xlex.XLexType;

public class XLexElementContentGroup 	
	extends XLexType
{

	IXLexElementContentGroup instruction;
	
	public XLexElementContentGroup(XLexDocType document, Node node)
			throws XLexInvalidDocumentException {
		super(document,node);
    	if (! XLexDocType.NamespaceURI.equals(node.getNamespaceURI())) {
        	throw new XLexInvalidDocumentException("Foreign node where Action expected");
    	}
    	
    	
    	
    	switch(node.getLocalName()) 
    	{
    		case "setVar":
    			instruction = (IXLexElementContentGroup) new XLexSetVarType(document,node);
    			break;
    		case "parse":
    			instruction = (IXLexElementContentGroup) new XLexParseType(document,node);
    			break;
    		default:
    			throw new XLexInvalidDocumentException(String.format("XLexActionInstruction expected, found %s",node.getNodeName()));
    	}
	}

	public void execute(Context ctx) throws XLexException {
		instruction.executeContentGroup(ctx);
	}


}
