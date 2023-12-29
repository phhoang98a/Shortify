package com.backend.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.server.config.Snowflake;
import com.backend.server.dto.UrlRequest;
import com.backend.server.model.UrlPair;
import com.backend.server.repository.RedisRepository;
import com.backend.server.repository.UrlRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlService {
  @Autowired
  private UrlRepository mongoRepository;

  @Autowired
  private RedisRepository redisRepository;

  public String generateHash(long id) {
    String BASE62_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    if (id == 0) {
      return "0";
    }
    StringBuilder base62Number = new StringBuilder();
    while (id > 0) {
      int remainder = (int) (id % 62);
      base62Number.insert(0, BASE62_CHARS.charAt(remainder));
      id /= 62;
    }
    return base62Number.toString();
  }

  public UrlPair generateShortUrl(UrlRequest request) {
    System.out.println(request);
    UrlPair pair = mongoRepository.findByLongURL(request.getLongUrl());
    if (pair == null) {
      Snowflake s = new Snowflake();
      long uniqueId = s.nextId();
      String hashValue = generateHash(uniqueId);
      UrlPair newPair = new UrlPair(uniqueId, request.getLongUrl(), hashValue);
      mongoRepository.save(newPair);
      return newPair;
    }
    return pair;
  }

  public String redirecting(String shortUrl){
    UrlPair pair = redisRepository.findByShortURL(shortUrl);
    if (pair == null){
      pair = mongoRepository.findByShortURL(shortUrl);
      redisRepository.save(pair);
    }
    String longUrl = pair.getLongURL();
    return longUrl;
  }
}
