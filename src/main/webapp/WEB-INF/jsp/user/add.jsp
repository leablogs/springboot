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
		$("#submit").click(function() {
			var userName = $("#userName").val();
			var note = $("#note").val();
			if ($.trim(userName) == "") {
				alert("username is not empty");
				return;
			}
			if ($.trim(note) == "") {
				alert("note is not empty");
				return;
			}
			var params = {
				username : userName,
				note : note
			}
			$.post({
				url : "./insert",
				contentType : "application/json",
				data : JSON.stringify(params),
				success : function(result) {
					if (result == null || result.id == null) {
						alert("insert fail");
						return;
					}
					alert("insert success");
				}
			})
		})
	})
</script>
</head>
<body>

	<form id="insertForm">
		<table>
			<tr>
				<td>username:</td>
				<td><input id="userName" name="userName" /></td>
			</tr>
			<tr>
				<td>note:</td>
				<td><input id="note" name="note" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input id="submit" type="button" value="submit" /></td>
			</tr>
		</table>
	</form>





















</body>
</html>