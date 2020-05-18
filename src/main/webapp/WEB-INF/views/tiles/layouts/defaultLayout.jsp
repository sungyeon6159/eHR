<%--
  /**
  * Class Name : defaultLayout.jsp.jsp
  * Description : tiles base definition
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
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set  var="hContext" value="${pageContext.request.contextPath }"></c:set>
    
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <!-- 제목 Title동적으로 read:tiles.xml -->
    <title><tiles:getAsString name="title"></tiles:getAsString></title>

    <!-- 부트스트랩 -->
    <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js .는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <table border="1" >
        <tr>
            <!-- tiles header -->
            <td height="30" colspan="2"><tiles:insertAttribute name="header"/></td>
        </tr>
        <tr>
            <!--tiles menu  -->
            <td><tiles:insertAttribute name="menu"/></td>
            <!--tiles body  -->
            <td><tiles:insertAttribute name="body"/></td>
        </tr>
        <tr>
            <!-- tiles footer -->
            <td height="20" colspan="2"><tiles:insertAttribute name="footer"/></td>
        </tr>        
    
    </table>
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->

    <script type="text/javascript">
        

    </script>
  </body>
</html>

    