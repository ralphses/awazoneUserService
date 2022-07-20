package net.awazone.awazoneUserService.utils;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@AllArgsConstructor
public class UserEmailValidation {

    private final JavaMailSender javaMailSender;

    public boolean sendVerificationMail(String userEmail, String refererCode) throws MessagingException, UnsupportedEncodingException {
        String content = "Please click on the link below to verify your account<br>" +
                "<h3><a href=\"{[URL]}\"target=\"_blank\">Verify HERE</a></h3>";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);

        mimeMessageHelper.setFrom("no-reply");
        mimeMessageHelper.setTo(userEmail);
        mimeMessageHelper.setSubject("Verify Email");

        String verifySite = "http://localhost:8080/api/v1/user/verify?code="+refererCode;
        content = content.replace("{[URL]}", verifySite);

        mimeMessageHelper.setText(content, true);
        javaMailSender.send(message);

        return true;
    }


}
