package edu.navalkishoreb.tvguide.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.navalkishoreb.tvguide.R;

/**
 * A simple {@link Fragment} subclass.
 */
public final class Retry extends BaseRetry implements View.OnClickListener {

  public static BaseFragment newInstance(String message) {
    Bundle bundle = new Bundle();
    bundle.putString("message", message);
    BaseFragment fragment = new Retry();
    fragment.setArguments(bundle);
    return fragment;
  }

  public Retry() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_retry, container, false);
  }

  @Override protected void findViews(View rootView) {
    TextView textView = (TextView) rootView.findViewById(R.id.txt_retry_message);
    textView.setText(getMessage());
    rootView.findViewById(R.id.btn_retry).setOnClickListener(this);
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_retry:
        retry();
        break;
      default:
        throw new IllegalArgumentException("onClick is not implemented for this view");
    }
  }
}
