<%-- 
    Document   : index
    Created on : 17 feb 2023, 18:21:34
    Author     : juanf
--%>

<%@page import="java.util.Date"%>
<%@page import="java.security.Principal"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.time.LocalDateTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  String headerName = null;
  String headerValue = null;
  LocalDateTime ldt = LocalDateTime.now();
  String servletName = pageContext.getServletConfig().getServletName();
  Enumeration<String> eAttrNames = application.getAttributeNames();
  Enumeration<String> eHeaderNames = request.getHeaderNames();
  Enumeration<String> eReqAttrNames = request.getAttributeNames();
  String authType = request.getAuthType();
  String contextPath = request.getContextPath();
  String contentType = request.getContentType();
  String characterEncoding = request.getCharacterEncoding();
  int contentLength = request.getContentLength();
  long contentLengthLong = request.getContentLengthLong();
  Cookie[] cookies = request.getCookies();
  String localAddr = request.getLocalAddr();
  String localName = request.getLocalName();
  int localPort = request.getLocalPort();
  String method = request.getMethod();
  String pathInfo = request.getPathInfo();
  String protocol = request.getProtocol();
  String queryString = request.getQueryString();
  String remoteAddr = request.getRemoteAddr();
  String remoteHost = request.getRemoteHost();
  String remoteUser = request.getRemoteUser();
  String requestURI = request.getRequestURI();
  String requestURL = request.getRequestURL().toString();
  String requestedSessionId = request.getRequestedSessionId();
  String scheme = request.getScheme();
  String serverName = request.getServerName();
  int serverPort = request.getServerPort();
  String servletPath = request.getServletPath();
  String idSes = session.getId();
  Date sesct = new Date(session.getCreationTime());
  Date sesat = new Date(session.getLastAccessedTime());
  int maxInactiveInterval = session.getMaxInactiveInterval();
  Enumeration<String> eSesAttrNames = session.getAttributeNames();
  Principal userPrincipal = request.getUserPrincipal();
  Map<String, String[]> mapParam = request.getParameterMap();

%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Datos de JSP</title>
  </head>
  <body>
    <h1>Datos de JSP</h1>
    <p>Hora actual en el servidor : <%=ldt%></p>
    <div>
      <ul>
        <li>ServletName = <%=servletName%></li>
        <li>authType = <%=authType%></li>
        <li>ContextPath = <%=request.getContextPath()%></li>
        <li>contentType = <%=contentType%></li>
        <li>characterEncoding = <%=characterEncoding%></li>
        <li>contentLength = <%=contentLength%></li>
        <li>contentLengthLong = <%=contentLengthLong%></li>
        <li>localAddr = <%=localAddr%></li>
        <li>localName = <%=localName%></li>
        <li>localPort = <%=localPort%></li>
        <li>method = <%=method%></li>
        <li>pathInfo = <%=pathInfo%></li>
        <li>protocol = <%=protocol%></li>
        <li>queryString = <%=queryString%></li>
        <li>remoteAddr = <%=remoteAddr%></li>
        <li>remoteHost = <%=remoteHost%></li>
        <li>remoteUser = <%=remoteUser%></li>
        <li>requestURI = <%=requestURI%></li>
        <li>requestURL = <%=requestURL%></li>
        <li>requestedSessionId = <%=requestedSessionId%></li>
        <li>scheme = <%=scheme%></li>
        <li>serverName = <%=serverName%></li>
        <li>serverPort = <%=serverPort%></li>
        <li>servletPath = <%=servletPath%></li>
        <li>Session Id  = <%=idSes%></li>
        <li>Session Creation Time = <%=sesct%></li>
        <li>Session Last Accesed Time = <%=sesat%></li>
        <li>Session maxInactiveInterval = <%=maxInactiveInterval%></li>
      </ul>
      
      <p>Atributos del contexto de la Petición (request):</p>
      <ul>
        <%
          if (eReqAttrNames != null) {
            while (eReqAttrNames.hasMoreElements()) {
              headerName = eReqAttrNames.nextElement();
              headerValue = (String) request.getAttribute(headerName);
        %>
        <li><%=headerName%> : <%=headerValue%></li>
          <%
            }
          } else {
          %>
        <li>No hay atributos.</li>
          <%
            }
          %>
      </ul>
      
      <p>Cabeceras HTTP de la petición:</p>
      <ul>
        <%
          while (eHeaderNames.hasMoreElements()) {
            headerName = eHeaderNames.nextElement();
            headerValue = request.getHeader(headerName);
        %>
        <li><%=headerName%> : <%=headerValue%></li>
          <%
            }
          %>
      </ul>

      <p>Atributos del contexto de la Aplicación (application):</p>
      <ul>
        <%
          if (eAttrNames != null) {
            while (eAttrNames.hasMoreElements()) {
              headerName = eAttrNames.nextElement();
              if (application.getAttribute(headerName) instanceof String) {
                headerValue = (String) application.getAttribute(headerName);
              } else {
                headerValue = application.getAttribute(headerName).toString();
              }
        %>
        <li><%=headerName%> : <%=headerValue%></li>
          <%
            }
          } else {
          %>
        <li>No hay atributos.</li>
          <%
            }
          %>
      </ul>
    </div>
  </body>
</html>
