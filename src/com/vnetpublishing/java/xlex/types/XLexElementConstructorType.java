package com.vnetpublishing.java.xlex.types;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.XLexType;

public class XLexElementConstructorType extends XLexType
{

	public XLexElementConstructorType(XLexDocType ownerDocument, Node domNode) {
		super(ownerDocument,domNode);
	}

	public void reduce(Context ctx) {
		
		String name = ((Element)getNode()).getAttribute("name");
		Node n = ctx.getTargetDocument().createElementNS(ownerDocument.getTargetNamespace(), name);
		ctx.getCurrentNode().appendChild(n);
		
		Context cctx = ctx.createChildContext(n);
		
		//TODO: Run instructions...
		
	}
}
