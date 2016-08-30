package edu.navalkishoreb.tvguide.listing;

import android.support.v7.widget.RecyclerView;
import edu.navalkishoreb.domain.model.TvShow;
import java.util.ArrayList;

/**
 * Created by NavalB on 26-08-2016.
 */

abstract class BaseListingAdapter extends RecyclerView.Adapter<BaseTvShowHolder> {

  interface Callback extends BaseTvShowHolder.Callback {
    void loadMore(int index);
  }

  private ArrayList<TvShow> list;
  private boolean isExhausted;
  private BaseListingAdapter.Callback loadMore;

  BaseListingAdapter(Callback loadMore) {
    list = new ArrayList<TvShow>(0);
    this.loadMore = loadMore;
  }

  void addData(ArrayList<TvShow> list) {
    isExhausted = false;
    this.list.addAll(list);
  }

  @Override public int getItemCount() {
    return list.size();
  }

  ArrayList<TvShow> getData() {
    return list;
  }

  @Override public void onBindViewHolder(BaseTvShowHolder holder, int position) {
    if (isExhausted && position == list.size()) {
      loadMore.loadMore(position);
    }
    holder.setTvShow(list.get(position));
  }

  void listExhausted() {
    isExhausted = true;
  }
}
