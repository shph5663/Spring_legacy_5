<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<form class="form-horizontal" action="./memberJoin" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label class="control-label col-sm-2" for="id">ID:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="id" placeholder="Enter ID" name="id">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="pw">Password:</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="pw" placeholder="Enter Password" name="pw">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="name">Name:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="name" placeholder="Enter Name" name="name">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="email">Email:</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="email" placeholder="Enter Email" name="email">
					</div>
				</div>				
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="phone">Phone:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="phone" placeholder="Enter Phone" name="phone">
					</div>
				</div>		
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="age">Age:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="age" placeholder="Enter Age" name="age">
					</div>
				</div>		
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="avatar">Avatar:</label>
					<div class="col-sm-10">
						<input type="file" class="form-control" id="avatar" placeholder="Enter Avatar" name="avatar">
					</div>
				</div>			
						
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Submit</button>
					</div>
				</div>
			</form>

		</div>
	</div>

<script type="text/javascript">

$("#id").blur(function() {//여기서 아이디를 조회해서 db로 보낸다
	var id = $("#id").val();

	//방법 1
	//$.post("./memberIdCheck",{id:id}, function(data){
	//	alert(data);
	//});
	
	//방법2
	$.ajax({
		type:"post",  //method 형식
		url :"./memberIdCheck", //URL주소
		data : {id:id}, //parameter
		success : functionata() {
			alert(r);
		},
		error : functionㅇ() {
			alert("error ")
		}
	});
});

</script>





</body>
</html>