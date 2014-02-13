package com.vnetpublishing.xlex.types;

import java.net.URI;
import java.net.URISyntaxException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class XLexImportType 
{
    protected URI src;
    protected URI targetNamespace = null;
    
    protected XLexImportType(Node node) throws DOMException, URISyntaxException
    {
        src = new URI(node.getAttributes().getNamedItem("src").getNodeValue());
        if (null != node.getAttributes().getNamedItem("targetNamespace")) {
            targetNamespace = new URI(node.getAttributes().getNamedItem("targetNamespace").getNodeValue());
        }
    }
    
    public URI getSrc()
    {
    	return src;
    }
    
    public URI getTargetNamespace()
    {
    	return targetNamespace;
    }
    
    public URI[] toArray()
    {
    	URI[] ret;
    	
    	if (null == targetNamespace) {
    		ret = new URI[1];
    		ret[0] = src;
    	} else {
    		ret = new URI[2];
    		ret[0] = src;
    		ret[1] = targetNamespace;
    	}
    	return ret;
    }
}
