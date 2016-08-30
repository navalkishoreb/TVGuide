package edu.navalkishoreb.tvguide.listing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import edu.navalkishoreb.tvguide.R;

public final class List extends BaseList {

  public static void launch(Context context) {
    Intent intent = new Intent(context, List.class);
    context.startActivity(intent);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.application_frame);
  }
}
