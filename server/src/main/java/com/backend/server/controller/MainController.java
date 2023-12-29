package com.backend.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.backend.server.dto.UrlRequest;
import com.backend.server.model.UrlPair;
import com.backend.server.service.UrlService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class MainController {
  private final UrlService urlService;

  @PostMapping("url/shorten")
  @CrossOrigin(origins = "http://localhost:3000")
  @ResponseBody
  public ResponseEntity<UrlPair> generateShortUrl(@RequestBody UrlRequest request) {
    return ResponseEntity.ok(urlService.generateShortUrl(request));
  }

  @GetMapping("/{shortUrl}")
  public String getById(@PathVariable String shortUrl) {
    String longUrl = urlService.redirecting(shortUrl);
    return "redirect:"+longUrl;
  }
}
