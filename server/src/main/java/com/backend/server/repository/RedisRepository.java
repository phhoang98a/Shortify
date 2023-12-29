package com.backend.server.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.backend.server.model.UrlPair;

@Repository
public class RedisRepository {
  private HashOperations<String, String, UrlPair> hashOperations;
  private RedisTemplate<String, Object> redisTemplate;

  public RedisRepository(RedisTemplate<String, Object> redisTemplate){
    this.redisTemplate = redisTemplate;
    this.hashOperations = this.redisTemplate.opsForHash();
  }

  public void save(UrlPair pair){
    hashOperations.put("PAIRS", pair.getShortURL(), pair);
  }

  public UrlPair findByShortURL(String shortUrl){
    return (UrlPair) hashOperations.get("PAIRS", shortUrl);
  }  
}
