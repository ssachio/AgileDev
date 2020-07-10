package jp.kobespiral.agiledev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * JankenController
 */
@Controller
@RequestMapping("janken")
public class JankenController {

  @PostMapping
  public String janken(@RequestParam("hand") String hand, ModelMap model) {
    model.addAttribute("cpuHand", hand);
    return "janken.html";
  }

  @GetMapping
  public String janken() {
    return "janken.html";
  }

}
