package com.backend.server.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@RequiredArgsConstructor
public class UrlPair implements Serializable {
  public long id;
  public String longURL;
  public String shortURL;    
  public UrlPair(long id, String longURL, String shortURL) {
    this.id = id;
    this.longURL = longURL;
    this.shortURL = shortURL;
  }
}
