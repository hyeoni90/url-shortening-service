package com.hyeonah.urlshorteningservice.controller;

import com.hyeonah.urlshorteningservice.dto.UrlRequest;
import com.hyeonah.urlshorteningservice.dto.UrlResponse;
import com.hyeonah.urlshorteningservice.service.ShorteningUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ShorteningUrlController {
    private final ShorteningUrlService shorteningUrlService;

    @RequestMapping("/")
    public String home(final Model model) {
        model.addAttribute("urlRequest", new UrlRequest());
        return "home";
    }

    @PostMapping(value = "/url-shortener")
    public String createShorteningUrl(@ModelAttribute final UrlRequest urlRequest, final Model model) {
        final UrlResponse response = shorteningUrlService.createShorteningUrl(urlRequest.getUrl());
        model.addAttribute("response", response);
        return "success";
    }

    @GetMapping("/{shorteningKey}")
    public String redirectShortenedUrl(@PathVariable final String shorteningKey) {
        final UrlResponse response = shorteningUrlService.findByShorteningKey(shorteningKey);
        return "redirect:" + response.getOriginalUrl();
    }
}
