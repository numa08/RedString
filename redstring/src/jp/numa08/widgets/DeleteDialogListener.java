package jp.numa08.widgets;

import java.util.Date;

import jp.numa08.objects.Goods;
import jp.numa08.redstring.TopActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DeleteDialogListener implements OnClickListener {
	// private static final String TAG = DeleteDialogListener.class
	// .getSimpleName();
	private final transient TopActivity activity;
	private final transient Date date;
	private final transient Goods goods;

	public DeleteDialogListener(final TopActivity activity, final Date date,
			final Goods goods) {
		super();
		this.activity = activity;
		this.date = date;
		this.goods = goods;
	}

	@Override
	public void onClick(final DialogInterface dialog, final int which) {
		// TODO Auto-generated method stub
		activity.onDeleteDate(date, goods);
	}
}
