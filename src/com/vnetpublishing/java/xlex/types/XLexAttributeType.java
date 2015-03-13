package com.vnetpublishing.java.xlex.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.XLexException;
import com.vnetpublishing.java.xlex.XLexType;

public class XLexAttributeType extends XLexType
{

	List<XLexInstruction> instructions = new ArrayList<XLexInstruction>();
	String name;

	public XLexAttributeType(XLexDocType document, Node node) throws XLexInvalidDocumentException {
		super(document,node);
		
    	Node nameNode = node.getAttributes().getNamedItem("name");
    	
    	if (null == nameNode) {
    		throw new XLexInvalidDocumentException("Anonymous attribute found in XLex document");
    	}
    	name = nameNode.getNodeValue();
    	
    	Node child = node.getFirstChild();
    	
    	while(child != null) {
    		if (child.getNodeType() == Node.ELEMENT_NODE) {
    			instructions.add(new XLexInstruction(document,child));
    		}
    		child = child.getNextSibling();
    	}
		
	}

	
	public void reduce(Context ctx) throws XLexException {
		

			
		Element node = (Element)ctx.getCurrentNode();
		Context cctx = ctx.createChildContext(node);
		Iterator<XLexInstruction> i = instructions.iterator();
		//System.out.println(String.format("Attribute: has context %s",cctx.toString()));
		while(i.hasNext()) {
			i.next().reduce(cctx);
		}
		String s = cctx.reduce();
		//System.out.println(String.format("Setting attribute %s to %s on %s",name,s,node.getNodeName()));
		if ("xmlns".equals(name)) {
			throw new XLexException("Attempt to set namespace with attribute");
		} else {
			node.setAttribute(name,s);
		}
		
	}


}
