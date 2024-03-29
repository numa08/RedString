package jp.numa08.widgets;

import jp.numa08.objects.Goods;
import jp.numa08.redstring.TopActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;

public class AddDialogListener implements OnClickListener {
	// private static final String TAG =
	// AddDialogListener.class.getSimpleName();
	private final transient TopActivity activity;
	private final transient EditText nameText;
	private final transient EditText pliceText;

	public AddDialogListener(final TopActivity activity,
			final EditText nameText, final EditText pliceText) {
		this.activity = activity;
		this.nameText = nameText;
		this.pliceText = pliceText;
	}

	@Override
	public void onClick(final DialogInterface dialog, final int which) {
		// TODO Auto-generated method stub
		final String name = nameText.getText().toString();
		final String pliceString = pliceText.getText().toString();
		if (name.length() > 0 && pliceString.length() > 0) {
			int plice;
			try {
				plice = Integer.parseInt(pliceString);
				final Goods goods = new Goods(name, plice);
				activity.onAddGoods(goods);
			} catch (Exception e) {
				// TODO: handle exception
				dialog.dismiss();
			}
		}
		dialog.dismiss();
	}
}
