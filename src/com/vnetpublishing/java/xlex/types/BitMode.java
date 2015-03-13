package com.vnetpublishing.java.xlex.types;

import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.XLexException;

public class BitMode 
	extends XLexBaseSetBitReadModeType
{

	public BitMode(XLexDocType ownerDocument, Node domNode) 
		throws XLexException
	{
		super(ownerDocument,domNode);
		
		// validate size
		
		if (sizeProperty < 1) {
			throw new XLexException("Invalid bit size");
		}
		
		// validate class
		if (null == classProperty) {
			classProperty = "bit";
		}
		
		switch(classProperty) {
			case "bit":
				break;
			default:
				throw new XLexException("Invalid class property");
		}
		
		// validate encoding
		
		if (encodingProperty != null) {
			throw new XLexException("Encoding not allowed for bit mode");
		}
		
		
	}
}
