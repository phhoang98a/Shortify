package com.backend.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.server.model.UrlPair;

public interface UrlRepository extends MongoRepository<UrlPair, String> {
  UrlPair findByLongURL(String longURL);
  UrlPair findByShortURL(String shortURL);
}
