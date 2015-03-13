package com.vnetpublishing.java.xlex.types;

import org.w3c.dom.Node;

public class XLexVarType 
{

	protected XLexStorageType parent;
	protected Node node;
	protected String value = null;
	protected String name;
	
	protected XLexVarType(Node node, XLexStorageType parent) 
	{
		this.parent = parent;
		this.node = node;
		name = node.getAttributes().getNamedItem("name").getNodeValue();
		if (null != node.getAttributes().getNamedItem("value")) {
			value = node.getAttributes().getNamedItem("value").getNodeValue();
		}
	}
	
	public String getName() 
	{
		return name;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
}
