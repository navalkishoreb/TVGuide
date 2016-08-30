package edu.navalkishoreb.tvguide.listing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.navalkishoreb.tvguide.R;

/**
 * Created by NavalB on 26-08-2016.
 */

final class ListingAdapter extends BaseListingAdapter {
  private Callback callback;

  ListingAdapter(Callback callback) {
    super(callback);
    this.callback = callback;
  }

  @Override public BaseTvShowHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tv_show, parent, false);
    BaseTvShowHolder holder = new TvShowHolder(view);
    holder.setCallback(callback);
    return holder;
  }
}
