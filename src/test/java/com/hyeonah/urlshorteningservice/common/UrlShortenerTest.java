package com.hyeonah.urlshorteningservice.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UrlShortenerTest {

    @Test
    @DisplayName("shorteningKey 생성 및 시퀀스 값 일치 테스트")
    void generateShorteningKey() {
        final long seq = 1550000L;

        final String shorteningKey = UrlShortener.generateShorteningKey(seq);
        final long seqByShorteningKey = UrlShortener.getSeqByShorteningKey(shorteningKey);

        assertThat(shorteningKey.length()).isLessThanOrEqualTo(8);
        assertThat(seqByShorteningKey).isEqualTo(seq);
    }

    @Test
    @DisplayName("shorteningKey 로 시퀀스 디코딩 및 값 일치 테스트")
    void getSeqByShorteningKey() {
        final String shorteningKey = "0frg9XcE";

        final long sequence = UrlShortener.getSeqByShorteningKey(shorteningKey);
        final String generateShorteningKey = UrlShortener.generateShorteningKey(sequence);

        assertThat(sequence).isNotZero();
        assertThat(shorteningKey.length()).isLessThanOrEqualTo(8);
        assertThat(shorteningKey).isEqualTo(generateShorteningKey);
    }
}