package edu.navalkishoreb.tvguide.base;

import edu.navalkishoreb.tvguide.R;
import edu.navalkishoreb.tvguide.listing.FetchListing;
import edu.navalkishoreb.tvguide.listing.FetchingView;
import edu.navalkishoreb.tvguide.login.GoogleLoginHeadless;
import edu.navalkishoreb.tvguide.login.LoggingView;
import edu.navalkishoreb.tvguide.login.LoginHeadlessApi;
import edu.navalkishoreb.tvguide.login.SocialLogin;

/**
 * Created by NavalB on 22-08-2016.
 */

abstract class FragmentFactory {

  static BaseFragment get(int fragmentTagId) {

    switch (fragmentTagId) {
      case R.string.tag_SocialLogin:
        return SocialLogin.newInstance();
      case R.string.tag_LoggingView:
        return LoggingView.newInstance();
      case R.string.tag_LoginHeadlessApi:
        return LoginHeadlessApi.newInstance();
      case R.string.tag_GoogleLoginHeadless:
        return GoogleLoginHeadless.newInstance();
      case R.string.tag_FetchingView:
        return FetchingView.newInstance();
      case R.string.tag_FetchTvShowsApi:
        return FetchListing.newInstance();
      default:
        throw new IllegalArgumentException("No such fragment is implemented");
    }
  }
}
