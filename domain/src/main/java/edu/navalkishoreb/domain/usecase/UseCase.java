package edu.navalkishoreb.domain.usecase;

/**
 * Created by NavalB on 22-08-2016.
 */

public interface UseCase<T> {

  interface Callback<T> {
    void onSuccess(T result);

    void onError(String errorMessage);
  }

  void execute(Callback<T> callback);
}
