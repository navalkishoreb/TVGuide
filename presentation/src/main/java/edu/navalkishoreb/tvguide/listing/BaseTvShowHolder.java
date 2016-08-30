package edu.navalkishoreb.tvguide.listing;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import edu.navalkishoreb.domain.model.TvShow;
import edu.navalkishoreb.tvguide.R;

/**
 * Created by NavalB on 26-08-2016.
 */

abstract class BaseTvShowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  private TvShow tvShow;
  private ImageView imageView;
  private TextView titleTextView;
  private Callback callback;

  interface Callback {
    void onItemClicked(TvShow tvShow);
  }

  BaseTvShowHolder(View itemView) {
    super(itemView);
    itemView.setOnClickListener(this);
    imageView = (ImageView) itemView.findViewById(R.id.item_image_view);
    titleTextView = (TextView) itemView.findViewById(R.id.item_title_text);
    checkNotNUll();
  }

  void setCallback(Callback callback) {
    this.callback = callback;
  }

  private void checkNotNUll() {
    if (imageView == null) {
      throw new IllegalArgumentException(
          "Item view does not have image view with id R.id.item_image_view ");
    }
    if (titleTextView == null) {
      throw new IllegalArgumentException(
          "Item view does not have text view with id R.id.item_title_text ");
    }
  }

  void setTvShow(TvShow tvShow) {
    this.tvShow = tvShow;
    this.titleTextView.setText(tvShow.getTitle());
    Picasso.with(imageView.getContext())
        .load(tvShow.getImageUrl())
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.error)
        .into(imageView);
  }

  @Override public void onClick(View view) {
    if (callback != null) {
      callback.onItemClicked(tvShow);
    }
  }
}
