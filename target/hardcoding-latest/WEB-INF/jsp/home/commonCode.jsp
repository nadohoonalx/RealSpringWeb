<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

<script>

	util.initSelectBox('Members', 'master', '', 'Y', "study/getMembers");
	util.initSelectBox('upper', 'master', '', 'Y');
	
	//1202마스터 정보를 upper 에서 값을 아래로 채인지 시켜서 lower 로 바인딩을 시킨다
	
	$('#upper').change(function(){
		util.initSelectBox('lower', $(this).val(),'','Y')
	})
</script>
</head>
<body>
	상위코드 : <select id="upper" style="width: 140p;"></select><br>
	하위코드 : <select id="lower" style="width: 140p;"></select><br>
  
  	<hr>
  	
  	회원목록 : <select id="Members" style="width: 140p;"></select><br>
  	
</body>
</html>