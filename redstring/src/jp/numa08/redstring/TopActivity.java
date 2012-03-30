package jp.numa08.redstring;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import jp.numa08.actionbarcompat.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * @author numanuma08
 * 
 *         �N�����ɕ\������Activity
 */
public class TopActivity extends ActionBarActivity {
	private final transient SimpleDateFormat Date_Format = new SimpleDateFormat(
			"yyyy�NMM��dd��", Locale.JAPAN);
	private final transient SimpleDateFormat Month_Format = new SimpleDateFormat(
			"yyyy�NMM��", Locale.JAPAN);
	/**
	 * �I������Ă�����t�̕`��p�^�[���B�����l�́h�����h
	 */
	private transient DateSelector selectedDate = DateSelector.Today;

	/** Called when the activity is first created. */
	/*
	 * {@inheritDoc} Activity�������̓����`�D
	 */
	@Override
	public void onCreate(final Bundle sInstanceState) {
		super.onCreate(sInstanceState);
		setContentView(R.layout.main);

		if (selectedDate.equals(DateSelector.Today)) {
			final Date now = new Date();
			setTitle(Date_Format.format(now));
		}
	}

	/*
	 * {@inheritDoc} �I�v�V�������j���[�������̓���
	 */
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// TODO Auto-generated method stub
		final MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	/*
	 * {@inheritDoc} �I�v�V�������j���[�I�����̓���
	 */
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.menu_date_select) {
		}
		return super.onOptionsItemSelected(item);
	}

	public void onDateSelected(int witch) {

	}
}