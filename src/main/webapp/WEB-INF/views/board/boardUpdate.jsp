<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../tem/boot.jsp"></c:import>
<c:import url="../tem/summer.jsp"></c:import>
</head>
<body>
<c:import url="../tem/header.jsp"></c:import>

<h1>${board} update form</h1>

<form action="./${board}Update" method="post">
<input type="hidden" name="num" value="${dto.num}">
				<div class="form-group">
					<label class="control-label col-sm-2" for="title">title:</label>
					<div class="col-sm-20">
						<input type="text" class="form-control" id="title" value="${dto.title}"  name="title">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="writer">writer:</label>
					<div class="col-sm-20">
						<input type="text" disabled="disabled" class="form-control" id="writer" value="${dto.writer}" name="writer">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="contents">contents:</label>
					<div class="form-group" >
		    			<label for="contents"></label>
		    			<textarea rows="5" cols="" class="form-control" id="contents" name="contents">${dto.contents}</textarea>
		  		</div> 
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Submit</button>
					</div>
				</div>

</form>

<script type="text/javascript">
	//$(선택자).action();
	$("#contents").summernote({
		  height: 300,                 // set editor height
		  minHeight: null,             // set minimum height of editor
		  maxHeight: null,             // set maximum height of editor
		  focus: true                  // set focus to editable area after initializing summernote
		});
</script>
</body>
</html>