package io.github.com.dailanbueno.apienvioemail.service;

import io.github.com.dailanbueno.apienvioemail.dtos.EmailDto;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void enviaEmail(EmailDto emailDto) {
       try {
           MimeMessage message = javaMailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(message, true);

           helper.setFrom("noreply@gmail.com");
           helper.setSubject("Dev Bueno");
           helper.setTo(emailDto.email());

           String template  = carregaTemplateEmail();

           template = template.replace("#{nome}", emailDto.nome());
           helper.setText(template, true);
           javaMailSender.send(message);
       } catch (Exception exception) {
           System.out.println("Falha ao enviar o email");
       }
    }

    public String carregaTemplateEmail() throws IOException {
        ClassPathResource resource = new ClassPathResource("template-email.html");
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}
