package edu.navalkishoreb.domain.usecase;

import edu.navalkishoreb.domain.TvRepository;
import edu.navalkishoreb.domain.model.TvShow;
import java.util.ArrayList;

/**
 * Created by NavalB on 25-08-2016.
 */

public final class FetchListing implements UseCase<ArrayList<TvShow>> {
  private final TvRepository tvRepository;

  public FetchListing(TvRepository tvRepository) {
    this.tvRepository = tvRepository;
  }

  @Override public void execute(Callback<ArrayList<TvShow>> callback) {
    tvRepository.fetchListing(callback);
  }
}
