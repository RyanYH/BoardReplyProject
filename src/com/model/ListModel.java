package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.dao.*;
public class ListModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String page=req.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=5;
		int start=(curpage*rowSize)-rowSize;
		int end=curpage*rowSize;
		map.put("start", start);
		map.put("end", end);
		
		List<ReplyBoardVO> list=ReplyBoardDAO.boardAllData(map);
		for(ReplyBoardVO vo:list)
		{
			vo.setReplyCount(ReplyBoardDAO.boardReplyCount(vo.getNo()));
		}
		int totalpage=ReplyBoardDAO.boardTotalPage();
		
		int block=5;
		int fromPage=((curpage-1)/block*block)+1;
		int toPage=((curpage-1)/block*block)+block;
		// << [1][2][3][4][5]>> => << [6][7][8][9] >>
		if(toPage>totalpage)
		   toPage=totalpage;
		req.setAttribute("list", list);
		req.setAttribute("curpage", curpage);
		req.setAttribute("totalpage", totalpage);
		req.setAttribute("block", block);
		req.setAttribute("fromPage", fromPage);
		req.setAttribute("toPage", toPage);
		return "view/list.jsp";
	}

}







