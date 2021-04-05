package com.hyeonah.urlshorteningservice.repository;

import com.hyeonah.urlshorteningservice.model.Url;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UrlRepositoryTest {

    @Autowired
    private UrlRepository urlRepository;

    private Url url;

    @BeforeEach
    void setUp() {
        final String originalUrl = "https://en.wikipedia.org/wiki/URL_shortening";
        final String shorteningKey = "JZfOQNro";
        final int reqCount = 1;

        url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShorteningKey(shorteningKey);
        url.setReqCount(reqCount);
    }

    @Test
    @DisplayName("url 저장 테스트")
    void save() {
        final Url saveUrl = urlRepository.save(url);

        assertThat(saveUrl.getOriginalUrl()).isEqualTo(url.getOriginalUrl());
        assertThat(saveUrl.getReqCount()).isEqualTo(url.getReqCount());
    }

    @Test
    void findByOriginalUrl() {
        final Url findUrl = urlRepository.findByOriginalUrl("https://en.wikipedia.org/wiki/URL_shortening");

        assertThat(findUrl.getOriginalUrl()).isEqualTo(url.getOriginalUrl());
        assertThat(findUrl.getShorteningKey()).isEqualTo(url.getShorteningKey());
        assertThat(findUrl.getReqCount()).isEqualTo(url.getReqCount());
    }
}