package edu.navalkishoreb.tvguide.listing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.navalkishoreb.tvguide.R;
import edu.navalkishoreb.tvguide.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public final class FetchingView extends BaseFetching {

  public static BaseFragment newInstance() {
    return new FetchingView();
  }

  public FetchingView() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_fetching, container, false);
  }

  @Override protected void findViews(View rootView) {

  }
}
