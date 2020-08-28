<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	
	<link rel="stylesheet" href="/resources/css/simplex.css">
  	<link rel="stylesheet" href="/resources/css/login_page.css">
  	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  	<title>소셜 로그인</title>

</head>
<body>
<%@ include file="../board/header.jsp" %>


   <form role="form" method='post' action="/customLogin">
    <div class="container" style="margin-left: 490px; width:930px;">
      <ul style="z-index:1;" class="nav nav-tabs">
        <li class="nav-item">
          <a class="nav-link " style="text-align:center; width:450px; " data-toggle="tab" href="/customLogin">회원</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" style="text-align:center; width:450px; " data-toggle="tab"
            href="customLogin_social.html">소셜 로그인</a>
        </li>
      </ul>
    </div>
    <div id="myTabContent" class="tab-content">
      <div style="width:100%; height: 300px; margin-top: 20px; background-color: #F2F2F2;">
        <div class="tab-pane fade active show" name="user">
          <div class="container_inner">
            <div class="login_box">
              <div id="naver_id_login" style="text-align: center">
                <a href="${url}"> <img width="223"
                    src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png" /></a>
              </div>
              <div class="login_menu" style="margin: 15px 115px 0 0;">
                <a href="/customSignup" style="color:gray;">회원가입</a> |
                <a href="#" style="color:gray;">아이디찾기</a> |
                <a href="#" style="color:gray;">비밀번호찾기</a>
              </div>
            </div>
            <div class="ad_box">
              <img src="/resources/images/test.jpg" alt="poster">
            </div>
          </div>
        </div>
      </div>
      </div>
  </form>


</body>
    
    
    <!-- jQuery -->
    <script src="/resources/js/jquery-3.5.1.min.js"></script>

  
	
	
	
	
	<script>
	$(".btn-primary").on("click", function(e){
		
		e.preventDefault();
		$("form").submit();
		
	});
	
	</script>

</body>
</html>