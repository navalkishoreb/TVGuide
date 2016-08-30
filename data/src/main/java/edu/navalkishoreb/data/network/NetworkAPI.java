package edu.navalkishoreb.data.network;

import edu.navalkishoreb.domain.model.TvShow;
import edu.navalkishoreb.domain.usecase.UseCase;
import java.util.ArrayList;

/**
 * Created by NavalB on 22-08-2016.
 */

public interface NetworkAPI {

  void login(UseCase.Callback<Void> callback);

  void fetchListing(UseCase.Callback<ArrayList<TvShow>> callback);
}
