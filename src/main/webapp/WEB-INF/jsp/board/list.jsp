<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <link rel="stylesheet" href="/node_modules/tui-grid/dist/tui-grid.min.css">
            <script src="/node_modules/tui-grid/dist/tui-grid.min.js"></script>
       
     
        </head>

<script>

	function btnRegi(){
		location.href="board/boardReg";
	}
</script>
        <body>
       
       <button onClick="btnRegi()">새글쓰기</button>
       <hr>
<table>
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>	
	</thead>
	<tbody>
		
	</tbody>
</table>
        </body>

        </html>