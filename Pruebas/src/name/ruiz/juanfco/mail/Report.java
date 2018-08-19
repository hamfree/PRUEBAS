/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.mail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Report {

    public void sendMail() {

        //Setting up configurations for the email connection to the Google SMTP server using TLS
        Properties props = new Properties();

        props.put("mail.smtp.host", "true");

        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.host", "smtp.gmail.com");

        props.put("mail.smtp.port", "587");

        props.put("mail.smtp.auth", "true");

        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("singularidad.cuantica@gmail.com", "melotragotod0");

            }

        });

        try {

            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(session);

            //Storing the comma seperated values to email addresses
            String to = "juanfco.ruiz@gmail.com,juanfco.ruiz@outlook.es";

            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email

            addresses in an array of InternetAddress objects*/
            InternetAddress[] address = InternetAddress.parse(to, true);

            //Setting the recepients from the address variable
            msg.setRecipients(Message.RecipientType.TO, address);

            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());

            msg.setSubject("Correo de ejemplo : " + timeStamp);

            msg.setSentDate(new Date());

            msg.setText("Correo de ejemplo generado por el sistema");

            msg.setHeader("XPriority", "1");

            Transport.send(msg);

            System.out.println("El correo ha sido enviado correctamente");

        } catch (MessagingException mex) {

            System.out.println("Incapaz de enviar un correo. " + mex);

        }

    }
}
