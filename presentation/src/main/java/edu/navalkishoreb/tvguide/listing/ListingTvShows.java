package edu.navalkishoreb.tvguide.listing;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.navalkishoreb.domain.model.TvShow;
import edu.navalkishoreb.tvguide.R;
import edu.navalkishoreb.tvguide.base.BaseFragment;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public final class ListingTvShows extends BaseListingTvShows {

  public static BaseFragment newInstance(ArrayList<TvShow> list){
    BaseFragment fragment = new ListingTvShows();
    Bundle  bundle = new Bundle();
    bundle.putParcelableArrayList("list",list);
    fragment.setArguments(bundle);
    return fragment;
  }

  public ListingTvShows() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_listing_tv_shows, container, false);
  }

  @Override protected void findViews(View rootView) {
    RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.listing);
    GridLayoutManager layoutManager = new GridLayoutManager(getContext(), getResources().getInteger(R.integer.span_count));
    recyclerView.setLayoutManager(layoutManager);
    //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(getAdapter());
  }
}
