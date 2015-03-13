package com.vnetpublishing.java.xlex.types;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.XLexType;

public class XLexAttributeType extends XLexType
{

	List<XLexActionInstruction> instructions = new ArrayList<XLexActionInstruction>();
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
    			instructions.add(new XLexActionInstruction(child,document));
    		}
    		child = child.getNextSibling();
    	}
		
	}

	
	public void reduce(Context ctx) {
		Element node = (Element)ctx.getCurrentNode();
		
		Context cctx = ctx.createChildContext(node);
		
		//TODO: Run commands
		
		node.setAttribute(name,cctx.reduce());
	}

}
