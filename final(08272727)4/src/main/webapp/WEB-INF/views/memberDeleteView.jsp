<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>



<html>
	<head>
		<link rel="stylesheet" href="/resources/css/simplex.css">
		<title>회원탈퇴</title>
	</head>
	
	
	<body>
	<sec:authorize access="isAuthenticated()">

       	<sec:authentication property="principal.username" var="userid" />
       	<sec:authentication property="principal.member.userName" var="userName"/>
       	<sec:authentication property="principal.member.userEmail" var="userEmail"/>
		<section id="container">
			<form action="/memberDelete" method="POST" id="delForm">
				<div class="form-group has-feedback">
					<label class="control-label" for="userid">아이디</label>
					<input class="form-control" type="text" id="userid" name="userid" value="${userid}" readonly="readonly"/>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="userpw">패스워드</label>
					<input class="form-control" type="password" id="userpw" name="userpw"/>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="userName">성명</label>
					<input class="form-control" type="text" id="userName" name="userName" value="${userName}" readonly="readonly"/>
				</div>
				<div class="form-group has-feedback">
					<button class="btn btn-success" type="button" id="sub" >회원탈퇴</button>
					<button class="cencle btn btn-danger" type="button">취소</button>
				</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			<div>
				<c:if test="${msg == false}">
					비밀번호가 맞지 않습니다.
				</c:if>
			</div>
		</section>
		
	</sec:authorize>
	 <!-- jQuery -->
    <script src="/resources/js/jquery-3.5.1.min.js"></script>
	
		<script type="text/javascript">
		var csrfHeaderName = "${_csrf.parameterName}";
		var csrfTokenValue = "${_csrf.token}";
		
		$(document).ready(function(){
			// 취소
			$(".cencle").on("click", function(){
				
				location.href = "/";
						    
			})
		
			$("#sub").on("click", function(){
				if($("#userpw").val()==""){
					alert("비밀번호를 입력해주세요.");
					$("#userpw").focus();
					return false;
				}
			$.ajax({
				url : "/passChk",
				type : "POST",
				dataType : "json",
				data : $("#delForm").serializeArray(),
				success : function(data) {
				console.log('data:' + data);	
					if(data==true){
						if(confirm("회원탈퇴하시겠습니까?")){
						$("#delForm").submit();
						 	}
					}else{
						alert("패스워드가 틀렸습니다.");
						return;
						}
					}
					
				});
			});  
			
		});
	
		
	</script>
		
	</body>
	
</html>