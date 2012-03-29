package jp.numa08.redstring;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/**
 * @author numanuma08 日付変更ダイアログのOKボタンのリスナークラス
 */
public class DateSelectListener implements OnClickListener {
	// private static final String TAG =
	// DateSelectListener.class.getSimpleName();
	private final transient TopActivity activity;

	/**
	 * コンストラクタ
	 * 
	 * @param activity
	 *            呼び出し元Activity
	 * 
	 */
	public DateSelectListener(final TopActivity activity) {
		this.activity = activity;
	}

	/*
	 * クリック時の動作 {@inheritDoc}
	 */
	@Override
	public void onClick(final DialogInterface dialog, final int which) {
		// TODO Auto-generated method stub
		activity.onDateChanged(which);
	}
}
