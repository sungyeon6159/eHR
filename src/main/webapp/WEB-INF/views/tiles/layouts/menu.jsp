<%--
  /**
  * Class Name : menu.jsp
  * Description : tiles menu
  * Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2020. 5. 15.            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.01.06
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<table>

  <tr>
    <td><a href="${context}/board/do_retrieve.do?pageNum=1&pageSize=10&searchDiv=&searchWord=">게시판</a></td>
  </tr>
  <tr>
    <td><a href="${context}/hello/hello.do">hello</a></td>
  </tr>
  <tr>
    <td><a href="${context}/chart/pie_chart_view.do">파이차트</a></td>
  </tr>  
</table>

