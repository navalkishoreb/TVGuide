package edu.navalkishoreb.tvguide.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import edu.navalkishoreb.tvguide.R;
import edu.navalkishoreb.tvguide.base.BaseActivity;

public class Detail extends BaseActivity {

  public static void launch(Context context) {
    Intent intent = new Intent(context, Detail.class);
    context.startActivity(intent);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
  }
}
