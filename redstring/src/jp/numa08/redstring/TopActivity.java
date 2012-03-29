package jp.numa08.redstring;

import java.util.ArrayList;
import java.util.List;

import jp.numa08.actionbarcompat.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class TopActivity extends ActionBarActivity {
	private transient List<String> dateList = new ArrayList<String>();

	/** Called when the activity is first created. */
	/*
	 * {@inheritDoc} Activity�������̓����`�D
	 */
	@Override
	public void onCreate(final Bundle sInstanceState) {
		super.onCreate(sInstanceState);
		setContentView(R.layout.main);

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

	public void onDateChanged(int witch) {

	}
}