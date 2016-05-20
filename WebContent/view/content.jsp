<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="view/table.css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
var w=0;// 댓글달기
var m=0;// 수정하기
var d=0;// 삭제하기
$(function(){ // window.onload , $(document).ready(function(){})
	$('.img_write').click(function(){
		var id=$(this).attr('id');
		var no=id.substring(2);
		if(w==0)
		{
			$('#w'+no).show();
			w=1;
		}
		else
		{
			$('#w'+no).hide();
			w=0;
		}
	});
	$('.img_modify').click(function(){
		var id=$(this).attr('id');
		var no=id.substring(2);
		if(m==0)
		{
			$('#m'+no).show();
			m=1;
		}
		else
		{
			$('#m'+no).hide();
			m=0;
		}
	});
});
</script>
</head>
<body>
  <center>
    <h3>내용보기</h3>
    <table id="table_content">
     <tr>
      <td class="tdcenter" width=20%>번호</td>
      <%--
           request.setAttribute("vo",vo)
           request.getAttribute("vo").getNo()
           ${vo.no }
           
           ${requestScope.no} => ${no} request.setAttribute("no",10)
           ${sessionScope.no} => session.setAttribute("no",10)
           ${param.id} => request.getParameter("id")
       --%>
      <td class="tdcenter" width=30%>${vo.no }</td>
      <td class="tdcenter" width=20%>작성일</td>
      <td class="tdcenter" width=30%>
       <fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
      </td>
     </tr>
     <tr>
      <td class="tdcenter" width=20%>이름</td>
      <td class="tdcenter" width=30%>${vo.name }</td>
      <td class="tdcenter" width=20%>조회수</td>
      <td class="tdcenter" width=30%>${vo.hit }</td>
     </tr>
     <tr>
      <td class="tdcenter" width=20%>제목</td>
      <td class="tdleft" colspan="3">${vo.subject }</td>
     </tr>
     <tr>
      <td class="tdleft" colspan="4" valign="top" height=100>
       <pre>${vo.content }</pre>
      </td>
     </tr>
    </table>
    <table id="table_content">
     <tr>
      <td align=right>
        <img src="view/image/btn_modify.gif" border=0>
        <img src="view/image/btn_delete.gif" border=0>
        <a href="list.do?page=${page }">
        <img src="view/image/btn_list.gif" border=0></a>
      </td>
     </tr>
     <tr id="deletePwd" style="display:">
         <td colspan="2" align=right>
         <form method=post action="board_delete.do">
         <input type="hidden" value="${vo.no }" name="no">
         <input type="hidden" value="${page }" name="page">
                      비밀번호:<input type="password" name=pwd size=10>
          <button>삭제</button>
         </form>
         </td>
       </tr>
    </table>
    <table id="table_content">
      <tr>
        <th>댓글보기</th>
      </tr>
    </table>
    <table id="table_content">
     <c:forEach var="rvo" items="${list }">
       <tr>
        <td width="75%" height=30>
         <c:if test="${rvo.group_tab>0 }">
          <c:forEach var="i" begin="1" end="${rvo.group_tab }">
          &nbsp;&nbsp;
          </c:forEach>
          <img src="view/image/icon_reply.gif">
         </c:if>
         
          <pre>${rvo.msg }</pre>
         
         <br>
         <c:if test="${rvo.group_tab>0 }">
          <c:forEach var="i" begin="1" end="${rvo.group_tab }">
          &nbsp;&nbsp;
          </c:forEach>
         </c:if>
         <font color="blue">${rvo.name }</font>
         (${rvo.dbday })
        </td>
        <td width="25%">
          <img src="view/image/btn_reply.gif" id="ww${rvo.no }" class="img_write">&nbsp;
          <c:if test="${sessionScope.id==rvo.id }">
            <img src="view/image/btn_modify.gif" id="mm${rvo.no }" class="img_modify">&nbsp;
            <a href="reply_delete.do?no=${rvo.no }&bno=${vo.no}&page=${page}">
            <img src="view/image/btn_delete.gif" id="dd${rvo.no }" class="img_delete"></a>
          </c:if>
        </td>
       </tr>
       
       <tr id="w${rvo.no }" style="display:none">
        <td class="tdleft" colspan=2>
         <form method="post" action="reply_re_insert.do">
          <input type="hidden" name="no" value="${rvo.no }">
          <input type="hidden" name="bno" value="${vo.no }">
          <input type="hidden" name="page" value="${page }">
          <textarea rows="4" cols="62" name="reply_data" style="float: left"></textarea>
          <button style="height:57px;width:50px">댓글달기</button>
         </form>
        </td>
      </tr>
      <tr id="m${rvo.no }" style="display:none">
        <form method="post" action="reply_update.do">
         <td class="tdleft" colspan=2>
          <input type="hidden" name="no" value="${rvo.no }">
          <input type="hidden" name="bno" value="${vo.no }">
          <input type="hidden" name="page" value="${page }">
          <textarea rows="4" cols="62" name="reply_data" style="float: left">${rvo.msg }</textarea>
          <button style="height:57px;width:50px">댓글수정</button>
         </td>
        </form>
      </tr>
     </c:forEach>
    </table>
    <table id="table_content">
      <tr>
        <td class="tdleft">
         <form method="post" action="reply_insert.do" id="ri">
          <input type=hidden name="bno" value="${vo.no }">
          <input type=hidden name="no" value="${rvo.no }">
          <input type=hidden name="page" value="${page }"> 
          <textarea rows="4" cols="62" name="reply_data" style="float: left"></textarea>
          <button style="height:57px;width:50px" id="reply_insert">댓글달기</button>
         </form>
        </td>
      </tr>
    </table>
  </center>
</body>
</html>




