<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<button>요청하기</button>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		crossorigin="anonymous"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			header := w.Header()
			header.Add("Access-Control-Allow-Origin", "*")
			header.Add("Access-Control-Allow-Methods", "DELETE, POST, GET, OPTIONS")
			header.Add("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With")
		$('button').on('click',function(){
			request();
		})
		var request = function(){
		$.ajax({
		    url: "https://api.iamport.kr/users/getToken",
		    method: "POST", // POST method
		    headers: { "Content-Type": "application/json" }, // "Content-Type": "application/json"
		    data: {
		      imp_key: "3148146972167771", // REST API키
		      imp_secret: "GjkI4r8pEyfLZVLH9TmQC41G6u0DswY9PiRUK0koyzaxuVfEPBurGw8M08psEnBuGtpruJP2j0ANWPsL" // REST API Secret
		    }
		  }).done(function(){
			  alert('요청성공!');
		  }) 
		  
		  
		}
		
		
		
		
		})
		</script>
</body>
</html>