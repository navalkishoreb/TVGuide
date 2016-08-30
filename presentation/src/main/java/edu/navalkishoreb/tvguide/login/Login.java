package edu.navalkishoreb.tvguide.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import edu.navalkishoreb.tvguide.R;

public final class Login extends BaseLogin {

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.application_frame);
  }

  public static void launch(Context context) {
    Intent intent = new Intent(context, Login.class);
    context.startActivity(intent);
  }
}
