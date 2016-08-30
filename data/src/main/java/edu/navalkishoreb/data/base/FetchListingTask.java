package edu.navalkishoreb.data.base;

import edu.navalkishoreb.domain.model.TvShow;
import edu.navalkishoreb.domain.usecase.UseCase;
import java.util.ArrayList;

/**
 * Created by NavalB on 25-08-2016.
 */

final class FetchListingTask implements Runnable {
  private final UseCase.Callback<ArrayList<TvShow>> callback;

  FetchListingTask(UseCase.Callback<ArrayList<TvShow>> callback) {
    this.callback = callback;
  }

  @Override public void run() {
    try {
      System.out.println("executing thread");
      Thread.sleep(5000);
      System.out.println("finished thread");
      //callback.onError(getMockError());
      callback.onSuccess(getMockList());
    } catch (InterruptedException e) {
      System.out.println("interrupted thread");
      e.printStackTrace();
      callback.onError(getMockError());
    }
  }

  private ArrayList<TvShow> getMockList() {
    ArrayList<TvShow> list = new ArrayList<TvShow>();
    {
      TvShow tvShow = new TvShow();
      tvShow.setDescription(
          "Based on the Pretty Little Liars series of young adult novels by Sara Shepard, the series follows the lives of four girls — Spencer, Hanna, Aria, and Emily — whose clique falls apart after the disappearance of their queen bee, Alison. One year later, they begin receiving messages from someone using the name \"A\" who threatens to expose their secrets — including long-hidden ones they thought only Alison knew.");
      tvShow.setId(31917);
      tvShow.setImageUrl("http://image.tmdb.org/t/p/w342/vC324sdfcS313vh9QXwijLIHPJp.jpg");
      tvShow.setRating(5.04F);
      tvShow.setTitle("Pretty Little Liars");
      list.add(tvShow);
    }
    {
      TvShow tvShow = new TvShow();
      tvShow.setDescription(
          "Based on the Pretty Little Liars series of young adult novels by Sara Shepard, the series follows the lives of four girls — Spencer, Hanna, Aria, and Emily — whose clique falls apart after the disappearance of their queen bee, Alison. One year later, they begin receiving messages from someone using the name \"A\" who threatens to expose their secrets — including long-hidden ones they thought only Alison knew.");
      tvShow.setId(31917);
      tvShow.setImageUrl("http://image.tmdb.org/t/p/w342/esN3gWb1P091xExLddD2nh4zmi3.jpg");
      tvShow.setRating(5.04F);
      tvShow.setTitle("Suits");
      list.add(tvShow);
    }

    {
      TvShow tvShow = new TvShow();
      tvShow.setDescription(
          "Based on the Pretty Little Liars series of young adult novels by Sara Shepard, the series follows the lives of four girls — Spencer, Hanna, Aria, and Emily — whose clique falls apart after the disappearance of their queen bee, Alison. One year later, they begin receiving messages from someone using the name \"A\" who threatens to expose their secrets — including long-hidden ones they thought only Alison knew.");
      tvShow.setId(31917);
      tvShow.setImageUrl("http://image.tmdb.org/t/p/w342/i6Iu6pTzfL6iRWhXuYkNs8cPdJF.jpg");
      tvShow.setRating(5.04F);
      tvShow.setTitle("Mr. Robot");
      list.add(tvShow);
    }
    return list;
  }

  private String getMockError() {
    return "There is some error";
  }
}
