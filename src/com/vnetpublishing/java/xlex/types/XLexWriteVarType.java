package com.vnetpublishing.java.xlex.types;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.XLexException;
import com.vnetpublishing.java.xlex.XLexType;

public class XLexWriteVarType extends XLexType implements IXLexWriteGroup {

	String var;
	
	public XLexWriteVarType(XLexDocType document, Node node) {
		super(document,node);
		Element el = (Element)node;
		var = el.getAttribute("var");
	}

	@Override
	public void executeWriteGroup(Context ctx) throws XLexException {
		ctx.write(getOwnerDocument().getStorage().getVar(var));
	}

}
