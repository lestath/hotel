package com.lestath.xmpro.repository.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.lestath.xmlpro.model.Guest;
import com.lestath.xmpro.repository.GuestRepository;
import com.lestath.xmpro.repository.xml.core.MyXmlReader;
import com.lestath.xmpro.repository.xml.core.MyXmlWriter;


public class XmlGuestRepository implements GuestRepository {

	private MyXmlWriter writer;
	private MyXmlReader reader;
	
	public static String db_name="/tmp/guest.xml";
	public static String entity_name = "guest";
	public static String[] entity_fields ={
		"id",
		"name",
		"surname"
	};
	
	public XmlGuestRepository() throws ParserConfigurationException{
		setWriter(new MyXmlWriter(db_name));
		setReader(new MyXmlReader(db_name));
		
	}
	
	@Override
	public List<Guest> findAll() {
		List<Guest> elems= new ArrayList<Guest>();
		Guest g;
		Document d;
		try {
			d = getReader().read();
			NodeList nl = d.getElementsByTagName(entity_name); 
			for(int i = 0; i<nl.getLength();i++){
				g = takeGuest((Element) nl.item(i));
				elems.add(g);
			}
			return elems;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Guest findById(Long id) {
		Document d;
		try {
			d = getReader().read();
			NodeList nl = d.getElementsByTagName(entity_name); 
			Guest g;
			Element e;
			for (int i = 0; i < nl.getLength(); i++) {
				e = (Element) nl.item(i);
				g = this.takeGuest(e);
				if(id==g.getId()) return g;
			}
		} catch (SAXException | IOException e1) {
			e1.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void save(Guest obj) {
		try {
			Element e = toNode(obj);
			getWriter().getDocument().getElementsByTagName(entity_name+"s").item(0).appendChild(e);
			getWriter().writeContent();
		} catch (TransformerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void delete(Guest obj) {
		Document d = getWriter().getDocument();
		NodeList nl = d.getElementsByTagName(entity_name); 
		Guest g;
		Element e;
		for (int i = 0; i < nl.getLength(); i++) {
			e = (Element) nl.item(i);
			g=takeGuest(e);
			if(obj.getId()==g.getId()){
				Node root = d.getElementsByTagName(entity_name+"s").item(0);
				root.removeChild(nl.item(i));
				try {
					getWriter().writeContent();
				} catch (TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			};
		}
		
	}

	@Override
	public void update(Guest obj) {
		Document d = getWriter().getDocument();
		NodeList nl = d.getElementsByTagName(entity_name); 
		Guest g;
		Element e;
		for (int i = 0; i < nl.getLength(); i++) {
			e = (Element) nl.item(i);
			g=takeGuest(e);
			if(obj.getId()==g.getId()){
				Node root = d.getElementsByTagName(entity_name+"s").item(0);
				root.removeChild(nl.item(i));
				root.appendChild(toNode(obj));
				try {
					getWriter().writeContent();
				} catch (TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
			};
		}
	}

	private MyXmlWriter getWriter() {
		return writer;
	}

	private void setWriter(MyXmlWriter writer) {
		this.writer = writer;
	}
	
	
	
	private Guest takeGuest(Element elem){
		if(elem == null) return null;
		Guest g = new Guest();
		g.setId(Long.parseLong(elem.getElementsByTagName( entity_fields[0]).item(0).getTextContent()));
		g.setName(elem.getElementsByTagName( entity_fields[1]).item(0).getTextContent());
		g.setSurname(elem.getElementsByTagName( entity_fields[2]).item(0).getTextContent());
		return g;
	}
	
	
	private Element toNode(Guest guest){
		Element elem = getWriter().getDocument().createElement(entity_name);
		
		Element elemchild = getWriter().getDocument().createElement(entity_fields[0]);
		elemchild.appendChild(getWriter().getDocument().createTextNode(Long.toString(guest.getId())));
		elem.appendChild(elemchild);

		elemchild = getWriter().getDocument().createElement(entity_fields[1]);
		elemchild.appendChild(getWriter().getDocument().createTextNode(guest.getName()));
		elem.appendChild(elemchild);
		
		elemchild = getWriter().getDocument().createElement(entity_fields[2]);
		elemchild.appendChild(getWriter().getDocument().createTextNode(guest.getSurname()));
		elem.appendChild(elemchild);
		
		return elem;
	}

	private MyXmlReader getReader() {
		return reader;
	}

	private void setReader(MyXmlReader reader) {
		this.reader = reader;
	}

}
