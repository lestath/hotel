package com.lestath.xmpro.repository.xml.core;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class MyXmlReader extends Xml {

	public MyXmlReader(String filename) throws ParserConfigurationException {
		super(filename);
	}

	public Document read() throws SAXException, IOException {
		Document doc = getDocumentBuilder().parse(getXmlfile());
		return doc;
	}
}
