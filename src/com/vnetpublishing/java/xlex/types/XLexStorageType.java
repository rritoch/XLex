package com.vnetpublishing.java.xlex.types;

import java.util.HashMap;

import org.w3c.dom.Node;

import com.vnetpublishing.java.xlex.XLexException;

public class XLexStorageType 
{
    protected XLexDocType parent;
    protected Node node;
    protected final HashMap<String,XLexVarType> vars = new HashMap<String,XLexVarType>();
    
    protected XLexStorageType(Node node, XLexDocType parent) throws XLexInvalidDocumentException
    {
        this.node = node;
        this.parent = parent;
        
        Node child = node.getFirstChild();
        while(null != child) {
            if (Node.ELEMENT_NODE == child.getNodeType()) {
                if (!XLexDocType.NamespaceURI.equals(child.getNamespaceURI())) {
                    throw new XLexInvalidDocumentException("Unexpected foreign element");
                }
                if (!"var".equals(child.getLocalName())) {
                    throw new XLexInvalidDocumentException("Unexpected element");
                }
                
                XLexVarType tmp = new XLexVarType(child,this);
                if (vars.containsKey(tmp.getName())) {
                    throw new XLexInvalidDocumentException("Duplicate Storage Var");
                }
                vars.put(tmp.getName(),tmp);
            }
            child = child.getNextSibling();
        }
    }
    
    public String getVar(String name)
        throws XLexException 
    {
        if (!vars.containsKey(name)) {
            throw new XLexException("Storage doesn't contain requested variable");
        }
        return vars.get(name).getValue();
    }
    
    public void setVar(String name,String value) 
    	throws XLexException
    {
        if (!vars.containsKey(name)) {
            throw new XLexException("Storage doesn't contain requested variable");
        }
        vars.get(name).setValue(value);
    }
}
