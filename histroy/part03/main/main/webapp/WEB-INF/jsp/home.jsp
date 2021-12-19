<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	<script src="/node_modules/jquery/dist/jquery.min.js"></script>
</head>
<script>
	function getMessage(no) {
		$.ajax({
			method : "GET",
			url : "/study/getMessage" + no,
			dataType : "JSON",
			data : null,
			error : function() {
				alert('error');
			},
			sucess : result
		});
	}
	
	function result(data){
		console.dir(data);
		alert(data.name);
	}
</script>
<body>
<h1>
	Hello world! jsp  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<p>${samki}</p>
<h1>${combo}</h1>
1. 클래스를 이용한 JSON 반환 <input type ="button" value="getMessage1" 
							     onClick="javascript:getMessage(1)"/><br/>
2. Map을 이용한 Json 반환	<input type ="button" value="getMessage2" 
							     onClick="javascript:getMessage(2)"/><br/>

<select id="type" style ="width:140px"></select>				     
</body>
</html>
