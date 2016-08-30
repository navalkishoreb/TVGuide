package edu.navalkishoreb.tvguide.listing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import edu.navalkishoreb.domain.model.TvShow;
import edu.navalkishoreb.tvguide.R;
import edu.navalkishoreb.tvguide.base.BaseActivity;
import edu.navalkishoreb.tvguide.base.BaseFragment;
import edu.navalkishoreb.tvguide.base.Debug;
import edu.navalkishoreb.tvguide.base.Navigator;
import edu.navalkishoreb.tvguide.base.Retry;
import java.util.ArrayList;

/**
 * Created by NavalB on 24-08-2016.
 */

abstract class BaseList extends BaseActivity implements ListCallback {

  private int currentView;
  private FetchListingApi fetchListingApi;
  private FetchListingApi.Callback update;
  private Navigator navigator;

  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    setCurrentView(savedInstanceState.getInt(getString(R.string.key_current_view)));
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(getString(R.string.key_current_view), currentView);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setCurrentView(R.string.tag_FetchingView);
    fetchListingApi = getFetchTvShowApi();
    navigator = new Navigator();
  }

  @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    setFragment(currentView);
  }

  @Override public void onAttachFragment(Fragment fragment) {
    super.onAttachFragment(fragment);
    if (fragment instanceof FetchListingApi.Callback) {
      Debug.d("List", "attaching listing callback fragment");
      update = (FetchListingApi.Callback) fragment;
    }
  }

  @Override public void performFetch() {
    fetchListingApi.fetch();
  }

  private void setCurrentView(int fragmentTagId) {
    currentView = fragmentTagId;
  }

  private FetchListingApi getFetchTvShowApi() {
    Fragment fragment = addHeadlessFragment(R.string.tag_FetchTvShowsApi);
    if (fragment == null) {
      throw new IllegalArgumentException("FetchListingApi can not be NULL");
    }
    if (!(fragment instanceof FetchListingApi)) {
      throw new IllegalArgumentException(
          "Need to implement " + FetchListingApi.class.getCanonicalName());
    }
    return (FetchListingApi) fragment;
  }

  @Override public void add(ArrayList<TvShow> list) {
    Debug.d("List", "adding list view");
    if (update == null) {
      Debug.d("List", "creating view");
      update = attachListView(list);
    } else {
      Debug.d("List", "updating view");
      update.add(list);
    }
  }

  // list can not be exhausted without even
  // There has to be some logic/ relevance to
  // pass empty
  @Override public void listExhausted() {
    if (update != null) {
      Debug.d("List", "listExhausted");
      update.listExhausted();
    } else {
      onError("There is not data to show");
    }
  }

  @Override public void onError(String errorMessage) {
    Debug.e("List", errorMessage);
    if (update == null) {
      BaseFragment fragment = Retry.newInstance(errorMessage);
      setCurrentView(R.string.tag_Retry);
      setFragment(fragment, currentView);
    } else {
      update.onError(errorMessage);
    }
  }

  @Override public void loadMore(int index) {
    Debug.d("List", "load more");
    fetchListingApi.fetch();
  }

  @Override public void onItemClicked(TvShow tvShow) {
    Debug.d("List", "onItemClicked");
    navigateToNextPage();
  }

  private void navigateToNextPage() {
    overridePendingTransition(0, 0);
    navigator.navigateToDetailPage(this);
  }

  @Override public void onRetry() {
    Debug.d("List", "onRetry");
    setCurrentView(R.string.tag_FetchingView);
    setFragment(currentView);
  }

  private FetchListingApi.Callback attachListView(ArrayList<TvShow> list) {
    Fragment fragment = ListingTvShows.newInstance(list);
    if (!(fragment instanceof FetchListingApi.Callback)) {
      throw new IllegalArgumentException(
          "Fragment need to implement " + FetchListingApi.Callback.class.getCanonicalName());
    }
    setCurrentView(R.string.tag_LisitingTvShows);
    setFragment(fragment, R.string.tag_LisitingTvShows);
    return (FetchListingApi.Callback) fragment;
  }
}
