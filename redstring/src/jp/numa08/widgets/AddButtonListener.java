package jp.numa08.widgets;

import jp.numa08.redstring.TopActivity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author numanuma08 追加ボタンクリック時の動作
 * 
 */
public class AddButtonListener implements OnClickListener {
	// private static final String TAG =
	// AddButtonListener.class.getSimpleName();
	private final transient TopActivity activity;

	/**
	 * コンストラクタ
	 * 
	 * @param activity
	 *            呼び出し元Activity
	 * 
	 * 
	 */
	public AddButtonListener(final TopActivity activity) {
		this.activity = activity;
	}

	/*
	 * クリックされた　 {@inheritDoc}
	 */
	@Override
	public void onClick(final View view) {
		// TODO Auto-generated method stub
		activity.onAddButtonClicked();
	}
}
