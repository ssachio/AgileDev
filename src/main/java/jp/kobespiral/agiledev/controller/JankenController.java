package jp.kobespiral.agiledev.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import jp.kobespiral.agiledev.model.JankenBattlers;
import jp.kobespiral.agiledev.model.JankenUser;
import jp.kobespiral.agiledev.service.AsyncJanken;

/**
 * JankenController
 */
@Controller
@RequestMapping("janken")
public class JankenController {
  Random rand = new Random();

  @Autowired
  private JankenBattlers jbattlers;

  @Autowired
  AsyncJanken asyncJanken;

  @GetMapping("asyncJanken")
  public SseEmitter asyncJanken() {
    SseEmitter emitter = new SseEmitter();
    asyncJanken.asyncJanken(emitter, this.jbattlers);
    return emitter;
  }

  @RequestMapping("asyncHand")
  @ResponseBody // json呼び出し
  public ArrayList<JankenUser> asyncHand() {
    System.out.println("[[[[[janken]]]]]");

    // jbattlersそのものをreturnしてもパースしてくれないので，JavaのBeanのリストを返すようにする
    return this.jbattlers.getJankenUser();

  }

  /**
   *
   * @param hand
   * @param model
   * @param principal ログイン時のユーザ情報などを保持している
   *                  このメソッドはなくても良い．@Controllerの中でjsonを返すメソッドの実装方法として置いておく
   * @return
   */
  @PostMapping("hand")
  public String janken(@RequestParam("hand") final String hand, ModelMap model, Principal principal) {
    String loginUser = principal.getName();
    this.jbattlers.addUserHand(loginUser, hand);
    model.addAttribute("username", loginUser);
    model.addAttribute("userCount", this.jbattlers.countJankenUsers());
    model.addAttribute("enter", loginUser);
    return "janken.html";
  }

  @GetMapping
  public String janken(ModelMap model, Principal principal) {
    this.exitRoom(model, principal);
    return "janken.html";
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
