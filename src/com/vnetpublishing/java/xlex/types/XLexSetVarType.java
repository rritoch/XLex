package com.vnetpublishing.java.xlex.types;

import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.XLexType;

public class XLexSetVarType 
	extends XLexType 
	implements IXLexElementContentGroup {

	public XLexSetVarType(XLexDocType document, Node node) {
		super(document,node);
	}

	@Override
	public void executeContentGroup(Context ctx) {
		// TODO Auto-generated method stub
		System.out.println("XLexSetVarType: FixMe!");
	}

}
