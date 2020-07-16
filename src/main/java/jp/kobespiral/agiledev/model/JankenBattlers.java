package jp.kobespiral.agiledev.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class JankenBattlers {
  ArrayList<JankenUser> jusers = new ArrayList<>();

  public void addJankenUser(String name) {
    JankenUser ju = new JankenUser(name);
    this.jusers.add(ju);
  }

  public int countJankenUsers() {
    return this.jusers.size();
  }
}
