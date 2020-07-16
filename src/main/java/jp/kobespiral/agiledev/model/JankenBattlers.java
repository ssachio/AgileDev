package jp.kobespiral.agiledev.model;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Component;

@Component
public class JankenBattlers {
  ArrayList<JankenUser> jusers = new ArrayList<>();

  public void addJankenUser(String name) {
    for (JankenUser j : this.jusers) {
      if (j.getName().equals(name)) {
        return;
      }
    }
    JankenUser ju = new JankenUser(name);
    this.jusers.add(ju);
  }

  public void removeJankenUser(String name) {
    Iterator<JankenUser> iterator = this.jusers.iterator();
    while (iterator.hasNext()) {
      JankenUser j = iterator.next();
      if (j.getName().equals(name)) {
        iterator.remove();
      }
    }
  }

  public int countJankenUsers() {
    return this.jusers.size();
  }
}
