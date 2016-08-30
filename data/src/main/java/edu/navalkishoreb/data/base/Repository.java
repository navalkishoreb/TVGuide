package edu.navalkishoreb.data.base;

import edu.navalkishoreb.domain.TvRepository;

/**
 * Created by NavalB on 22-08-2016.
 */

public abstract class Repository {
  public static TvRepository get() {
    return new TvRepositoryImpl();
  }
}
