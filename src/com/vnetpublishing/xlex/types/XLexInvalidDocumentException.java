package com.vnetpublishing.xlex.types;

import com.vnetpublishing.xlex.XLexException;

@SuppressWarnings("serial")
public class XLexInvalidDocumentException 
	extends XLexException {
	public XLexInvalidDocumentException(String msg) {
		super(msg);
	}
}
