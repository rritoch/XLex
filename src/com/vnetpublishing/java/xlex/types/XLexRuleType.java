package com.vnetpublishing.java.xlex.types;

import java.util.ArrayList;

import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.Context;

public class XLexRuleType 
{
	XLexDocType document;
	Node node;
	
	ArrayList<XLexActionInstruction> instructions;
	
	XLexElementConstructorType element;
	String name;
	
    protected XLexRuleType(Node node,XLexDocType parent) 
    	throws XLexInvalidDocumentException
    {
    	this.node = node;
    	document = parent;
    	Node nameNode = node.getAttributes().getNamedItem("name");
    	
    	if (null == nameNode) {
    		throw new XLexInvalidDocumentException("Anonymous rule found in XLex document");
    	}
    	name = nameNode.getNodeValue();
    	
    	Node child = node.getFirstChild();
    	
    	int mode = 1;
    	
    	while(child != null) {
    		if (child.getNodeType() == Node.ELEMENT_NODE) {
    			
    			if (mode == 1) {
    			    if ("element".equals(child.getLocalName()) && 
    			    	XLexDocType.NamespaceURI.equals(child.getNamespaceURI())
    			    	) {
    			    	element = new XLexElementConstructorType(child,document);
    			    	mode = 2;
    			    } else {
    			    	instructions.add(new XLexActionInstruction(child,document));
    			    }
    			} else {
    				throw new XLexInvalidDocumentException(String.format("Unexpected element %s after element constructor",child.getNodeName()));
    			}
    		}
    		
    		child = child.getNextSibling();
    	}
    	
    }
    
    public String getName() 
    {
    	return name;
    }


	public void reduce(Context ctx) {
		// TODO Auto-generated method stub
		
	}
}
