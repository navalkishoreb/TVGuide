package edu.navalkishoreb.tvguide.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import edu.navalkishoreb.tvguide.R;
import edu.navalkishoreb.tvguide.base.BaseFragment;

/**
 *
 */
public final class SocialLogin extends BaseSocialLogin  {

  public static BaseFragment newInstance() {
    return new SocialLogin();
  }

  private Button button;

  public SocialLogin() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_social_login, container, false);
  }

  @Override protected void findViews(View rootView) {
    button = (Button) rootView.findViewById(R.id.btn_social_login_google_plus);
    button.setOnClickListener(this);
  }


  @Override public void onDestroyView() {
    super.onDestroyView();
    button.setOnClickListener(null);
    button = null;
  }
}
