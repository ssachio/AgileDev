package jp.kobespiral.agiledev.model;

public class JankenUser {
  private String name;
  private String hand;

  public JankenUser(String name) {
    this.name = name;
  }

  public JankenUser(String name, String hand) {
    this.name = name;
    this.hand = hand;
  }

  public String getName() {
    return this.name;
  }

  public String getHand() {
    return this.hand;
  }

}
