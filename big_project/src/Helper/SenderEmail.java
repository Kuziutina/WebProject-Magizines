package Helper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SenderEmail implements Runnable{
    private Properties props;
    private Session session;
    private Message message;
    private String text;
    private String to_email;
    public SenderEmail(String text, String to_email) {
        this.text = text;
        this.to_email = to_email;
    }


    @Override
    public void run() {
        props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("cuzyutinasofia@yandex.ru", "Cjabqrf23199808");
                    }
                });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("cuzyutinasofia@yandex.ru"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to_email));
            message.setSubject("Subject");
            message.setText(text);

            Transport.send(message);

//            message.setContent(text, "text/html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
