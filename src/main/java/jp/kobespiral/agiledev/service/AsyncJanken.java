package jp.kobespiral.agiledev.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import jp.kobespiral.agiledev.model.JankenBattlers;

@Service
public class AsyncJanken {

  @Async
  public void asyncJanken(SseEmitter emitter, JankenBattlers jkbattle) {
    System.out.println("async janken start");
    try {
      while (true) {
        if (jkbattle.countJankenHands() < 2) {
          TimeUnit.SECONDS.sleep(1);
          continue;
        }
        // emitter.send("/janken/asyncHand");// 指定したAPIを呼び出させるメッセージ．
        // TODO Javaオブジェクトを投げて直接htmlで表示させたい

        emitter.send(jkbattle.getJankenUser());
        // SseEventBuilder eventBuilder = SseEmitter.event();
        // emitter.send(eventBuilder.data(jkbattle).name("jankenbattlers").id(String.valueOf(jkbattle.hashCode())));
        break;
      }
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    emitter.complete();
    System.out.println("async janken end");
  }

}
