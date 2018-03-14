package com.lestath.xmpro.repository.xml.core;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Xml {
	
	private String filename;
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder documentBiulder;
	private Document document;
	private File xmlfile;
	        
	
	
	Xml(String filename) throws ParserConfigurationException{
		 setFilename(filename);
		 try {
			init();
		} catch (SAXException  | IOException e) {
			setDocument(getDocumentBuilder().newDocument());
		}
	}
	
	private void init() throws ParserConfigurationException, SAXException, IOException{
		setXmlfile(new File(getFilename()));
		setDbFactory(DocumentBuilderFactory.newInstance());
		setDocumentBuilder(getDbFactory().newDocumentBuilder());
		setDocument(getDocumentBuilder().parse(getXmlfile()));
		
	}

	public void setFilename(String filename) {
	 this.filename = filename;
	}

	public DocumentBuilderFactory getDbFactory() {
		return dbFactory;
	}

	public void setDbFactory(DocumentBuilderFactory dbFactory) {
		this.dbFactory = dbFactory;
	}

	public DocumentBuilder getDocumentBuilder() {
		return documentBiulder;
	}

	public void setDocumentBuilder(DocumentBuilder documentBiulder) {
		this.documentBiulder = documentBiulder;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getFilename() {
		return filename;
	}

	public File getXmlfile() {
		return xmlfile;
	}

	public void setXmlfile(File xmlfile) {
		this.xmlfile = xmlfile;
	}
	
}

