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
</head>
<body>
  <center>
    <h3>게시판</h3>
    <table id="table_content">
      <tr>
        <th width=10%>번호</th>
        <th width=45%>제목</th>
        <th width=15%>이름</th>
        <th width=20%>작성일</th>
        <th width=10%>조회수</th>
      </tr>
      <%--
          for(ReplyBoardVO vo:request.getAttribute("list"))
          {
          }
       --%>
      <c:forEach var="vo" items="${list }">
        <tr class="dataTr">
          <td width=10% class="tdcenter">${vo.no }</td>
          <%--
             ${vo.no} => out.println(vo.getNo())
           --%>
          <td width=45% class="tdleft">
           <a href="content.do?no=${vo.no }&page=${curpage}">${vo.subject }</a>
           &nbsp;
           <c:if test="${vo.replyCount>0 }">
            (${vo.replyCount })
           </c:if>
          </td>
          <td width=15% class="tdcenter">${vo.name }</td>
          <td width=20% class="tdcenter">
            <fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
          </td>
          <td width=10% class="tdcenter">${vo.hit }</td>
        </tr>
      </c:forEach>
    </table>
    <table id="table_content">
      <tr>
       <td align=right>
         <a href="list.do?page=1">
         <img src="view/image/begin.gif" border=0></a>
         <c:if test="${curpage>block }">
           <a href="list.do?page=${fromPage-1 }">
           <img src="view/image/prev.gif" border=0></a>
         </c:if>
        <c:if test="${curpage<=block }">
         <a href="list.do?page=${curpage>1?curpage-1:curpage }">
         <img src="view/image/prev.gif" border=0></a>
        </c:if>
        <c:forEach var="i" begin="${fromPage }" end="${toPage }">
          <c:if test="${i==curpage }">
           [<font color="red">${i }</font>]
          </c:if>
          <c:if test="${i!=curpage }">
           [<a href="list.do?page=${i }">${i }</a>]
          </c:if>
        </c:forEach>
        <c:if test="${toPage<totalpage }">
        <a href="list.do?page=${toPage+1 }">
        <img src="view/image/next.gif"></a>
        </c:if>
        <c:if test="${toPage>=totalpage }">
        <a href="list.do?page=${curpage<totalpage?curpage+1:curpage }">
        <img src="view/image/next.gif"></a>
        </c:if>
        <a href="list.do?page=${totalpage }">
        <img src="view/image/end.gif"></a>
        &nbsp;&nbsp;
        ${curpage } page / ${totalpage } pages
       </td>
      </tr>
    </table>
  </center>
</body>
</html>





