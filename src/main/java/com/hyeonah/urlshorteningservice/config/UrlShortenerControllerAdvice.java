package com.hyeonah.urlshorteningservice.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UrlShortenerControllerAdvice {

    @ExceptionHandler
    public String errorHandler(final Exception exception, final Model model) {
        model.addAttribute("exception", exception);
        return "error";
    }
}
