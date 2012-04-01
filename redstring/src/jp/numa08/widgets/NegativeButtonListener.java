package jp.numa08.widgets;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class NegativeButtonListener implements OnClickListener {
	// private static final String TAG = NegativeButtonListener.class
	// .getSimpleName();

	@Override
	public void onClick(final DialogInterface dialog, final int which) {
		// TODO Auto-generated method stub
		dialog.dismiss();
	}
}
