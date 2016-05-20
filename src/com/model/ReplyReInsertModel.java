package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ReplyBoardDAO;
import com.dao.ReplyVO;

public class ReplyReInsertModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("EUC-KR");
		String bno=req.getParameter("bno");
		String no=req.getParameter("no");
		String page=req.getParameter("page");
		String msg=req.getParameter("reply_data");
		// DB연동 
		// 상위 댓글의 정보 읽는다 
		ReplyVO pvo=ReplyBoardDAO.replyParentData(Integer.parseInt(no));
		// group_step변경 
		ReplyBoardDAO.replyDataModify(pvo);
		
		ReplyVO vo=new ReplyVO();
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(Integer.parseInt(no));
		vo.setBno(Integer.parseInt(bno));
		vo.setId("hong");
		vo.setName("홍길동");
		vo.setMsg(msg);
		ReplyBoardDAO.replyReInsert(vo);
		ReplyBoardDAO.replyReDepthIncrement(Integer.parseInt(no));
		//ReplyBoardDAO.replyInsert(vo);
		req.setAttribute("no", bno);
		req.setAttribute("page", page);
		return "view/reply_insert.jsp";
	}

}
