<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/resources/css/simplex.css">
    <link rel="stylesheet" href="/resources/css/my_page.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <title>게시글정보</title>
</head>
<body>
<%@ include file="../board/header.jsp" %>
  <div class="nav_container">
    <ul class="nav nav-tabs">
      <li class="nav-item">
        <a class="nav-link" style="text-align:center; width:300px; " data-toggle="tab" href="/myReservation" >예매내역</a>
      </li>
      <li class="nav-item">
        <a class="nav-link active" style="text-align:center; width:300px; " data-toggle="tab" href="/myBoard">게시글정보</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" style="text-align:center; width:300px; " data-toggle="tab" href="/myInfo">회원정보수정</a>
      </li>
    </ul>
  
<h4 style="font-weight: 600; margin-top: 100px;">게시글정보</h4>
<hr color="black">
  <table class="table table-hover">
    <thead>
      <tr class="table-active">
        <th scope="col">번호</th>
        <th scope="col">제목</th>
        <th scope="col">작성날짜</th>
        <th scope="col">조회수</th>
        <th scope="col">삭제여부</th>
      </tr>
    </thead>
    <tbody>
    <tr>
        <td>Column</th>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td><button type="button" class="btn btn-outline-primary">삭제</button></td>
      </tr>
      <tr>
        <td>Column</th>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td><button type="button" class="btn btn-outline-primary">삭제</button></td>
      </tr>
      <tr>
        <td>Column</th>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td><button type="button" class="btn btn-outline-primary">삭제</button></td>
      </tr>
    </tbody>
</table>
<hr color="lightgray">

</div>
</body>
</html>