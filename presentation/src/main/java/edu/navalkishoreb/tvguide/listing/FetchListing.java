package edu.navalkishoreb.tvguide.listing;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import edu.navalkishoreb.data.base.Repository;
import edu.navalkishoreb.domain.model.TvShow;
import edu.navalkishoreb.domain.usecase.UseCase;
import edu.navalkishoreb.tvguide.base.Debug;
import edu.navalkishoreb.tvguide.base.HeadlessFragment;
import java.util.ArrayList;

/**
 * Created by NavalB on 24-08-2016.
 */

public final class FetchListing extends HeadlessFragment<ArrayList<TvShow>>
    implements FetchListingApi {

  private FetchListingApi.Callback callback;
  private Handler uiHandler;
  private UseCase<ArrayList<TvShow>> useCase;

  public static HeadlessFragment newInstance() {
    return new FetchListing();
  }

  @SuppressWarnings("deprecation") @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof FetchListingApi.Callback)) {
      throw new IllegalArgumentException(
          "Parent activity need to implement " + FetchListingApi.Callback.class.getCanonicalName());
    }
    callback = (FetchListingApi.Callback) activity;
  }

  @Override protected void initializeData() {
    super.initializeData();
    Debug.d("List", "initializeData HeadLess");
    uiHandler = new Handler(Looper.getMainLooper());
    useCase = new edu.navalkishoreb.domain.usecase.FetchListing(Repository.get());
  }


  @Override public void fetch() {
    Debug.d("List", "fetching listing");
    if (isNotExecuting()) {
      Debug.d("List", "executing use case");
      useCase.execute(this);
      setExecuting(true);
    } else {
      Debug.d("List", "still fetching");
    }
  }

  // this callback is received in separate other than UIThread
  // so we have make appropriate decision
  // whatever result is received should directly be set on UIThread no
  // further processing should be done
  // we can change onSuccess
  @Override public void onSuccess(final ArrayList<TvShow> result) {
    super.onSuccess(result);
    Debug.d("List","result received HeadLess");
    uiHandler.post(new Runnable() {
      @Override public void run() {
        if (result.isEmpty()) {
          callback.listExhausted();
        } else {
          callback.add(result);
        }
      }
    });
  }

  @Override public void onError(final String errorMessage) {
    super.onError(errorMessage);
    Debug.d("List","error occurred HeadLess");
    uiHandler.post(new Runnable() {
      @Override public void run() {
        callback.onError(errorMessage);
      }
    });
  }

}
