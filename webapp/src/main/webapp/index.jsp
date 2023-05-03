<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<% 
String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aplicacion web de prueba</title>
</head>
<body>
<h1></h1>
<ul>
<li>ContextPath: <%=ctx%></li>
<li>ServerName: <%=request.getServerName() %></li>
<li><a href="<%=ctx%>/InfoServlet">Informacion sobre el Contenedor de Aplicaciones</a></li>
<li><a href="<%=ctx%>/InfoServlet?param1=1,2,3,4&param2='a','b','c'&param3=false">Informacion sobre el Contenedor de Aplicaciones (con parametros en el GET)</a></li>
</ul>
</body>
</html>