package edu.navalkishoreb.tvguide.login;

import android.app.Activity;
import edu.navalkishoreb.tvguide.base.BaseFragment;

/**
 * Created by NavalB on 23-08-2016.
 */

abstract class BaseLogging extends BaseFragment {

  interface Callback {
    void performLogin();
  }

  private Callback callback;

  @SuppressWarnings("deprecation") @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof Callback)) {
      throw new IllegalArgumentException(
          "Parent activity need to implement " + Callback.class.getCanonicalName());
    }
    callback = (Callback) activity;
  }

  @Override public void onResume() {
    super.onResume();
    callback.performLogin();
  }
}
