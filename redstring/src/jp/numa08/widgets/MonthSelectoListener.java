package jp.numa08.widgets;

import jp.numa08.redstring.TopActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.DatePicker;

/**
 * 月選択ダイアログで、月が選択された
 * 
 * @author numanuma08
 * 
 */
public class MonthSelectoListener implements OnClickListener {
	// private static final String TAG = MonthSelectoListener.class
	// .getSimpleName();
	private final transient TopActivity activity;
	private final transient DatePicker datePicker;

	public MonthSelectoListener(final TopActivity activity,
			final DatePicker datePicker) {
		super();
		this.activity = activity;
		this.datePicker = datePicker;
	}

	@Override
	public void onClick(final DialogInterface dialog, final int which) {
		// TODO Auto-generated method stub
		final int year = datePicker.getYear();
		final int month = datePicker.getMonth();
		activity.onMonthSelected(year, month);
	}
}
