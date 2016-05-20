package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ReplyBoardDAO;

public class DeleteModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String no=req.getParameter("no");
		String page=req.getParameter("page");
		String pwd=req.getParameter("pwd");
		boolean bCheck=false;
		String db_pwd=ReplyBoardDAO.boardGetPwd(Integer.parseInt(no));
		if(pwd.equals(db_pwd))
		{
			ReplyBoardDAO.boardDelete(Integer.parseInt(no));
			bCheck=true;
		}
		else
		{
			bCheck=false;
		}
		req.setAttribute("bCheck", bCheck);
		req.setAttribute("page", page);
		return "view/delete.jsp";
	}

}
