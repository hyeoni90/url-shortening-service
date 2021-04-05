package com.hyeonah.urlshorteningservice.dto;

import com.hyeonah.urlshorteningservice.model.Url;

public class UrlResponse {

    private String originalUrl;
    private String shorteningKey;
    private String shortUrl;
    private int reqCount;

    private UrlResponse() {
    }

    private UrlResponse(final String originalUrl, final String shorteningKey, final int reqCount) {
        this.originalUrl = originalUrl;
        this.shorteningKey = shorteningKey;
        this.reqCount = reqCount;
    }

    public static UrlResponse of(final Url url) {
        return new UrlResponse(url.getOriginalUrl(), url.getShorteningKey(), url.getReqCount());
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShorteningKey() {
        return shorteningKey;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public long getReqCount() {
        return reqCount;
    }
}
