package edu.navalkishoreb.tvguide.listing;

import android.app.Activity;
import edu.navalkishoreb.tvguide.base.BaseFragment;

/**
 * Created by NavalB on 24-08-2016.
 */

abstract class BaseFetching extends BaseFragment {

  interface Callback {
    void performFetch();
  }

  private Callback callback;

  @SuppressWarnings("deprecation") @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof BaseFetching.Callback)) {
      throw new IllegalArgumentException(
          "Parent activity need to implement " + BaseFetching.Callback.class.getCanonicalName());
    }
    callback = (Callback) activity;
  }

  @Override public void onStart() {
    super.onStart();
    callback.performFetch();
  }
}
