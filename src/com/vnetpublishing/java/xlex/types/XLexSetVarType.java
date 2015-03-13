package com.vnetpublishing.java.xlex.types;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.XLexException;
import com.vnetpublishing.java.xlex.XLexType;

public class XLexSetVarType 
	extends XLexType 
	implements IXLexElementContentGroup {

			
	String var;
	String value;
	
	public XLexSetVarType(XLexDocType document, Node node) {
		super(document,node);
		Element el = (Element)node;
		var = el.getAttribute("var");
		value = el.getAttribute("value");
	}

	@Override
	public void executeContentGroup(Context ctx) throws XLexException {
		// TODO Auto-generated method stub
		getOwnerDocument().getStorage().setVar(var, value);
	}

}
