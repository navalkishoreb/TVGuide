package edu.navalkishoreb.tvguide.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import edu.navalkishoreb.tvguide.R;

/**
 * Created by NavalB on 22-08-2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

  private FragmentManager fragmentManager;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    fragmentManager = getSupportFragmentManager();
    FragmentManager.enableDebugLogging(true);
  }

  // this method is going to be frequently used by fragments who are not concerned with bundle data
  protected void setFragment(int fragmentTagId) {
    setFragment(fragmentTagId, null);
  }

  protected void setFragment(Fragment fragment, int fragmentTagId) {
    setFragment(fragment, getString(fragmentTagId));
  }

  private Fragment getFragmentFromFactory(int fragmentTagId) {
    Fragment fragment = getFragmentFromManager(fragmentTagId);
    if (fragment == null) {
      fragment = FragmentFactory.get(fragmentTagId);
    }
    return fragment;
  }

  private Fragment getFragmentFromManager(int fragmentTagId) {
    String fragmentTag = getString(fragmentTagId);
    return fragmentManager.findFragmentByTag(fragmentTag);
  }

  // set a fragment from factory with arguments
  private void setFragment(int fragmentTagId, Bundle bundle) {
    String fragmentTag = getString(fragmentTagId);
    Fragment fragment = getFragmentFromFactory(fragmentTagId);
    if (bundle != null) {
      fragment.setArguments(bundle);
    }
    setFragment(fragment, fragmentTag);
  }

  private void setFragment(Fragment fragment, String fragmentTag) {
    setFragment(R.id.fragment_container, fragment, fragmentTag);
  }

  private void setFragment(int container, Fragment fragment, String fragmentTag) {
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(container, fragment, fragmentTag);
    transaction.commitAllowingStateLoss();
  }

  protected Fragment addHeadlessFragment(int fragmentTagId) {
    Fragment fragment = getFragmentFromFactory(fragmentTagId);
    String fragmentTag = getString(fragmentTagId);
    if (!fragment.isAdded()) {
      return addHeadlessFragment(fragment, fragmentTag);
    } else {
      return fragment;
    }
  }

  private Fragment addHeadlessFragment(Fragment fragment, String fragmentTag) {
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.add(fragment, fragmentTag);
    transaction.commitAllowingStateLoss();
    return fragment;
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    fragmentManager = null;
  }
}
