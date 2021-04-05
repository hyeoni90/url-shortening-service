package com.hyeonah.urlshorteningservice.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UrlValidatorTest {

    @Test
    void validateUrl() {
        final String url = "hyeon";
        final String url2 = "wiki/URL_shortening";
        final boolean isValidUrl1 = UrlValidator.validateUrl(url);
        final boolean isValidUrl2 = UrlValidator.validateUrl(url2);

        assertAll(
                () -> assertThat(isValidUrl1).isFalse(),
                () -> assertThat(isValidUrl2).isFalse()
        );
    }

    @Test
    void success_validateUrl() {
        final String url1 = "http://xxx:password@www.daum.net";
        final String url2 = "https://en.wikipedia.org/wiki/URL_shortening";

        final boolean isValidUrl1 = UrlValidator.validateUrl(url1);
        final boolean isValidUrl2 = UrlValidator.validateUrl(url2);

        assertAll(
                () -> assertThat(isValidUrl1).isTrue(),
                () -> assertThat(isValidUrl2).isTrue()
        );
    }
}