/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.nom.juanfranciscoruiz.webapp.servlets;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author juanf
 */
@WebServlet(name = "InfoServlet", urlPatterns = {"/InfoServlet"})
public class InfoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InfoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InfoServlet at " + request.getContextPath() + "</h1>");
            ServletContext sctx = request.getServletContext();
            out.println("<h2>Atributos del contexto de servlets</h2>");
            Map<String, Object> mapa = getContextServletAttributesAsMap(sctx);
            out.println(Map2HtmlList(mapa));
            out.println("<h2>Atributos del request</h2>");
            mapa = getRequestAttributesAsMap(request);
            out.println(Map2HtmlList(mapa));
            out.println("<h2>Propiedades de la request actual</h2>");
            out.println(infoRequest(request));
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String Map2HtmlList(Map<String, Object> mapa) {
        String msg = null;
        StringBuilder sb = new StringBuilder();
        if (mapa != null && !mapa.isEmpty()) {
            sb.append("<ul>");
            for (String key : mapa.keySet()) {
                Object valor = mapa.get(key);
                sb = sb.append("<li>")
                        .append(key)
                        .append(" : ")
                        .append(String.valueOf(valor))
                        .append("</li>");
            }
            sb.append("</ul>");
            msg = sb.toString();
        } else {
            msg = "<p>No hay atributos</p>";
        }

        return msg;
    }

    private String infoRequest(HttpServletRequest req) {
        String msg = null;
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>")
                .append("<li>AuthType : ")
                .append(req.getAuthType())
                .append("</li>")
                .append("<li>CharacterEncoding : ")
                .append(req.getCharacterEncoding())
                .append("</li>")
                .append("<li>ContentLength : ")
                .append(req.getContentLength())
                .append("</li>")
                .append("<li>ContentType : ")
                .append(req.getContentType())
                .append("</li>")
                .append("<li> ContextPath : ")
                .append(req.getContextPath())
                .append("</li>")
                .append("<li>Cookies : ")
                .append(Arrays.toString(req.getCookies()))
                .append("</li>")
                .append("<li>Headers: ")
                .append(Map2HtmlList(getRequestHeadersAsMap(req)))
                .append("</li>")
                .append("<li>LocalAddr : ")
                .append(req.getLocalAddr())
                .append("</li>")
                .append("<li>LocalName : ")
                .append(req.getLocalName())
                .append("</li>")
                .append("<li>LocalPort: ")
                .append(req.getLocalPort())
                .append("</li>")
                .append("<li>Locale: ")
                .append(req.getLocale().toString())
                .append("</li>")
                .append("<li> : ")
                .append(req.getMethod())
                .append("</li>")
                .append("<li>Request Params : ")
                .append(Map2HtmlList(getReqParamsAsMap(req)))
                .append("</ul>");

        msg = sb.toString();
        return msg;
    }

    private Map<String, Object> getContextServletAttributesAsMap(ServletContext sctx) {
        Enumeration<String> attributeNames = sctx.getAttributeNames();
        Map<String, Object> mapa = new HashMap<>();

        if (attributeNames != null && attributeNames.hasMoreElements()) {
            while (attributeNames.hasMoreElements()) {
                String key = attributeNames.nextElement();
                Object value = sctx.getAttribute(key);
                mapa.put(key, value);
            }
        } else {
            return null;
        }

        return mapa;
    }

    private Map<String, Object> getRequestAttributesAsMap(HttpServletRequest req) {
        Enumeration<String> attributeNames = req.getAttributeNames();

        Map<String, Object> mapa = new HashMap<>();

        if (attributeNames != null && attributeNames.hasMoreElements()) {
            while (attributeNames.hasMoreElements()) {
                String key = attributeNames.nextElement();
                Object value = req.getAttribute(key);
                mapa.put(key, value);
            }
        } else {
            return null;
        }

        return mapa;
    }

    private Map<String, Object> getRequestHeadersAsMap(HttpServletRequest req) {
        Enumeration<String> headerNames = req.getHeaderNames();
        Map<String, Object> mapa = new HashMap<>();

        if (headerNames != null && headerNames.hasMoreElements()) {
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                Object value = req.getHeader(key);
                mapa.put(key, value);
            }
        } else {
            return null;
        }

        return mapa;
    }

    private Map<String, Object> getReqParamsAsMap(HttpServletRequest req) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Map<String, Object> mapa = new HashMap<>();
        if (parameterMap != null && !parameterMap.isEmpty()) {

            for (String key : parameterMap.keySet()) {
                String[] values = req.getParameterValues(key);
                Object value = Arrays.toString(values);
                mapa.put(key, value);
            }
        } else {
            return null;
        }
        return mapa;
    }

}
