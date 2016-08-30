package edu.navalkishoreb.tvguide.base;

import android.content.Context;
import edu.navalkishoreb.tvguide.detail.Detail;
import edu.navalkishoreb.tvguide.listing.List;
import edu.navalkishoreb.tvguide.login.Login;

/**
 * Created by NavalB on 24-08-2016.
 */

public final class Navigator {

  public void navigateToLoginPage(Context context) {
    Login.launch(context);
  }

  public void navigateToFeedPage(Context context) {
    List.launch(context);
  }

  public void navigateToDetailPage(Context context) {
    Detail.launch(context);
  }
}
