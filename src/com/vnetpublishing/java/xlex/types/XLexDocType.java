package com.vnetpublishing.java.xlex.types;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.vnetpublishing.java.xlex.XLexException;
import com.vnetpublishing.java.xlex.XLexType;

public class XLexDocType
	extends XLexType
{
    
    private static final int __M_EXPECT_IMPORT = 1;
    private static final int __M_EXPECT_STORAGE = 2;
    private static final int __M_EXPECT_GROUPS = 3;
    private static final int __M_EXPECT_RULES = 4;
    private static final int __M_EXPECT_TOOLS = 5;
    private static final int __M_EXPECT_END = 99;
    
    public static final String NamespaceURI = "urn:vnetpublishing:xlex";
    
    protected Document doc;
    
    protected ArrayList<XLexImportType> imports = new ArrayList<XLexImportType>();

    protected XLexStorageType storage = null;
    protected XLexGroupListType groups = null;
    protected XLexRuleListType rules = null;
    protected XLexToolListType tools = null;
    
    protected XLexDocType(Node node) 
    	throws DOMException, URISyntaxException, XLexException, IOException, SAXException, ParserConfigurationException 
    {
    	super(null,node);
    	_init(node);
    }
    
    protected XLexDocType(URI src) 
            throws IOException, SAXException, ParserConfigurationException, DOMException, URISyntaxException, XLexException 
    {
    	super(null,null);
        // Load and parse document
        URL src_url = src.toURL();
        InputStream is = src_url.openStream();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        doc = dbf.newDocumentBuilder().parse(is);
        
        _init((Node)doc);
        
    }
    
    private void _init(Node node)
    		throws IOException, SAXException, ParserConfigurationException, DOMException, URISyntaxException, XLexException
    {
    	domNode = node;
    	
    	Node child = doc.getDocumentElement().getFirstChild();
        int mode = __M_EXPECT_IMPORT;
        
        while(null != child) {
            
            if (Node.ELEMENT_NODE == child.getNodeType()) {
                if (! XLexDocType.NamespaceURI.equals(child.getNamespaceURI())) {
                    throw new XLexInvalidDocumentException("Foreign node in XLex Document");
                }
                
                int cMode = -1;
                
                switch(child.getLocalName()) {
                    case "import":
                        cMode = __M_EXPECT_IMPORT;
                        break;
                    case "storage":
                        cMode = __M_EXPECT_STORAGE;
                        break;
                    case "rules":
                        cMode = __M_EXPECT_RULES;
                        break;
                    case "groups":
                        cMode = __M_EXPECT_GROUPS;
                        break;
                    case "tools":
                        cMode = __M_EXPECT_TOOLS;
                        break;
                    default:
                        throw new XLexInvalidDocumentException(String.format("Unexpected root node %s",child.getNodeName()));
                }
                
                if (cMode < mode) {
                    throw new XLexInvalidDocumentException(String.format("Unexpected node %s",child.getNodeName()));
                }
                
                switch(cMode) {
                    case __M_EXPECT_IMPORT:
                        imports.add(new XLexImportType(child));
                        break;
                    case __M_EXPECT_STORAGE:
                        storage = new XLexStorageType(child,this);
                        mode = __M_EXPECT_GROUPS;
                        break;
                    case __M_EXPECT_GROUPS:
                        groups = new XLexGroupListType(child);
                        mode = __M_EXPECT_GROUPS;
                        break;
                    case __M_EXPECT_RULES:
                        rules = new XLexRuleListType(this,child);
                        mode = __M_EXPECT_TOOLS;
                        break;
                    case __M_EXPECT_TOOLS:
                        tools = new XLexToolListType(child);
                        mode = __M_EXPECT_END;
                        break;
                    default:
                        throw new XLexInvalidDocumentException("General processing failure");
                }
                
            }
            child = child.getNextSibling();
        }
    }
    
	Document getDoc() 
    {
        return doc;
    }
    
    public XLexStorageType getStorage() 
    {
    	return storage;
    }

    public XLexGroupListType getGroups() 
    {
    	return groups;
    }
    
    public XLexRuleListType getRules() 
    {
    	return rules;
    }
    
    public XLexToolListType getTools()
    {
    	return tools;
    }
    
    public String getTargetNamespace() {
    	return doc.getDocumentElement().getAttribute("targetNamepsace");
    }
}
