<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<c:import url="../tem/boot.jsp"></c:import>

</head>
<body>
<c:import url="../tem/header.jsp"></c:import>

<div class="container">
	<div class="row">
		<h1>${board} list</h1>
		
		 <form class ="col-xs-6" action="./${board}List">
		    <div class="input-group">
		     <select class="form-control" id="sel1" name="kind">
		   		<option value="bt">title</option>
		    	<option value="bc">contents</option>
		    	<option value="bw">writer</option>
  			</select>
			</div>
		      <input type="text" class="form-control" placeholder="Search" name="search">
		      <div class="input-group-btn">
		        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
		      </div>
		    </div>
		  </form>
		
		<table class="table table-hover">
			<tr>
				<td>num</td>
				<td>title</td>
				<td>writer</td>
				<td>date</td>
				<td>hit</td>			
			</tr>
			<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.num}</td>
				<td>
				<c:catch>
				<c:forEach begin="1" end="${dto.depth}">
					-->
					<!-- &nbsp;&nbsp; -->
				</c:forEach>
				</c:catch>
				<a href="./${board}Select?num=${dto.num}">${dto.title}</a></td>
				<td>${dto.writer}</td>
				<td>${dto.regdate}</td>
				<td>${dto.hit}</td>			
			</tr>

			</c:forEach>
		</table>
		
		<div>
			<ul class="pagination">
			<c:if test="${pager.curBlock>1}"><li><a href="./${board}List?curPage=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}">이전</a></li>
			</c:if>
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<li><a href="./${board}List?curPage=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
			</c:forEach>
			<c:if test="${pager.totalBlock>pager.curBlock}"><li><a href="./${board}List?curPage=${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}">다음</a></li>
			</c:if>
			</ul>
		</div>
		
		<a href="./${board}Write" class="btn btn-danger">write</a>
		
		<c:catch>
		<c:choose>
			<c:when test="${board eq 'notice'}">
			<c:if test="${member.id eq 'admin'}">
				<div>
					<a href="./${board}Write" class="btn btn-danger">write</a>
				</div>	
			</c:if>
			</c:when>
			<c:otherwise>
			<c:if test="${not empty member}">
				<div>
					<a href="./${board}Write" class="btn btn-danger">write</a>
				</div>	
			</c:if>
			</c:otherwise>
		</c:choose>
		</c:catch>

</body>
</html>




















