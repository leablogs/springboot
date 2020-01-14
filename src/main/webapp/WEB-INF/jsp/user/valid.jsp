<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var params = {
			username : "wnag",
			note : "dsf",
			email : "shilh@sina.cn",
			age : 30,
			date : "2020-01-15",
			remark : "sdasda"
		}
		$.post({
			url : "./pojocheck",
			contentType : "application/json",
			data : JSON.stringify(params),
			success : function(result) {
				alert("aaa")
			},
			error : function() {
				alert("bbb")
			}
		})
	})
</script>
</head>
<body>
</body>
</html>