package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ReplyBoardDAO;
import com.dao.ReplyVO;

public class ReplyDeleteModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String bno=req.getParameter("bno");
		String no=req.getParameter("no");
		String page=req.getParameter("page");
		// DB¿¬µ¿ 
		ReplyVO vo=
				ReplyBoardDAO.replyInfoData(Integer.parseInt(no));
		if(vo.getDepth()==0)
		{
			ReplyBoardDAO.replyDelete(Integer.parseInt(no));
		}
		else
		{
			ReplyBoardDAO.replyMsgUpdate(Integer.parseInt(no));
		}
		ReplyBoardDAO.replyDepthDecrement(vo.getRoot());
		req.setAttribute("no", bno);
		req.setAttribute("page", page);
		return "view/reply_insert.jsp";
	}

}







