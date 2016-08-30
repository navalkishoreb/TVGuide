package edu.navalkishoreb.data.base;

import edu.navalkishoreb.data.network.NetworkAPI;
import edu.navalkishoreb.domain.model.TvShow;
import edu.navalkishoreb.domain.usecase.UseCase;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by NavalB on 22-08-2016.
 */

 final class NetworkManager implements NetworkAPI {

  private static final int POOL_SIZE = 5;
  private final ExecutorService executor;

  NetworkManager() {
    executor = Executors.newFixedThreadPool(POOL_SIZE);
  }


  @Override public void login(UseCase.Callback<Void> callback) {
    System.out.println("submitting task");
    Future fetchShows = executor.submit(new LoginTask(callback));
    System.out.println("task submitted successfully");
    //new Countdown(5, fetchShows);
  }

  @Override public void fetchListing(UseCase.Callback<ArrayList<TvShow>> callback) {
    System.out.println("submitting task");
    Future fetchListing = executor.submit(new FetchListingTask(callback));
    System.out.println("task submitted successfully");
    //new Countdown(5, fetchListing);
  }
}
