package edu.navalkishoreb.data.base;

import edu.navalkishoreb.data.network.NetworkAPI;
import edu.navalkishoreb.domain.TvRepository;
import edu.navalkishoreb.domain.model.TvShow;
import edu.navalkishoreb.domain.usecase.UseCase;
import java.util.ArrayList;

final class TvRepositoryImpl implements TvRepository {

  private NetworkAPI networkAPI;

  TvRepositoryImpl() {
    networkAPI = new NetworkManager();
  }

  @Override public void fetch(UseCase.Callback<Void> callback) {
    networkAPI.login(callback);
  }

  @Override public void fetchListing(UseCase.Callback<ArrayList<TvShow>> callback) {
    networkAPI.fetchListing(callback);
  }
}
