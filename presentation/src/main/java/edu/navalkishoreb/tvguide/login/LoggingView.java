package edu.navalkishoreb.tvguide.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import edu.navalkishoreb.tvguide.R;
import edu.navalkishoreb.tvguide.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public final class LoggingView extends BaseLogging {


  public static BaseFragment newInstance(){
    return new LoggingView();
  }
  public LoggingView() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_logging_view, container, false);
  }

  @Override protected void findViews(View rootView) {
    //TextView textView = (TextView) rootView.findViewById(R.id.txt_loading_view);
    //textView.setText(R.string.login_loading_text);
  }
}
