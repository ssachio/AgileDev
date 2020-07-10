package jp.kobespiral.agiledev.controller;

import java.util.Random;

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
  Random rand = new Random();

  @PostMapping
  public String janken(@RequestParam("hand") final String hand, final ModelMap model) {
    model.addAttribute("playerHand", hand);
    String cpuHand = this.getCpuHand();
    model.addAttribute("cpuHand", cpuHand);
    model.addAttribute("winner", getWinner(hand, cpuHand));

    return "janken.html";
  }

  @GetMapping
  public String janken() {
    return "janken.html";
  }

  private String getWinner(String playerHand, String cpuHand) {
    if (playerHand.equals(cpuHand)) {
      return "Draw";
    } else if (playerHand.equals("gu") && cpuHand.equals("choki") || playerHand.equals("choki") && cpuHand.equals("pa")
        || playerHand.equals("pa") && cpuHand.equals("gu")) {
      return "Player";
    }
    return "CPU";
  }

  private String getCpuHand() {
    final int cpuHand = rand.nextInt(3);
    switch (cpuHand) {
      case 0:
        return "gu";
      case 1:
        return "choki";
      case 2:
        return "pa";
    }
    System.out.println(cpuHand);
    return "dummy";

  }

}
