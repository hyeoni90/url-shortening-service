package com.hyeonah.urlshorteningservice.service;

import com.hyeonah.urlshorteningservice.common.UrlShortener;
import com.hyeonah.urlshorteningservice.common.UrlValidator;
import com.hyeonah.urlshorteningservice.dto.UrlResponse;
import com.hyeonah.urlshorteningservice.model.Url;
import com.hyeonah.urlshorteningservice.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ShorteningUrlService {

    private final UrlRepository urlRepository;

    public UrlResponse createShorteningUrl(@NonNull final String originalUrl) {
        if (originalUrl.isEmpty() || !UrlValidator.validateUrl(originalUrl)) {
            throw new IllegalArgumentException("originUrl 을 확인해주세요.");
        }

        final Url findUrl = urlRepository.findByOriginalUrl(originalUrl);
        if (!Objects.isNull(findUrl)) {
            findUrl.addReqCount();
            return UrlResponse.of(findUrl);
        }

        final Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setReqCount(1);
        final Url savedUrl = urlRepository.save(url);

        savedUrl.setShorteningKey(UrlShortener.generateShorteningKey(savedUrl.getId()));
        return UrlResponse.of(savedUrl);
    }

    public UrlResponse findByShorteningKey(final String shorteningKey) {
        final long id = UrlShortener.getSeqByShorteningKey(shorteningKey);
        final Url url = urlRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 링크 입니다."));
        return UrlResponse.of(url);
    }
}
