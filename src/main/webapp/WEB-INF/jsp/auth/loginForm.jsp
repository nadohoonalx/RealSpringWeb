
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	session.invalidate();
	System.out.println("SESSION ID [" + (session == null ? null : session.getId()) + "]");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <form action="nosession/login" method="POST">
        <span>이름 : </span><input type="text" name="name" placeholder="아이디를 입력하세요."><br>
        <span>암호 : </span><input type="password" name="password" placeholder="아이디를 입력하세요.">
        <input type="submit" value="로그인">
        <d:if test="${error ==  'true'}">
         <font-color="red"><b>이름이나 암호가 일치하지 않습니다.</b></font>
         </d:if>
    </form>
</body>

</html>