package com.hyeonah.urlshorteningservice.service;

import com.hyeonah.urlshorteningservice.dto.UrlResponse;
import com.hyeonah.urlshorteningservice.model.Url;
import com.hyeonah.urlshorteningservice.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ShorteningUrlServiceTest {

    private ShorteningUrlService shorteningUrlService;

    @MockBean
    private UrlRepository urlRepository;

    @BeforeEach
    void setUp() {
        this.shorteningUrlService = new ShorteningUrlService(urlRepository);
    }

    @DisplayName("유효하지 않은 originalUrl 입력할 경우 예외처리 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"URL_shortening  ", "xxx/cccc", "ccc.e4"})
    void shouldException_createShorteningUrl(final String originalUrl) {
        final Throwable e = assertThrows(IllegalArgumentException.class, () -> {

            final UrlResponse urlResponse = shorteningUrlService.createShorteningUrl(originalUrl);

            assertAll("shouldException_createShorteningUrl",
                    () -> assertNotNull(urlResponse)
            );
        });

        assertThat(e.getMessage()).isEqualTo("originUrl 을 확인해주세요.");
    }

    @DisplayName("동일한 originalUrl 요청 시 요청 수 카운트 증감 테스트")
    @Test
    void addCount_createShorteningUrl() {
        final Url url = getSuccessfulUrl();
        given(urlRepository.findByOriginalUrl(url.getOriginalUrl())).willReturn(url);

        final UrlResponse urlResponse = shorteningUrlService.createShorteningUrl(url.getOriginalUrl());

        assertAll("addCount_createShorteningUrl",
                () -> assertNotNull(urlResponse),
                () -> assertThat(urlResponse.getReqCount()).isEqualTo(2)
        );
    }

    @DisplayName("새로운 originalUrl 요청 시 테스트")
    @Test
    void createShorteningUrl() {
        final Url url = new Url();
        url.setId(2589654L);
        url.setOriginalUrl("https://store.musinsa.com/app/goods/1343223?loc=goods_rank");
        url.setReqCount(1);

        given(urlRepository.findByOriginalUrl(any())).willReturn(null);
        given(urlRepository.save(any())).willReturn(url);

        final UrlResponse urlResponse = shorteningUrlService.createShorteningUrl(url.getOriginalUrl());

        assertAll("createShorteningUrl",
                () -> assertNotNull(urlResponse),
                () -> assertThat(urlResponse.getOriginalUrl()).isEqualTo(url.getOriginalUrl()),
                () -> assertThat(urlResponse.getReqCount()).isEqualTo(url.getReqCount())
        );
    }

    @DisplayName("shorteningKey 로 Url 조회 없을 경우 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"  ", "xxxskeofjskefie", " ccc.e4"})
    void shouldException_findByShorteningKey(final String shorteningKey) {
        final Throwable e = assertThrows(IllegalArgumentException.class, () -> {

            final UrlResponse urlResponse = shorteningUrlService.findByShorteningKey(shorteningKey);

            assertAll("shouldException_findByShorteningKey",
                    () -> assertNotNull(urlResponse)
            );
        });

        assertThat(e.getMessage()).isEqualTo("잘못된 링크 입니다.");
    }

    @DisplayName("shorteningKey 로 Url 조회 테스트")
    @Test
    void findByShorteningKey() {
        final Url url = getSuccessfulUrl();
        given(urlRepository.findById(any())).willReturn(Optional.of(url));

        final UrlResponse urlResponse = shorteningUrlService.findByShorteningKey(url.getShorteningKey());

        assertThat(urlResponse.getOriginalUrl()).isEqualTo(url.getOriginalUrl());
        assertThat(urlResponse.getShorteningKey()).isEqualTo(url.getShorteningKey());
    }

    private Url getSuccessfulUrl() {
        final Url url = new Url();
        url.setId(143319143983939L);
        url.setOriginalUrl("https://en.wikipedia.org/wiki/URL_shortening");
        url.setShorteningKey("JZfOQNro");
        url.setReqCount(1);
        return url;
    }
}