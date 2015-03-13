package com.vnetpublishing.java.xlex.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.XLexException;

public class XLexRuleType 
{
	XLexDocType document;
	Node node;
	
	List<XLexActionInstruction> instructions = new ArrayList<XLexActionInstruction>();
	
	XLexElementConstructorType element = null;
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
    			    	element = new XLexElementConstructorType(document,child);
    			    	mode = 2;
    			    } else {
    			    	instructions.add(new XLexActionInstruction(document,child));
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


	public void reduce(Context ctx) throws XLexException {
		
		// Run action instructions
		Iterator<XLexActionInstruction> i = instructions.iterator();
		
		while(i.hasNext()) {
			i.next().execute(ctx);
		}
		
		// Run element
		
		if (element != null) {
			element.reduce(ctx);
		}
		
	}
}
