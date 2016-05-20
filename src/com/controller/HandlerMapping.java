package com.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// xml 파싱 
/*
 *   XML 파싱 : XML에 설정된 데이터를 읽어온다 
 *      XML => <a>aaa</a> <bean id="list">
 *      방법 
 *       DOM (Document Object Model) : 수정 ,삭제,추가
 *       SAX (Simple API for XML): readonly
 *          <?xml version="1.0"> => startDocument()
 *           <a>                 => startElement()
 *            <b>aaa</b>         => startElement() charactors 
 *                                  endElement()
 *            <b>ccc</b>
 *           </a>
 *       ==== JAXP
 *       JAXB
 *         마셀 ( 객체 => xml)
 *         언마셜 ( xml => 객체 )
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












