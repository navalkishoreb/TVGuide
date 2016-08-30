package edu.navalkishoreb.tvguide.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import edu.navalkishoreb.domain.usecase.UseCase;

/**
 * Created by NavalB on 22-08-2016.
 */

public abstract class HeadlessFragment<T> extends BaseFragment implements UseCase.Callback<T> {
  public interface Callback {
    void onError(String errorMessage);
  }

  private boolean isExecuting;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  @Override protected void findViews(View rootView) {
    if (rootView != null) {
      throw new IllegalArgumentException("Headless fragment can not implement view");
    }
  }

  @Override public void onSuccess(T result) {
    setExecuting(false);
  }

  @Override public void onError(String errorMessage) {
    setExecuting(false);
  }

  protected boolean isExecuting() {
    return isExecuting;
  }

  protected boolean isNotExecuting() {
    return !isExecuting();
  }

  protected void setExecuting(boolean executing) {
    isExecuting = executing;
  }
}
