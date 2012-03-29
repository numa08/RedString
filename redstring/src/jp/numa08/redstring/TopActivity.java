package jp.numa08.redstring;

import jp.numa08.actionbarcompat.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class TopActivity extends ActionBarActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle sInstanceState) {
		super.onCreate(sInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// TODO Auto-generated method stub
		final MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	// @Override
	// public boolean onOptionsItemSelected(final MenuItem item) {
	// // TODO Auto-generated method stub
	// return super.onOptionsItemSelected(item);
	// }
}