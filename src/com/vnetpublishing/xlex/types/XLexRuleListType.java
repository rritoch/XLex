package com.vnetpublishing.xlex.types;

import java.util.HashMap;

import org.w3c.dom.Node;

import com.vnetpublishing.xlex.XLexException;
import com.vnetpublishing.xlex.XLexType;

public class XLexRuleListType
	extends XLexType
{
	HashMap<String,XLexRuleType> rules;
	
	
    protected XLexRuleListType(XLexDocType ownerDocument,Node domNode)
    	throws XLexException
    {
    	super(ownerDocument,domNode);
    	
    	rules = new HashMap<String,XLexRuleType>();
    	
    	XLexRuleType rule;
    	
    	Node child = domNode.getFirstChild();
    	
    	while(null != child) {
    		if (child.getNodeType() == Node.ELEMENT_NODE) {
            	if (! XLexDocType.NamespaceURI.equals(child.getNamespaceURI())) {
                	throw new XLexInvalidDocumentException("Foreign node in XLex Rule");
            	}
            	if (!"rule".equals(child.getLocalName())) {
            		throw new XLexInvalidDocumentException(String.format("Unexpected element '%s'in rules",domNode.getNodeName()));
            	}
            	
            	rule = new XLexRuleType(child,ownerDocument);
            	
            	if (rules.containsKey(rule.getName())) {
            		throw new XLexInvalidDocumentException(String.format("Duplicate rule name '%s'in rules",rule.getName()));
            	}
            	rules.put(rule.getName(),rule);
            	
    		}
    	    child = child.getNextSibling();
    	}
    	
    	
    }
}
