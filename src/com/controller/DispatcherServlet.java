package com.controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Model;

import java.util.*;
// list.do ==> list
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //private Map clsMap=new HashMap();
    /*private String[] strCls={
    	"com.model.ListModel",
    	"com.model.InsertModel",
    	"com.model.ContentModel"
    };
    private String[] strCmd={
    	"list",
    	"insert",
    	"content"
    };*/
	private WebApplicationContext wc;
	public void init(ServletConfig config) throws ServletException {
		try
		{
			String path=config.getInitParameter("xmlPath");
			wc=new WebApplicationContext(path);
			/*for(int i=0;i<strCmd.length;i++)
			{
				Class clsName=Class.forName(strCls[i]);
				// 정보 (클래스 정보 읽기)
				Object obj=clsName.newInstance();
				// 메모리 할당 ==> 리플렉션 (클래스,메소드,필드,매개변수)
				System.out.println(strCmd[i]+"|"+obj);
				clsMap.put(strCmd[i], obj);
			}*/
		}catch(Exception ex){}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://localhost:8080/MVCProject/list.do
		try
		{
			// 사용자가 요청한 내용 
			String cmd=request.getRequestURI();
			cmd=cmd.substring(request.getContextPath().length()+1, 
					cmd.lastIndexOf('.'));
			Model model=wc.getBean(cmd);
			String jsp=model.handlerRequest(request, response);
			// forward
			RequestDispatcher rd=
					request.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

}





