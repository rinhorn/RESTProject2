<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.itbank.model.domain.Mountain"%>
<%@page import="java.util.List"%>

<%
	List<Mountain> mtList=(List)request.getAttribute("mtList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}
</style>
</head>
<body>

	<h2>산 목록</h2>
	<p></p>

	<table>
		<tr>
			<th>No</th>
			<th>이미지</th>
			<th>산 이름</th>
			<th>주소</th>
			<th>위도</th>
			<th>경도</th>
			<th>사용 마커</th>
		</tr>
		<%for(int i=0;i<mtList.size();i++){ %>
		<%Mountain mountain=mtList.get(i); %>
		<tr>
			<td></td>
			<td><img src="/data/<%=mountain.getFilename()%>" width="70px"></td>
			<td><%=mountain.getName() %></td>
			<td><%=mountain.getAddr() %></td>
			<td><%=mountain.getLati()%></td>
			<td><%=mountain.getLongi()%></td>
			<td><img src="/images/marker/<%=mountain.getMarker()%>" width="30px" height="30px"></td>
		</tr>
		<%} %>
	</table>

</body>
</html>
