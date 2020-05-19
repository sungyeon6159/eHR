<%--
  /**
  * Class Name : login.jsp
  * Description : 로그인
  * Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2020. 5. 6.            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.01.06
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="hContext" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title><spring:message code="message.user.login.title" /></title>

<!-- 부트스트랩 -->
<link href="${hContext}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<!-- div container -->
	<div class="container">
		
		<div class="row">
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 "></div>
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 ">
                <!-- div title -->
                <div>
                    <h2 class="text-center"><spring:message code="message.user.login.title" /></h2>
                </div>  
                <form action="${hContext}/login/login.do" class="form-horizontal" name="login_form" method="get">
				    <!-- div 언어 -->
				    <div class="form-group">
				         <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">   
						     <select class="form-control" name="lang" id="lang">
						       <option value="en"><spring:message code='message.user.login.language.en' /></option>
						       <option value="ko"><spring:message code='message.user.login.language.ko' /></option>
						     </select>
					     </div>
				    </div>
                        
                    <!-- div 아이디 -->
                    <div class="form-group">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <input class="form-control" type="text" name="member_id" id="member_id" size="30"
                             placeholder="<spring:message code='message.user.login.id' />"
                             maxlength="20"> 
                        </div>
                    </div>
                    <!-- div 비번 -->
                    <div class="form-group">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <input class="form-control" type="password" name="member_passwd" id="member_passwd" size="30"
                             placeholder="<spring:message code='message.user.login.password' />"
                             maxlength="50">
                        </div>
                    </div>   
                </form>	
                    <!-- div 버튼 -->
                    <div class="text-center">
                        <button type="button" class="btn btn-lg btn-primary btn-block"  id="member_login" size="30">
                            <spring:message code='message.user.login.login.btn' />
                        </button>  
                    </div>                     
                	
		    </div>
		</div>
	</div>
	
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${hContext}/resources/js/jquery-migrate-1.4.1.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${hContext}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	   $("#member_login").on("click",function(){
		  // console.log("member_login");
		    document.login_form.submit();
	   });
	</script>
</body>
</html>










