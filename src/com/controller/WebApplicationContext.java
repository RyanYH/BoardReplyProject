package com.controller;
import java.io.*;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.model.Model;
public class WebApplicationContext {
   private Map clsMap=new HashMap();
   public WebApplicationContext(String path)
   {
	   try
	   {
		   SAXParserFactory spf=SAXParserFactory.newInstance();
		   // �ļ��� ���� (factory ����)
		   SAXParser sp=spf.newSAXParser();
		   // �ļ��� 
		   HandlerMapping hm=new HandlerMapping();
		   sp.parse(new File(path), hm);
		   clsMap=hm.map;
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
   }
   public Model getBean(String id)
   {
	   return (Model)clsMap.get(id);
   }
}




