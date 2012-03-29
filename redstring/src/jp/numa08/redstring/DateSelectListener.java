package jp.numa08.redstring;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/**
 * @author numanuma08 ���t�ύX�_�C�A���O��OK�{�^���̃��X�i�[�N���X
 */
public class DateSelectListener implements OnClickListener {
	// private static final String TAG =
	// DateSelectListener.class.getSimpleName();
	private final transient TopActivity activity;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param activity
	 *            �Ăяo����Activity
	 * 
	 */
	public DateSelectListener(final TopActivity activity) {
		this.activity = activity;
	}

	/*
	 * �N���b�N���̓��� {@inheritDoc}
	 */
	@Override
	public void onClick(final DialogInterface dialog, final int which) {
		// TODO Auto-generated method stub
		activity.onDateChanged(which);
	}
}
