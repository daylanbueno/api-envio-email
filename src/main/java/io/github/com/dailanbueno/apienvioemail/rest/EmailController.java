package io.github.com.dailanbueno.apienvioemail.rest;


import io.github.com.dailanbueno.apienvioemail.dtos.EmailDto;
import io.github.com.dailanbueno.apienvioemail.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {

    private  final EmailService emailService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void enviarEmail(@RequestBody EmailDto emailDto) {
        emailService.enviaEmail(emailDto);
    }
}
