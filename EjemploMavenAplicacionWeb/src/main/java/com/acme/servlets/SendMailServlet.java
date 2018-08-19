/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hamfree
 */
public class SendMailServlet extends HttpServlet {

    private static final long serialVersionUID = 613427294607257113L;

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
        //TODO: No funciona y no se porque.
        final String SL = System.getProperty("line.separator");
        try {
            StringBuilder sb = new StringBuilder();
            String de = "singularidad.cuantica@gmail.com";
            String para = "juanfco.ruiz@gmail.com";
            String asunto = "Prueba envio correo desde Tomcat";
            String contenido = "Mensaje de prueba";

            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            Session session = (Session) envCtx.lookup("mail/Session");
            Properties p = session.getProperties();

            if (p != null) {
                String usuario = p.getProperty("mail.smtp.user");
                String password = p.getProperty("mail.smtp.password");
                String smtpserver = p.getProperty("mail.smtp.host");
                int puerto = Integer.parseInt(p.getProperty("mail.smtp.port"));

                sb.append("Configuracion de la sesion de correo:").append(SL);

                if (p != null) {
                    if (!p.isEmpty()) {
                        Enumeration keys = p.propertyNames();
                        while (keys.hasMoreElements()) {
                            String key = (String) keys.nextElement();
                            String value = p.getProperty(key);
                            sb.append(key).append("=").append(value).append(SL);
                        }
                        log(sb.toString());
                    } else {
                        log("El properties esta vacio.");
                    }
                } else {
                    log("La sesion no tiene properties.");
                }

                URLName urlname = new URLName("smtp", smtpserver, puerto, null, usuario, password);

                session.setPasswordAuthentication(urlname, new PasswordAuthentication(usuario, password));

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(de));
                InternetAddress to[] = new InternetAddress[1];
                to[0] = new InternetAddress(para);
                message.setRecipients(Message.RecipientType.TO, to);
                message.setSubject(asunto);
                message.setContent(contenido, "text/plain");
                Transport.send(message);
            } else {
                sb.append("La sesion no está configurada.");
            }

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet SendMailServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet SendMailServlet at " + request.getContextPath() + "</h1>");
                out.println("<p>" + sb.toString() + "</p>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (NamingException | MessagingException ex) {
            Logger.getLogger(SendMailServlet.class.getName()).log(Level.SEVERE, null, ex);
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

}
