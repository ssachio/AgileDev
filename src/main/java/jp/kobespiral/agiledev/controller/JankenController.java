package jp.kobespiral.agiledev.controller;

import java.security.Principal;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.kobespiral.agiledev.model.JankenBattlers;

/**
 * JankenController
 */
@Controller
@RequestMapping("janken")
public class JankenController {
  Random rand = new Random();

  @Autowired
  private JankenBattlers jbattlers;

  /**
   *
   * @param hand
   * @param model
   * @param principal ログイン時のユーザ情報などを保持している
   * @return
   */
  @PostMapping("hand")
  public String janken(@RequestParam("hand") final String hand, ModelMap model, Principal principal) {
    String loginUser = principal.getName();
    this.jbattlers.addUserHand(loginUser, hand);

    // もし2人分の手が入力されていたら
    // じゃんけん実施
    // winner(ユーザ名を入れる)をmodelに追加
    if (this.jbattlers.countJankenHands() >= 2) {
      model.addAttribute("winner", this.getWinner(loginUser));
    }

    System.out.println("Janken Post-------------------------");
    model.addAttribute("username", principal.getName());
    model.addAttribute("userCount", this.jbattlers.countJankenUsers());
    model.addAttribute("enter", loginUser);
    model.addAttribute("playerHand", this.jbattlers.getPlayerHand(loginUser));
    model.addAttribute("enemyHand", this.jbattlers.getEnemyHand(loginUser));

    return "janken.html";
  }

  @GetMapping
  public String janken(ModelMap model, Principal principal) {
    this.exitRoom(model, principal);
    return "janken.html";
  }

  // playerの勝ちの場合はplayer nameを，enemyの勝ちの場合はenemy nameを返す
  private String getWinner(String player) {
    // playerの手とenemyの手をjudgeJankenにわたす
    String winner = this.judgeJanken(this.jbattlers.getPlayerHand(player), this.jbattlers.getEnemyHand(player));
    if (winner.equals("enemy")) {
      return this.jbattlers.getEnemyName(player);
    }
    if (winner.equals("player")) {
      return player;
    }
    return winner;
  }

  private String judgeJanken(String playerHand, String enemyHand) {
    if (playerHand.equals(enemyHand)) {
      return "Draw";
    } else if (playerHand.equals("gu") && enemyHand.equals("choki")
        || playerHand.equals("choki") && enemyHand.equals("pa") || playerHand.equals("pa") && enemyHand.equals("gu")) {
      return "player";
    }
    return "enemy";
  }

  @GetMapping("enter")
  public String enterRoom(ModelMap model, Principal principal) {
    String loginUser = principal.getName();
    model.addAttribute("username", loginUser);
    this.jbattlers.addJankenUser(loginUser);
    System.out.println("enter get-------------------------");
    model.addAttribute("userCount", this.jbattlers.countJankenUsers());
    model.addAttribute("enter", loginUser);
    return "janken.html";
  }

  @GetMapping("exit")
  public String exitRoom(ModelMap model, Principal principal) {
    String loginUser = principal.getName();
    model.addAttribute("username", loginUser);
    model.addAttribute("exit", loginUser);
    this.jbattlers.removeJankenUser(loginUser);
    System.out.println("exit get-------------------------");
    model.addAttribute("userCount", this.jbattlers.countJankenUsers());
    return "janken.html";
  }

}
