<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo de Aplicacion Web controlada por Maven</title>
    </head>
    <body>
        <h1>Aplicacion Web para pruebas</h1>
        <ol>
            <li>
                <a href="<%=request.getContextPath()%>/MyBeanServlet">Servlet que usa MyBean via JNDI</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/MyBean2Servlet">Servlet que usa MyBean2 via JNDI</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/SendMailServlet">Servlet que envía un mensaj de correo usando JNDI</a>
            </li>
        </ol>

    </body>
</html>
