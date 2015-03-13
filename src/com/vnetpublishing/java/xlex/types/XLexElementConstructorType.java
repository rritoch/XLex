package com.vnetpublishing.java.xlex.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.XLexType;

public class XLexElementConstructorType extends XLexType
{

	String name;
	
	List<XLexElementConstructorType> elements = new ArrayList<XLexElementConstructorType>();
	
	public XLexElementConstructorType(XLexDocType document, Node node) throws XLexInvalidDocumentException {
		super(document,node);
		
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
    			    	elements.add(new XLexElementConstructorType(document,child));
    			    	mode = 2;
    			    } else {
    			    	//TODO: Handle XLexElementContentGroup
    			    	
    			    	//instructions.add(new XLexActionInstruction(child,document));
    			    }
    			} else if (mode == 2) {
    			    if ("attribute".equals(child.getLocalName()) && 
        			    	XLexDocType.NamespaceURI.equals(child.getNamespaceURI())
        			    	) {
        			    	elements.add(new XLexElementConstructorType(document,child));
        			} else {
        				throw new XLexInvalidDocumentException(String.format("Unexpected element %s after attribute list",child.getNodeName()));
        			}
    			}
    		}
    		
    		child = child.getNextSibling();
    	}
		
	}

	public void reduce(Context ctx) {
		Node n = ctx.getTargetDocument().createElementNS(ownerDocument.getTargetNamespace(), name);
		ctx.getCurrentNode().appendChild(n);
		
		Context cctx = ctx.createChildContext(n);
		
		//TODO: Run instructions...
		
		/*
		Iterator<XLexElementConstructorType> i = elements.iterator();
		while(i.hasNext()) {
			i.next().reduce(cctx);
		}
		
		*/
		
		// Apply Attributes
		
	}
}
