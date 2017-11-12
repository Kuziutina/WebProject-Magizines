import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;


public class SendEmail {

    public static void main(String args[]) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("cuzyutinasofia@yandex.ru", "Cjabqrf23199808");
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("cuzyutinasofia@yandex.ru"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("cuzyutinasofia@yandex.ru"));
        message.setSubject("Subject");

        message.setContent("<a href='http://localhost:8080/about'>text</a>", "text/html");

        Transport.send(message);
    }

}
