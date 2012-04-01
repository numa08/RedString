package jp.numa08.widgets;

import jp.numa08.redstring.TopActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/**
 * @author numanuma08 日付選択ダイアログ
 */
public class DateSelectListener implements OnClickListener {
	// private static final String TAG =
	// DateSelectListener.class.getSimpleName();
	private final transient TopActivity activity;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param activity
	 *            呼び出し元のActivity
	 * 
	 */
	public DateSelectListener(final TopActivity activity) {
		this.activity = activity;
	}

	/*
	 * OKボタンクリック時の動作
	 */
	@Override
	public void onClick(final DialogInterface dialog, final int which) {
	}
}
