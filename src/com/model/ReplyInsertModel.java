package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ReplyBoardDAO;
import com.dao.ReplyVO;

public class ReplyInsertModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("EUC-KR");
		String bno=req.getParameter("bno");
		String no=req.getParameter("no");
		String page=req.getParameter("page");
		String msg=req.getParameter("reply_data");
		// DB¿¬µ¿ 
		ReplyVO vo=new ReplyVO();
		vo.setBno(Integer.parseInt(bno));
		vo.setId("hong");
		vo.setName("È«±æµ¿");
		vo.setMsg(msg);
		ReplyBoardDAO.replyInsert(vo);
		req.setAttribute("no", bno);
		req.setAttribute("page", page);
		return "view/reply_insert.jsp";
	}

}
