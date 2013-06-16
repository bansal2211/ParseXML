package com.example.parsexml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler{
	private ArrayList<ItemData> itemList = new ArrayList<ItemData>();
	String value = "";
	ItemData item = null;
	Boolean flag = false;

	public ArrayList<ItemData> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<ItemData> itemList) {
		this.itemList = itemList;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		if(flag){
			value = new String(ch, start, length);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		flag = false;
		
		if(localName.equalsIgnoreCase("title")){
			item.setTitle(value);
		}
		if(localName.equalsIgnoreCase("link")){
			item.setLink(value);
		}
		if(localName.equalsIgnoreCase("item")){
			itemList.add(item);
		}
		
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		flag = true;
		value = "";
		if(localName.equalsIgnoreCase("item")){
			item = new ItemData();
			
		}
	}
	
	

}
