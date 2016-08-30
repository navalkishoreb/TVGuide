package edu.navalkishoreb.domain.usecase;

import edu.navalkishoreb.domain.TvRepository;

/**
 * Created by NavalB on 22-08-2016.
 */

public final class FetchTvShows implements UseCase<Void> {

  private final TvRepository tvRepository;

  public FetchTvShows(TvRepository tvRepository) {
    this.tvRepository = tvRepository;
  }

  @Override public void execute(Callback callback) {
    tvRepository.fetch(callback);
  }
}
