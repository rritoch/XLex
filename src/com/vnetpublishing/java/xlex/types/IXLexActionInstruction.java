package com.vnetpublishing.java.xlex.types;

import com.vnetpublishing.java.xlex.Context;
import com.vnetpublishing.java.xlex.XLexException;

public interface IXLexActionInstruction {

	void execute(Context ctx) throws XLexException;

}
