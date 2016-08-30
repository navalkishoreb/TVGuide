package edu.navalkishoreb.tvguide.login;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import edu.navalkishoreb.data.base.Repository;
import edu.navalkishoreb.domain.usecase.FetchTvShows;
import edu.navalkishoreb.domain.usecase.UseCase;
import edu.navalkishoreb.tvguide.base.HeadlessFragment;

/**
 * Created by NavalB on 22-08-2016.
 */

public final class LoginHeadlessApi extends HeadlessFragment<Void> implements LoginApi {

  public static HeadlessFragment newInstance() {
    return new LoginHeadlessApi();
  }
  interface Callback extends HeadlessFragment.Callback{
    void onSuccess();
  }

  private UseCase<Void> useCase;
  private LoginApi.Callback callback;
  private Handler uiHandler;

  @SuppressWarnings("deprecation") @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof LoginApi.Callback)) {
      throw new IllegalArgumentException(
          "Parent activity need to implement " + LoginApi.Callback.class.getCanonicalName());
    }

    Log.d("Login", "onAttach Headless");

    callback = (LoginApi.Callback) activity;
    uiHandler = new Handler(Looper.getMainLooper());
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    useCase = new FetchTvShows(Repository.get());
  }

  @Override public void login() {
    Log.d("Login", "execute use case");
    if (!isExecuting()) {
      Log.d("Login", "start execution");
      useCase.execute(this);
      setExecuting(true);
    } else {
      Log.d("Login", "still executing");
    }
  }

  @Override public void onSuccess(Void result) {
    super.onSuccess(result);
    Log.d("Login", "onSuccess Headless");
    uiHandler.post(new Runnable() {
      @Override public void run() {
        callback.onSuccess();
      }
    });
  }

  @Override public void onError(String errorMessage) {
    super.onError(errorMessage);
    Log.d("Login", "onError Headless");
    uiHandler.post(new Runnable() {
      @Override public void run() {
        callback.onError("Login Api error");
      }
    });
  }

  @Override public void onDetach() {
    super.onDetach();
    Log.d("Login", "onDetach Headless");
    callback = null;
    uiHandler = null;
  }
}
