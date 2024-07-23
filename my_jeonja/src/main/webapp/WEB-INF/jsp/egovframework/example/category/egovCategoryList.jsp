<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="title.sample" /></title>
    <link rel="stylesheet" href="<c:url value='/assets/compiled/css/app.css'/>">
    <link rel="stylesheet" href="<c:url value='/assets/compiled/css/app-dark.css'/>">
    <script type="text/javaScript" language="javascript" defer="defer">
        <!--
        /* 글 수정 화면 function */
        function fn_egov_select(id) {
//         	document.listForm.selectedId.value = id;
//            	document.listForm.action = "<c:url value='/category/updateCategoryView.do'/>";
//            	document.listForm.submit();
            document.location.href = "<c:url value='/category/updateCategoryView.do'/>?selectedId=" + id;
        }
        
        /* 글 등록 화면 function */
        function fn_egov_addView() {
//            	document.listForm.action = "<c:url value='/category/addCategory.do'/>";
//            	document.listForm.submit();
           document.location.href = "<c:url value='/category/addCategory.do'/>";
        }
        
        /* 글 목록 화면 function */
        function fn_egov_selectList() {
        	document.listForm.action = "<c:url value='/category/egovCategoryList.do'/>";
           	document.listForm.submit();
        }
        
        /* pagination 페이지 링크 function */
        function fn_egov_link_page(pageNo){
        	document.listForm.pageIndex.value = pageNo;
        	document.listForm.action = "<c:url value='/category/egovCategoryList.do'/>";
           	document.listForm.submit();
        }
        
        //-->
    </script>
</head>

<body>
<div id="app">
		<c:import url="/sidebar.do" />
		<div id="main">
        <div class="page-heading">
            <h3>Category List</h3>
            <section class="section">
                <div class="card">
                    <div class="card-body">
    <form id="listForm" name="listForm" method="post">
        <input type="hidden" name="selectedId" />
        <div id="content_pop">
        	<!-- 타이틀 -->
        	<div id="title">
        		<ul>
        			<li><img src="<c:url value='/images/egovframework/example/title_dot.gif'/>" alt=""/>Category 자료</li>
        		</ul>
        	</div>
        	<!-- // 타이틀 -->
        	<div id="search">
        		<ul>
        			<li>
        			    <label for="searchCondition" style="visibility:hidden;">선택</label>
        				<select id="searchCondition" name="searchCondition" class="use">
        					<option value="1">After</option>
        					<option value="0">Contains</option>
        				<select>
        			</li>
        			<li><label for="searchKeyword" style="visibility:hidden;display:none;">입력</label>
                        <input id="name" name="name" class="txt" type="text" value=""/>
                    </li>
        			<li>
        	            <span class="btn_blue_l">
        	                <a href="javascript:fn_egov_selectList();">검색</a>
        	                <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
        	            </span>
        	        </li>
                </ul>
        	</div>
        	<!-- List -->
        	<div id="table">
        		<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="카테고리ID, 케테고리명">
        			<caption style="visibility:hidden">카테고리ID, 케테고리명</caption>
        			<colgroup>
        				<col width="100"/>
        				<col width="?"/>
        			</colgroup>
        			<tr>
        				<th align="center">카테고리ID</th>
        				<th align="center">카테고리명</th>
        			</tr>
        			<c:forEach var="result" items="${resultList}" varStatus="status">
            			<tr>
            				<td align="center" class="listtd"><a href="javascript:fn_egov_select('${result.id}')">${result.id}</a></td>
            				<td align="left" class="listtd"><c:out value="${result.name}"/>&nbsp;</td>
            			</tr>
        			</c:forEach>
        		</table>
        	</div>
        	<!-- /List -->
        	<div id="paging">
        		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
        		<input type="hidden" id="pageIndex" name="pageIndex" />
        	</div>
        	<div id="sysbtn">
        	  <ul>
        	      <li>
        	          <span class="btn_blue_l">
        	              <a href="javascript:fn_egov_addView();">등록</a>
                          <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                      </span>
                  </li>
              </ul>
        	</div>
        </div>
    </form>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>
</body>
</html>