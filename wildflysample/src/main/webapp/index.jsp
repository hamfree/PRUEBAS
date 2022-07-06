<%-- 
    Document   : index
    Created on : 20 oct 2021, 21:24:57
    Author     : juanf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo aplicacion web en Wildfly</title>
        <%
            String ctx = request.getContextPath();
        %>
    </head>
    <body>
        <h1>Ejemplo aplicacion web en Wildfly</h1>
        <div style="text-align: center">
            <form accept-charset="UTF-8" method="POST" action="<%=ctx%>/hazconsulta">
                <input type="submit" value="Consulta"/>
            </form>
        </div>
    </body>
</html>
