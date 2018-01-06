<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Ejemplo de aplicación Maven con uso de EJB's</title>
    </head>
    <body>
        <%
            String url = application.getContextPath();
        %>
        <h1>Ejemplo de aplicación Maven con uso de EJB's</h1>
        <ul>
            <li>
                <a href='<%=url%>/PostMessage'>Crear un nuevo mensaje</a>
            </li>
            <li>
                <a href='<%=url%>/ListNews'>Listar los mensajes</a>
            </li>
        </ul>
    </body>
</html>
