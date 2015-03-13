package com.vnetpublishing.java.xlex.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.XLexException;
import com.vnetpublishing.java.xlex.XLexType;

public class XLexElementConstructorType extends XLexType
	implements IXLexElementContentGroup
{

	String name;
	String namespace = null;
	
	List<XLexElementContentGroup> content = new ArrayList<XLexElementContentGroup>();
	List<XLexAttributeType> attributes = new ArrayList<XLexAttributeType>();
	
	public XLexElementConstructorType(XLexDocType document, Node node) throws XLexInvalidDocumentException {
		super(document,node);
		
    	Node nameNode = node.getAttributes().getNamedItem("name");
    	
    	if (null == nameNode) {
    		throw new XLexInvalidDocumentException("Anonymous element found in XLex document");
    	}
    	name = nameNode.getNodeValue();
    	
    	Node namespaceNode = node.getAttributes().getNamedItem("namespace");
    	
    	if (namespaceNode != null) {
    		namespace = namespaceNode.getNodeValue();
    	}
    	
    	Node child = node.getFirstChild();
    	
    	int mode = 1;
    	
    	while(child != null) {
    		if (child.getNodeType() == Node.ELEMENT_NODE) {
    			if (mode == 1) {
    			    if ("attribute".equals(child.getLocalName()) && 
    			    	XLexDocType.NamespaceURI.equals(child.getNamespaceURI())
    			    	) {
    			    	attributes.add(new XLexAttributeType(document,child));
    			    	mode = 2;
    			    } else {
    			    	content.add(new XLexElementContentGroup(document,child));
    			    }
    			} else if (mode == 2) {
    			    if ("attribute".equals(child.getLocalName()) && 
        			    	XLexDocType.NamespaceURI.equals(child.getNamespaceURI())
        			    	) {
    			    	attributes.add(new XLexAttributeType(document,child));
        			} else {
        				throw new XLexInvalidDocumentException(String.format("Unexpected element %s after attribute list",child.getNodeName()));
        			}
    			}
    		}
    		
    		child = child.getNextSibling();
    	}
		
	}

	public void reduce(Context ctx) throws XLexException {
		
		Node n;
		
		
		Iterator<XLexAttributeType> ai;
		

		
		if (namespace == null) {
			n = ctx.getTargetDocument().createElementNS(ownerDocument.getTargetNamespace(), name);
		} else {
			n = ctx.getTargetDocument().createElementNS(namespace,name);
		}
		
		Context cctx = ctx.createChildContext(n);
		
		//TODO: Run content...
		
		Iterator<XLexElementContentGroup> ci = content.iterator();
		while(ci.hasNext()) {
			ci.next().execute(cctx);
		}

		// Apply Attributes
		
		ai = attributes.iterator();
		while(ai.hasNext()) {
			ai.next().reduce(cctx);
		}

		ctx.getCurrentNode().appendChild(n);
	}

	@Override
	public void executeContentGroup(Context ctx) throws XLexException {
		reduce(ctx);
	}
}
