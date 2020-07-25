package jp.kobespiral.agiledev.model;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Component;

@Component
public class JankenBattlers {
  ArrayList<JankenUser> jusers = new ArrayList<>();

  public ArrayList<JankenUser> getJankenUser() {
    return this.jusers;
  }

  public void addJankenUser(String name) {
    for (JankenUser j : this.jusers) {
      if (j.getName().equals(name)) {
        return;
      }
    }
    JankenUser ju = new JankenUser(name);
    this.jusers.add(ju);
  }

  public boolean exists(String name) {
    for (JankenUser j : this.jusers) {
      if (j.getName().equals(name)) {
        return true;
      }
    }
    return false;

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

  // 指定されたnameの手を取得する
  public String getPlayerHand(String name) {
    for (JankenUser j : this.jusers) {
      if (j.getName().equals(name)) {
        return j.getHand();
      }
    }
    return null;
  }

  // 指定されたnameじゃないほうの手を取得する
  public String getEnemyHand(String name) {
    for (JankenUser j : this.jusers) {
      if (j.getName().equals(name)) {
        continue;
      }
      return j.getHand();
    }
    return null;
  }

  // playerじゃないほうの名前を取得する
  public String getEnemyName(String name) {
    for (JankenUser j : this.jusers) {
      if (j.getName().equals(name)) {
        continue;
      }
      return j.getName();
    }
    return null;

  }

  /**
   * 設定されている手の数
   *
   * @return
   */
  public int countJankenHands() {
    int jankenHands = 0;
    Iterator<JankenUser> iterator = this.jusers.iterator();
    while (iterator.hasNext()) {
      JankenUser j = iterator.next();
      if (j.getHand() != null) {
        jankenHands++;
      }
    }
    return jankenHands;
  }

  // 対応するJankenUserオブジェクトに手を登録する
  public void addUserHand(String name, String hand) {
    JankenUser j = new JankenUser(name, hand);
    this.removeJankenUser(name);
    this.jusers.add(j);
  }
}
