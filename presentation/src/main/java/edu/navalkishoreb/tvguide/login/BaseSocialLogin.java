package edu.navalkishoreb.tvguide.login;

import android.app.Activity;
import android.view.View;
import edu.navalkishoreb.tvguide.R;
import edu.navalkishoreb.tvguide.base.BaseFragment;

/**
 * Created by NavalB on 22-08-2016.
 */

abstract class BaseSocialLogin extends BaseFragment implements View.OnClickListener {
  interface Media {
    void loginWithGooglePlus();
  }

   private Media media;

  @SuppressWarnings("deprecation") @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof Media)) {
      throw new IllegalArgumentException(
          "Parent activity need to implement " + Media.class.getCanonicalName());
    }
    media = (Media) activity;
}

  /**
   * whatever view is clicked on view
   * it's particular action is delegated to presenter
   * delegation should not involve any further if-else or switch case
   **/

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_social_login_google_plus:
        media.loginWithGooglePlus();
        break;
      default:
        throw new IllegalArgumentException("This view click is not implemented");
    }
  }

  @Override public void onDetach() {
    super.onDetach();
    media = null;
  }
}
