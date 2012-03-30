package jp.numa08.redstring.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import jp.numa08.redstring.TopActivity;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;

public class TopActivityTest extends
		ActivityInstrumentationTestCase2<TopActivity> {

	public TopActivityTest() {
		// TODO Auto-generated constructor stub
		super("jp.numa08.redstring", TopActivity.class);
	}

	@MediumTest
	public void testActionbarTestOnCreate() {
		final Activity activity = getActivity();
		final SimpleDateFormat format = new SimpleDateFormat("yyyy”NMMŒŽdd“ú",
				Locale.JAPAN);
		assertEquals("date is not equal", activity.getTitle(),
				format.format(new Date()));
	}
}
