package edu.navalkishoreb.tvguide.base;

import android.app.Activity;
import android.os.Bundle;
import edu.navalkishoreb.tvguide.base.BaseFragment;

/**
 * Created by NavalB on 23-08-2016.
 */

public abstract class BaseRetry extends BaseFragment {
  public interface Callback {
    void onRetry();
  }

  private String message;
  private Callback callback;

  @SuppressWarnings("deprecation") @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof Callback)) {
      throw new IllegalArgumentException(
          "Parent activity need to implement " + Callback.class.getCanonicalName());
    }
    callback = (Callback) activity;
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString("message", message);
  }

  @Override protected void onRestoreSavedInstance(Bundle savedInstanceState) {
    super.onRestoreSavedInstance(savedInstanceState);
    setMessage(savedInstanceState);
  }

  @Override protected void initializeData() {
    super.initializeData();
    setMessage(getArguments());
  }

  private void setMessage(Bundle bundle) {
    if (bundle == null) {
      throw new IllegalArgumentException("Retry fragment require message to be set in arguments");
    }
    message = bundle.getString("message");
  }

  String getMessage() {
    return message;
  }

  void retry() {
    callback.onRetry();
  }
}
