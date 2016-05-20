package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ReplyBoardDAO;
import com.dao.ReplyVO;
/*
 *    요청 
 *                       request
 *     = reply_upadte.do =======> DispatcherServlet
 *                          service(HttpServletRequest request)
 *                             => Model을 찾는다 
 *                             => Model에 request를 넘겨준다
 *                             => Model에서 필요한 데이터를 첨부
 *                                req.setAttribute()
 *                             => jsp를 찾는다 
 *                             => request를 JSP에 전송 
 *                                rd.forward(request)
 */
public class ReplyUpdateModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("EUC-KR");
		String bno=req.getParameter("bno");
		String no=req.getParameter("no");
		String page=req.getParameter("page");
		String msg=req.getParameter("reply_data");
		ReplyVO vo=new ReplyVO();
		vo.setNo(Integer.parseInt(no));
		vo.setMsg(msg);
		ReplyBoardDAO.replyUpdate(vo);
		// content.jsp
		req.setAttribute("no", bno);
		req.setAttribute("page", page);
		return "view/reply_insert.jsp";
	}

}








