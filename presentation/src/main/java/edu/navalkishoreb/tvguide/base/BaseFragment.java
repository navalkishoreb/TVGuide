package edu.navalkishoreb.tvguide.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by NavalB on 22-08-2016.
 */

public abstract class BaseFragment extends Fragment {


  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (savedInstanceState != null) {
      onRestoreSavedInstance(savedInstanceState);
    } else {
      initializeData();
    }
  }

  @Override public void onStart() {
    super.onStart();
    findViews(getView());
  }

  protected abstract void findViews(View rootView);

  protected void onRestoreSavedInstance(Bundle savedInstanceState) {

  }

  protected void initializeData() {

  }
}
