package edu.navalkishoreb.tvguide.base;

import android.util.Log;
import edu.navalkishoreb.tvguide.BuildConfig;

/**
 * Created by NavalB on 25-08-2016.
 */

public final class Debug {
  public static void d(String tag, String message){
    if(BuildConfig.DEBUG) {
      Log.d(tag,message);
    }
  }
  public static void e(String tag, String message){
    if(BuildConfig.DEBUG) {
      Log.e(tag,message);
    }
  }
  public static void i(String tag, String message){
    if(BuildConfig.DEBUG) {
      Log.i(tag,message);
    }
  }

}
