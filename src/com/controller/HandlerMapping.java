package com.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// xml �Ľ� 
/*
 *   XML �Ľ� : XML�� ������ �����͸� �о�´� 
 *      XML => <a>aaa</a> <bean id="list">
 *      ��� 
 *       DOM (Document Object Model) : ���� ,����,�߰�
 *       SAX (Simple API for XML): readonly
 *          <?xml version="1.0"> => startDocument()
 *           <a>                 => startElement()
 *            <b>aaa</b>         => startElement() charactors 
 *                                  endElement()
 *            <b>ccc</b>
 *           </a>
 *       ==== JAXP
 *       JAXB
 *         ���� ( ��ü => xml)
 *         �𸶼� ( xml => ��ü )
 */
import java.util.*;
public class HandlerMapping extends DefaultHandler{
    Map map=new HashMap();
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		try
		{
			if(qName.equals("bean"))
			{
				String id=attributes.getValue("id");
				String cls=attributes.getValue("class");
				
				Class clsName=Class.forName(cls);
				Object obj=clsName.newInstance();
				map.put(id, obj);
				
			}
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
    
}












