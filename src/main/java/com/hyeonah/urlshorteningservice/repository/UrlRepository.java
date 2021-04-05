package com.hyeonah.urlshorteningservice.repository;

import com.hyeonah.urlshorteningservice.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Url findByOriginalUrl(String originalUrl);
}
