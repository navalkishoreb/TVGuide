package edu.navalkishoreb.tvguide.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import edu.navalkishoreb.tvguide.base.BaseFragment;
import edu.navalkishoreb.tvguide.base.HeadlessFragment;

/**
 * Created by NavalB on 23-08-2016.
 */

public final class GoogleLoginHeadless extends HeadlessFragment
    implements GoogleApiClient.OnConnectionFailedListener, LoginApi,
    GoogleApiClient.ConnectionCallbacks {

  private static final int RC_SIGN_IN = 9123;

  interface Callback {
    void onSuccess(@NonNull GoogleSignInAccount account);

    void onError(String message);
  }

  public static BaseFragment newInstance() {
    return new GoogleLoginHeadless();
  }

  private Callback callback;
  private GoogleApiClient mGoogleApiClient;
  private GoogleSignInOptions gso;

  @SuppressWarnings("deprecation") @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof GoogleLoginHeadless.Callback)) {
      throw new IllegalArgumentException(
          "Parent activity need to implement " + GoogleLoginHeadless.Callback.class.getCanonicalName());
    }
    callback = (Callback) activity;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
        //.requestServerAuthCode("602947756636-n6p9169e1omi5n3br665k99mfd58618k.apps.googleusercontent.com")
        .build();
  }

  // since googleApiClient require activity object
  // googleApiClient should not be created in onCreate function for a headLess fragment
  @Override public void onStart() {
    super.onStart();
    mGoogleApiClient =
        new GoogleApiClient.Builder(this.getActivity()).enableAutoManage(getActivity(), 10, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .addConnectionCallbacks(this)
            .build();
  }

  @Override public void onStop() {
    super.onStop();
    stopGoogleClient();
  }

  @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    System.err.println("Google sign in connection failed");
    callback.onError(connectionResult.getErrorMessage());
  }

  @Override public void login() {
    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
    startActivityForResult(signInIntent, RC_SIGN_IN);
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == RC_SIGN_IN) {
      GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
      handleSignInResult(result);
    }
  }

  private void stopGoogleClient() {
    if (mGoogleApiClient.isConnected()) {
      mGoogleApiClient.stopAutoManage(getActivity());
      mGoogleApiClient.disconnect();
    }
  }

  private void handleSignInResult(GoogleSignInResult result) {
    stopGoogleClient();
    if (result.isSuccess()) {
      GoogleSignInAccount account = result.getSignInAccount();
      if (account != null) {
        System.out.println(account.getId());
        System.out.println(account.getPhotoUrl());
        System.out.println(account.getServerAuthCode());
        System.out.println(account.zzpg());
        System.out.println(account.zzph());
        callback.onSuccess(account);
      } else {
        System.err.println("google account is NULL");
        callback.onError("Google sign in account is NULL");
      }
    } else {
      String errorMessage="Unknown error";
      if(result.getStatus().getStatusCode() == 7){
        errorMessage = "Please check your network connection.";
      }
      callback.onError(errorMessage);
    }
  }

  @Override public void onConnected(@Nullable Bundle bundle) {
    System.out.println("google sign in connected");
  }

  @Override public void onConnectionSuspended(int i) {
    System.err.println("google sign in connection suspended " + i);
  }
}
