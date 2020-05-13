<%--
  /**
  * Class Name : board_list.jsp
  * Description : 게시판 목록
    http://localhost:8080/ehr/board/do_retrieve.do?pageNum=1&pageSize=10&searchDiv=&searchWord=
  * Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2020. 3. 10.            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.01.06
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>
<%@page import="com.sist.ehr.cmn.StringUtil"%>
<%@page import="com.sist.ehr.cmn.SearchVO"%>
<%@page import="com.sist.ehr.code.service.CodeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/common/common.jsp"%>
<%
		//페이지 사이즈
	String pageSize = "10";
	
	//페이지 num
	String pageNum = "1";
	
	//검색구분
	String searchDiv = "";
	
	//검색어
	String searchWord = "";
	
	SearchVO search = (SearchVO) request.getAttribute("vo");
	if (null != search) {
		pageSize = String.valueOf(search.getPageSize());
		pageNum = String.valueOf(search.getPageNum());
		searchDiv = search.getSearchDiv();
		searchWord = search.getSearchWord();
	}
	//out.print("search:"+search);
	
	List<CodeVO> searchList = (List<CodeVO>) request.getAttribute("searchList");
	//out.print("searchList:"+searchList);
	/*     for(CodeVO vo:searchList){
	    	out.print(vo.toString()+"<br/>");
	    } */
	
	//pageSizeList
	List<CodeVO> pageSizeList = (List<CodeVO>) request.getAttribute("pageSizeList");
	//out.print("pageSizeList:"+pageSizeList);
	/*     for(CodeVO vo:pageSizeList){
	        out.print(vo.toString()+"<br/>");
	    }   */
	
	int totalCnt = 0;
	
	totalCnt = (Integer) request.getAttribute("totalCnt");
	//out.print("totalCnt:"+totalCnt);

    //paging
    String url = H_PATH+"/board/do_retrieve.do";
    String scriptName = "doSeachPage";
    int maxNum =0;//총글수
    int currPageNo=1;//현재페이지 
    int rowPerPage=10;
    int bottomCount=5;//바닫에 page
    
    if(null !=search){
    	currPageNo = search.getPageNum();
    	rowPerPage = search.getPageSize();
    	maxNum     = totalCnt;
    }
    //--paging
    
    	


%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>부트스트랩 템플릿_LIST</title>


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
		<!-- div title -->
		<div class="page-header">
			<h1>게시판 목록</h1>
		</div>
		<!--// div title -->
		<!-- 검색영역 -->
		<div class="row">
			<div class="col-md-12 text-right">
				<form action="${hContext}/board/do_retrieve.do" name="searchFrm"
					method="get" class="form-inline">
					<input type="hidden" name="pageNum" id="pageNum" value="${vo.pageNum }">
					<input type="hidden"   name="boardId" id="boardId" />
					<div class="form-group">
						<%=StringUtil.makeSelectBox(pageSizeList, "pageSize", pageSize, false)%>
						<%=StringUtil.makeSelectBox(searchList, "searchDiv", searchDiv, true)%>
						<input type="text" class="form-control input-sm" id="searchWord"
							name="searchWord" placeholder="검색어"  value="${vo.searchWord }">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" onclick="javascript:doRetrieve();"
							class="btn btn-primary btn-sm">조회</button>
						<button type="button" class="btn btn-primary btn-sm">등록</button>
					</div>
				</form>
			</div>
		</div>
		<!--// 검색영역 -->

		<!-- Grid영역 -->
		<div class="table-responsive">
			<table class="table table-striped table-bordered sung" id="listTable">
				<!-- hidden-sm hidden-xs 숨기기 -->
				<thead class="bg-primary">
					<th class="text-center col-lg-1 col-md-1 col-sm-1 hidden-xs ">번호</th>
					<th class="text-center col-lg-8 col-md-8 col-sm-8 col-xs-8">제목</th>
					<th class="text-center col-lg-1 col-md-1 col-sm-1 col-xs-1">작성자</th>
					<th class="text-center col-lg-1 col-md-1 col-sm-1 hidden-xs  ">작성일</th>
					<th class="text-center col-lg-1 col-md-1 hidden-sm hidden-xs ">조회수</th>
					<th style="display: none;">BoardId</th>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${list.size()>0 }">
							<c:forEach var="vo" items="${list }">
								<tr>
									<td class="text-center hidden-sm hidden-xs"><c:out
											value="${vo.num }" /></td>
									<td class="text-left"><c:out value="${vo.title }" /></td>
									<td class="text-center"><c:out value="${vo.regId }" /></td>
									<td class="text-center hidden-sm hidden-xs  "><c:out
											value="${vo.regDt }" /></td>
									<td class="text-right hidden-sm hidden-xs"><c:out
											value="${vo.readCnt }" /></td>
									<td style="display: none;"><c:out value="${vo.boardId }" /></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="text-center">No data found.</td>
							</tr>
						</c:otherwise>
					</c:choose>

				</tbody>
			</table>
		</div>
		<!--// Grid영역 -->
		<!-- pagenation -->
		<div class="text-center">
			<%=StringUtil.renderPaging(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
		</div>
		<!--// pagenation -->
	</div>
	<!--// div container -->



	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${hContext}/resources/js/jquery-migrate-1.4.1.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${hContext}/resources/js/bootstrap.min.js">
		
	</script>
	<script type="text/javascript">
        function doSeachPage(url,no){
            console.log("#url:"+url);
            console.log("#no:" + no);
            
            var frm = document.searchFrm;
            frm.pageNum.value = no;
            frm.action = url;
            frm.submit();

        }
	
		function doRetrieve() {
			//console.log("doRetrieve");
			var frm = document.searchFrm;
			frm.pageNum.value = "1";
			frm.action = "${hContext}/board/do_retrieve.do";
			frm.submit();
		}

		$("#searchWord").on("keypress", function(e) {
			console.log("#searchWord");
			console.log("#searchWord:" + e.which);
			if (e.which == 13) {
				doRetrieve();
			}

		});

	    $("#listTable>tbody").on("click","tr",function(){
	   // $(".sung").on("click",function(){
	    	//console.log("sung #listTable>tbody");
            var trs = $(this);
            var tds = trs.children();
            var boardId = tds.eq(5).text();
            console.log("boardId:"+boardId);
	    	//board/do_selectone.do
            var frm = document.searchFrm;
            frm.boardId.value = boardId;
            frm.action = "${hContext}/board/do_selectone.do";
            frm.submit();
            
            //get location.href="getURL"            
            //location.href="${hContext}/board/do_selectone.do?boardId="+boardId;
	    	
		});
  

		
	</script>
</body>
</html>





















