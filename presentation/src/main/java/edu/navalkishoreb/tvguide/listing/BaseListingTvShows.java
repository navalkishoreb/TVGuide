package edu.navalkishoreb.tvguide.listing;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import edu.navalkishoreb.domain.model.TvShow;
import edu.navalkishoreb.tvguide.base.BaseFragment;
import java.util.ArrayList;

/**
 * Created by NavalB on 25-08-2016.
 */

abstract class BaseListingTvShows extends BaseFragment
    implements FetchListingApi.Callback, BaseListingAdapter.Callback {

  private BaseListingAdapter adapter;
  private BaseListingAdapter.Callback callback;

  @SuppressWarnings("deprecation") @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof BaseListingAdapter.Callback)) {
      throw new IllegalArgumentException("Parent activity need to implement "
          + BaseListingAdapter.Callback.class.getCanonicalName());
    }
    callback = (BaseListingAdapter.Callback) activity;
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelableArrayList("list", adapter.getData());
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    adapter = new ListingAdapter(BaseListingTvShows.this);
    if (getArguments() == null) {
      throw new IllegalArgumentException("bundle sent to fragment is NULL");
    }
    ArrayList<TvShow> list = getArguments().getParcelableArrayList("list");
    if (list == null) {
      throw new IllegalArgumentException("list sent to fragment is NULL. Check parcel key.");
    }
  }

  @Override protected void onRestoreSavedInstance(Bundle savedInstanceState) {
    super.onRestoreSavedInstance(savedInstanceState);
    adapter.addData(savedInstanceState.<TvShow>getParcelableArrayList("list"));
  }

  @Override protected void initializeData() {
    super.initializeData();
    if (getArguments() == null) {
      throw new IllegalArgumentException("bundle sent to fragment is NULL");
    }
    ArrayList<TvShow> list = getArguments().getParcelableArrayList("list");
    if (list == null) {
      throw new IllegalArgumentException("list sent to fragment is NULL. Check parcel key.");
    }
    adapter.addData(list);
  }

  @Override public void add(ArrayList<TvShow> list) {
    adapter.addData(list);
  }

  @Override public void listExhausted() {
    adapter.listExhausted();
  }

  @Override public void onError(String errorMessage) {

  }

  @Override public void loadMore(int position) {
    callback.loadMore(position);
  }

  @Override public void onItemClicked(TvShow tvShow) {
    callback.onItemClicked(tvShow);
  }

  BaseListingAdapter getAdapter() {
    return adapter;
  }
}
