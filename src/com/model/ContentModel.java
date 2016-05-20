package com.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.*;
public class ContentModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session=req.getSession();
		session.setAttribute("id", "hong");
		String no=req.getParameter("no");
		String page=req.getParameter("page");
		ReplyBoardVO vo=
			ReplyBoardDAO.boardContentData(Integer.parseInt(no));
		String rp=req.getParameter("rp");
		if(rp==null)
			rp="1";
		int crp=Integer.parseInt(rp);
		int rowSize=10;
		int start=(rowSize*crp)-rowSize;
		int end=rowSize*crp;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("bno", Integer.parseInt(no));
		List<ReplyVO> list=ReplyBoardDAO.replyAllData(map);
		req.setAttribute("vo", vo);
		req.setAttribute("page", page);
		req.setAttribute("list", list);
		return "view/content.jsp";
	}

}






