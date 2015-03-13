package com.vnetpublishing.java.xlex.types;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.XLexException;
import com.vnetpublishing.java.xlex.XLexType;

public class XLexWriteGroup extends XLexType 
	implements IXLexInstruction {

	IXLexWriteGroup item;
	
	public XLexWriteGroup(XLexDocType document, Node node) throws XLexInvalidDocumentException {
		super(document,node);
    	if (! XLexDocType.NamespaceURI.equals(node.getNamespaceURI())) {
        	throw new XLexInvalidDocumentException("Foreign node where XLexWriteGroup expected");
    	}
    	
    	
    	switch(node.getLocalName()) 
    	{
    		case "writeVar":
    			item = (IXLexWriteGroup) new XLexWriteVarType(document,node);
    			break;
    		case "writeBuffer":
    		default:
    			throw new XLexInvalidDocumentException(String.format("XLexWriteGroup expected, found %s",node.getNodeName()));
    	}
	}

	public static boolean valid(Node node) {
		
		if (node.getNodeType() != Node.ELEMENT_NODE) {
			return false;
		}
		
    	if (! XLexDocType.NamespaceURI.equals(node.getNamespaceURI())) {
        	return false;
    	}
    	
    	Element el = (Element)node;
    	
    	String lname = el.getLocalName();
    	
    	if ("writeBuffer".equals(lname)) {
    		return true;
    	}
    	
    	if ("writeVar".equals(lname)) {
    		return true;
    	}
    	
    	
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void execute(Context ctx) throws XLexException {
		item.executeWriteGroup(ctx);
	}

	@Override
	public void executeContentGroup(Context ctx) throws XLexException {
		execute(ctx);
	}


}
