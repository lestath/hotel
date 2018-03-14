package com.lestath.xmpro.repository.xml.core;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;

public class MyXmlWriter extends Xml{

	public MyXmlWriter(String filename) throws ParserConfigurationException {
		super(filename);	
	}


	public void writeContent() throws TransformerException{
		// write the content into xml file
		
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(getDocument());
        StreamResult result = new StreamResult(getXmlfile());
        transformer.transform(source, result);
	}
}
