package edu.navalkishoreb.data.base;

import edu.navalkishoreb.domain.usecase.UseCase;

/**
 * Created by NavalB on 25-08-2016.
 */

final class LoginTask implements Runnable {

  private final UseCase.Callback<Void> callback;

  LoginTask(UseCase.Callback<Void> callback) {
    this.callback = callback;
  }

  @Override public void run() {
    try {
      System.out.println("executing thread");
      Thread.sleep(1000);
      System.out.println("finished thread");
      callback.onSuccess(null);
    } catch (InterruptedException e) {
      System.out.println("interrupted thread");
      e.printStackTrace();
      callback.onError(e.getMessage());
    }
  }
}
