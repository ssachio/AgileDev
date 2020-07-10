package jp.kobespiral.agiledev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * JankenController
 */
@Controller
public class JankenController {

  @RequestMapping("janken")
  public String janken() {
    return "janken.html";
  }

}
