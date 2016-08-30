package edu.navalkishoreb.tvguide.listing;

import edu.navalkishoreb.domain.model.TvShow;
import edu.navalkishoreb.tvguide.base.HeadlessFragment;
import java.util.ArrayList;

/**
 * Created by NavalB on 24-08-2016.
 */

interface FetchListingApi {
  interface Callback extends HeadlessFragment.Callback {
    void add(ArrayList<TvShow> list);

    void listExhausted();
  }

  void fetch();
}
