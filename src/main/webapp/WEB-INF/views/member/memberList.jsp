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
		<h1>memberlist</h1>
		
		 <form class ="col-xs-6" action="./memberList">
		    <div class="input-group">
		     <select class="form-control" id="sel1" name="kind">
		   		<option value="id">ID</option>
		    	<option value="na">NAME</option>
		    	<option value="ph">PHONE</option>
		    	<option value="em">EMAIL</option>
  			</select>
			</div>
		      <input type="text" class="form-control" placeholder="Search" name="search">
		      <div class="input-group-btn">
		        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
		      </div>
		    </div>
		  </form>
		<div id="result">
		<table class="table table-hover">
			<tr>
				<td>id</td>
				<td>name</td>
				<td>phone</td>
				<td>email</td>		
				<td><input type="checkbox" id="checkAll">
				<button id="del" class="btn btn-danger">delete</button></td>
			</tr>
			<c:forEach items="${list}" var="vo" varStatus="i">
			<tr>
				<td id="id${i.index}">${vo.id}</td>
				<td>${vo.name}</td>
				<td>${vo.phone}</td>
				<td>${vo.email}</td>	
				<td><input type="checkbox" title="id${i.index}" id="${vo.id}" name="del" class="check"></td>	
			</tr>

			</c:forEach>
		</table>
		
		<div>
			<ul class="pagination">
			<c:if test="${pager.curBlock>1}"><li><a href="./memberList?curPage=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}">이전</a></li>
			</c:if>
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<li><a href="./memberList?curPage=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
			</c:forEach>
			<c:if test="${pager.totalBlock>pager.curBlock}"><li><a href="./memberList?curPage=${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}">다음</a></li>
			</c:if>
					</ul>
		</div>
	
		</div>

	
		<!--</div>
			<a href="./${board}Write" class="btn btn-danger">write</a>
		</div>-->
<script type="text/javascript">
//다중체크
$(function() {
		
		$("#result").on("click","#checkAll", function() {
			$(".check").prop("checked", $(this).prop("checked"));	
		});
	
		$("#result").on("click", ".check", function() {
			var result=true;
			$(".check").each(function() {
				if(!$(this).prop("checked")){
					result=false;
				}
			});
			
			$("#checkAll").prop("checked", result);
		});
		
		$("#result").on("click", "#del", function() {
			var ids = [];//빈 배열 생성
			$(".check").each(function() {
				if($(this).prop("checked")){
					//var id = $(this).attr("title");
					//alert($("#"+id).text());
					
					ids.push($(this).attr("id"));
					
				}
			});
			
			console.log(ids);
			//foreach 끝
			$.ajax({
				type:"get",
				traditional:true,
				url:"./memberDeletes",
				data: {
					ids:ids
				},
				success:function(data){
					$.get("./memberLists", function(data) {
						$("#result").html(data.trim());
					});
				}
				
			});
		});
		
		
		
});


















</script>
</body>
</html>