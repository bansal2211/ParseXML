package com.example.parsexml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class ParseXML extends Activity {
	InputStream xmlStream;
	ArrayList<ItemData> list;
	XMLHandler handler;
	ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_xml);
        try {
			xmlStream = this.getAssets().open("ItemXML.xml");
			ParseXML();
			list = handler.getItemList();
			LoadImagesFromUrl();
			ImageAdapter adapter = new ImageAdapter(ParseXML.this, R.layout.imagelist, list);
			listView = (ListView) findViewById(R.id.imageList);
			listView.setAdapter(adapter);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_parse_xml, menu);
        return true;
    }
    
    public void ParseXML(){
    	
    	try {
    		SAXParserFactory spf = SAXParserFactory.newInstance();
        	SAXParser sp = spf.newSAXParser();
			XMLReader reader = sp.getXMLReader();
			handler = new XMLHandler();
			reader.setContentHandler(handler);
			reader.parse(new InputSource(xmlStream));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void LoadImagesFromUrl(){
    	for (int i=0; i<list.size();i++){
    		LoadChart loader = new LoadChart(ParseXML.this, list.get(i).link);
    		loader.execute();
    		list.get(i).bitmap = loader.getBitmap();
    	}
    }
}
