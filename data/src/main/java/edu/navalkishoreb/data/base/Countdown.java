package edu.navalkishoreb.data.base;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Future;

/**
 * Created by NavalB on 25-08-2016.
 */

final class Countdown{

  Countdown(final int seconds, final Future future) {
    if (seconds != 0) {
      final Timer timer = new Timer();
      timer.scheduleAtFixedRate(new TimerTask() {
        int i = seconds;

        public void run() {
          System.out.println(i--);
          if (i < 0) {
            timer.cancel();
            future.cancel(true);
          }
        }
      }, 0, 1000);
    }
  }
}