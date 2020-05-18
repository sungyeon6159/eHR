<%--
  /**
  * Class Name : 
  * Description : bootstrap list 템플릿
  * Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2020. 3. 9.            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.01.06
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/common/common.jsp"%>
      
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>부트스트랩 HR_LIST 등록</title>

    <!-- 부트스트랩 -->  
    <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">

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
      <!-- div title --> 
      <div class="page-header">
        <h2>Validator</h2>
      </div>
      <!--// div title -->
        

		</div>
      <!-- div title -->
      <form class="form-horizontal" name="registerForm" id="registerForm">
	      <div class="form-group">
	      <label for="inputEmail3" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">제목</label>
		      <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
		      	<input type="text" class="form-control" id="title" name="title" placeholder="제목">
		      </div>
	      </div>
	      <div class="form-group">
	       <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
	           <input  type="submit" class="btn btn-primary btn-sm"  value="등록" id="to_list_btn" />
	       </div>
	      </div>
     </form>
 </div>     
 <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
     <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="${hContext}/resources/js/jquery-migrate-1.4.1.js"></script>
    <!-- jQuery validator -->
    <script src="${hContext}/resources/js/jquery.validate.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${hContext}/resources/js/bootstrap.min.js"> </script>
    
    <!-- 
        jquery.validate.js
        -HTML form안에 입력형식 검사에 사용.
        -검사규칙,사용자에게 보여줄 메시지를 JSON형식으로 정의
    
              환경설정: 순서 의미 있음
        -src="${hContext}/resources/js/jquery-migrate-1.4.1.js"
        -src="${hContext}/resources/js/jquery.validate.js"
        
       form요소의  validate 메소드
        -$("폼에대한 셀렉터").validate({검사규칙});
       
             검사규칙
        -$("폼에대한 셀렉터").validate({
           //테스트를 위하여 유효성 검사가 완료되어도 submit처리 하지 않음
           debug:true,
                    
           //검사할 필드,항목의 나열
           rules:{...},
        
           //검사 메시지
           messages:{.....}
        });     
       
     -->
    <script type="text/javascript">
         function bindEventHandler(){
        	 $("#registerForm").validate({
                 onfocus: false,
                 //서버전송여부
                 debug: true,
                  
                 rules: {
                     title:{
                        //필수값
                        required: true,
                        //최소길이
                        minlength: 3,
                        //최대길이
                        maxlength: "10",
                        //숫자만 입력
                        digits: true 
                     } 

                 },messages: {
                     //message
                     title:{
                        //필수값 
                        required: "제목은 필수값 입니다.",
                        //최소길이
                        minlength: $.validator.format('{0}자 이상 입력하세요.'),
                        //최대길이
                        maxlength: $.validator.format('{0}자 내로 입력하세요.'),
                        //숫자만 입력
                        digits: "숫자만 입력 입니다."
                        
                     }    

                 },errorPlacement:function(error,element){
                     //doNothing
                 },invalidHandler:function(form,validator){
                     //alert
                     var errors = validator.numberOfInvalids();
                     console.log("errors:"+errors);
                     if(errors){
                         console.log("validator.errorList[0].message:"+validator.errorList[0].message);    
                         alert(validator.errorList[0].message);
                         validator.errorList[0].element.focus();
                     }

                 }


          });

         }
    
         $(document).ready(function(){
             //input validation
        	 bindEventHandler();
        	    
         });

    </script>
    
    
    
  </body>
</html>












