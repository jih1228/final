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
    <title>예매내역</title>
</head>
<body>
<%@ include file="../board/header.jsp" %>
  <div class="nav_container">
    <ul class="nav nav-tabs">
      <li class="nav-item">
        <a class="nav-link active" style="text-align:center; width:300px; " data-toggle="tab" href="/myReservation" >예매내역</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" style="text-align:center; width:300px; " data-toggle="tab" href="/myBoard">게시글정보</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" style="text-align:center; width:300px; " data-toggle="tab" href="/myInfo">회원정보수정</a>
      </li>
    </ul>
  
<h4 style="font-weight: 600; margin-top: 100px;">예매내역</h4>
<hr color="black">
  <table class="table table-hover">
    <thead>
      <tr class="table-active">
        <th scope="col">예매번호</th>
        <th scope="col">영화제목</th>
        <th scope="col">상영관명</th>
        <th scope="col">상영날짜</th>
        <th scope="col">예매날짜</th>
        <th scope="col">예매인원</th>
        <th scope="col">예매좌석</th>
        <th scope="col">구입가격</th>
        <th scope="col">취소여부</th>
      </tr>
    </thead>
    <tbody>
    <tr>
        <td>Column</th>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td><button type="button" class="btn btn-outline-primary">취소</button></td>
      </tr>
      <tr>
        <td>Column</th>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td><button type="button" class="btn btn-outline-primary">취소</button></td>
      </tr>
      <tr>
        <td>Column</th>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td>Column</td>
        <td><button type="button" class="btn btn-outline-primary">취소</button></td>
      </tr>
    </tbody>
</table>
<hr color="lightgray">

</div>
</body>
</html>