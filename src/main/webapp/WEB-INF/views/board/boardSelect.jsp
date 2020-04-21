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
<div class="row">
	<h1>    title : ${dto.title}</h1>
	<h1>    writer : ${dto.writer}</h1>
	<h1>    contents : ${dto.contents}</h1>
</div>

<div>
	<a href="./${board}Update?num=${dto.num}" class="btn btn-primary">Update</a>
	<a href="./${board}Delete?num=${dto.num}" class="btn btn-danger" >Delete</a>
	<c:if test="${board ne 'notice'}"><a href="./${board}Reply?num=${dto.num}" class="btn btn-info" >Reply</a></c:if> 
</div>


</body>
</html>