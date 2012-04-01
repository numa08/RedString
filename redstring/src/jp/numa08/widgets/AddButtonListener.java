package jp.numa08.widgets;

import jp.numa08.redstring.TopActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class AddButtonListener implements OnClickListener {
	// private static final String TAG =
	// AddButtonListener.class.getSimpleName();
	private final transient TopActivity activity;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param activity
	 *            �Ăяo������Activity
	 */
	public AddButtonListener(final TopActivity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(final View view) {
		// TODO Auto-generated method stub
		activity.onAddButtonClicked();
	}
}
