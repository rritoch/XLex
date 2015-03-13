package com.vnetpublishing.java.xlex.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.XLexType;

public class XLexElementConstructorType extends XLexType
	implements IXLexElementContentGroup
{

	String name;
	
	List<XLexElementContentGroup> content = new ArrayList<XLexElementContentGroup>();
	List<XLexAttributeType> attributes = new ArrayList<XLexAttributeType>();
	
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

	public void reduce(Context ctx) {
		Node n = ctx.getTargetDocument().createElementNS(ownerDocument.getTargetNamespace(), name);
		ctx.getCurrentNode().appendChild(n);
		
		Context cctx = ctx.createChildContext(n);
		
		//TODO: Run content...
		
		Iterator<XLexElementContentGroup> ci = content.iterator();
		while(ci.hasNext()) {
			ci.next().execute(cctx);
		}

		// Apply Attributes
		
		Iterator<XLexAttributeType> ai = attributes.iterator();
		while(ai.hasNext()) {
			ai.next().reduce(cctx);
		}

		

		
	}

	@Override
	public void executeContentGroup(Context ctx) {
		reduce(ctx);
	}
}
