package jp.kobespiral.agiledev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import jp.kobespiral.agiledev.model.JankenBattlers;
import jp.kobespiral.agiledev.service.AsyncJanken;

@RestController
public class SseJankenController {

  @Autowired
  AsyncJanken asyncJanken;

  @GetMapping("janken2/async")
  public SseEmitter judgeJanken() {
    return null;
  }

}
