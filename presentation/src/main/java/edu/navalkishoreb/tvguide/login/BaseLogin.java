package edu.navalkishoreb.tvguide.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.navalkishoreb.tvguide.R;
import edu.navalkishoreb.tvguide.base.BaseActivity;
import edu.navalkishoreb.tvguide.base.BaseFragment;
import edu.navalkishoreb.tvguide.base.Debug;
import edu.navalkishoreb.tvguide.base.Navigator;
import edu.navalkishoreb.tvguide.base.Retry;

/**
 * Created by NavalB on 22-08-2016.
 */

abstract class BaseLogin extends BaseActivity implements LoginCallback {

  /**
   * {@link #currentView} retains fragmentTagId
   * used to re-launch when device is rotated
   * to restore view state.
   **/
  private int currentView;
  /**
   * the reason to place {@link #loginApi} in BaseLogin
   * is to provide full control of navigation to baseLogin as presenter
   * it decides desired view to attach when success or error occur
   **/
  private LoginApi loginApi;

  private Navigator navigator;

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    System.out.println("onSaveInstanceState " + currentView);
    outState.putInt(getString(R.string.key_current_view), currentView);
  }

  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    currentView =
        savedInstanceState.getInt(getString(R.string.key_current_view), R.string.tag_SocialLogin);
    System.out.println("onRestoreInstanceState " + currentView);
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    loginApi = getLoginApi();
    navigator = new Navigator();
  }

  private LoginApi getLoginApi() {
    Fragment fragment = addHeadlessFragment(R.string.tag_LoginHeadlessApi);
    if (fragment == null) {
      throw new IllegalArgumentException("LoginApi fragment can not be NULL");
    }
    if (!(fragment instanceof LoginApi)) {
      throw new IllegalArgumentException(
          "LoginHeadlessFragment need to implement " + LoginApi.class.getCanonicalName());
    }
    return (LoginApi) fragment;
  }

  private LoginApi getGoogleLoginApi() {
    Fragment fragment = addHeadlessFragment(R.string.tag_GoogleLoginHeadless);
    if (fragment == null) {
      throw new IllegalArgumentException("LoginApi fragment can not be NULL");
    }
    if (!(fragment instanceof LoginApi)) {
      throw new IllegalArgumentException(
          "LoginHeadlessFragment need to implement " + LoginApi.class.getCanonicalName());
    }
    return (LoginApi) fragment;
  }

  @Override protected void onRestart() {
    super.onRestart();
    System.out.println("onRestart currentView " + currentView);
  }

  // because onRestoreSaveInstance is called between onStart and onPostCreate
  // by the time onPostCreate is called correct value of currentView will be restored
  @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    if (currentView == 0) {
      System.out.println("current View is zero");
      setCurrentView(R.string.tag_SocialLogin);
    }
    System.out.println("onPostCreate " + currentView);
    // if activity is restored then current fragment will be
    // with fragmentManager and definitely won't go to fragmentFactory
    setFragment(currentView);
  }

  private void setCurrentView(int fragmentTagId) {
    currentView = fragmentTagId;
    System.out.println("setting currentView " + currentView);
  }

  @Override public void loginWithGooglePlus() {
    Log.d("Login", "setting loading view");
    setCurrentView(R.string.tag_LoggingView);
    setFragment(currentView);
  }

  @Override public void performLogin() {
    Log.d("Login", "performing login");
    loginApi.login();
  }

  @Override public void onError(String errorMessage) {
    Log.d("Login", "onError " + errorMessage);
      BaseFragment fragment = Retry.newInstance(errorMessage);
      setCurrentView(R.string.tag_Retry);
      setFragment(fragment, currentView);
  }

  @Override public void onSuccess() {
    Log.d("Login", "onSuccess");
    navigateToNextPage();
  }

  @Override public void onRetry() {
    Log.d("Login", "onRetry");
    setCurrentView(R.string.tag_LoggingView);
    setFragment(currentView);
  }

  @Override public void onSuccess(@NonNull GoogleSignInAccount account) {
    Log.d("Login", "Google account " + account.getDisplayName());
    navigateToNextPage();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    currentView = 0;
    loginApi = null;
    navigator = null;
  }

  private void navigateToNextPage() {
    overridePendingTransition(0, 0);
    navigator.navigateToFeedPage(this);
    finish();
  }
}
