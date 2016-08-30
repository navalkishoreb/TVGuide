package edu.navalkishoreb.tvguide.login;

import edu.navalkishoreb.tvguide.base.HeadlessFragment;

/**
 * Created by NavalB on 22-08-2016.
 */

interface LoginApi {

  interface Callback extends HeadlessFragment.Callback ,GoogleLoginHeadless.Callback, LoginHeadlessApi.Callback {
  }

  void login();
}
