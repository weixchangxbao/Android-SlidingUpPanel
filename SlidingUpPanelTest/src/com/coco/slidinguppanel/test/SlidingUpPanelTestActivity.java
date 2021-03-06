package com.coco.slidinguppanel.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.coco.slidinguppanel.SlidingUpPanel;
import com.coco.slidinguppanel.SlidingUpPanel.OnPanelCloseListener;
import com.coco.slidinguppanel.SlidingUpPanel.OnPanelOpenListener;
import com.coco.slidinguppanel.SlidingUpPanel.OnPanelScrollListener;

public class SlidingUpPanelTestActivity extends Activity {

	private static final String TAG = "SlidingUpPanelTestActivity";

	private SlidingUpPanel mSlidingUpPanel;
	private ImageView mCoverDown;
	private TextView mCoverHint;
	private Button mClickToClose;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding_up_panel_test);

		mSlidingUpPanel = (SlidingUpPanel) findViewById(R.id.sliding_up_panel);
		mCoverDown = (ImageView) findViewById(R.id.cover_down);
		mCoverHint = (TextView) findViewById(R.id.cover_hint);
		mClickToClose = (Button) findViewById(R.id.click_to_close);

		mSlidingUpPanel.setOnPanelOpenListener(new OnPanelOpenListener() {
			@Override
			public void onPanelOpened() {
				showToast("Sliding up panel opened!");
			}
		});
		mSlidingUpPanel.setOnPanelCloseListener(new OnPanelCloseListener() {
			@Override
			public void onPanelClosed() {
				showToast("Sliding up panel closed!");
			}
		});
		mSlidingUpPanel.setOnPanelScrolledListener(new OnPanelScrollListener() {
			@Override
			public void onPanelScrolled(float offset) {
				Log.d(TAG, "onPanelScrolled offset=" + offset);
				mCoverDown.setAlpha((int) ((1f - offset) * 255));
			}
		});

		mCoverDown.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showToast("Cover down pressed!");
			}
		});

		mClickToClose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mSlidingUpPanel.closePanel();
			}
		});
	}

	private void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

}
