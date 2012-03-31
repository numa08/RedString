package jp.numa08.redstring.utils;

import jp.numa08.objects.DB_COLUMNS;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author numanuma08 データーベースの操作をするヘルパー
 */
public class DBHelper extends SQLiteOpenHelper {
	// private static final String TAG = DBHelper.class.getSimpleName();

	private static final transient String DB_NAME = "redstring";
	private static final transient int DB_VERSION = 1;

	private static final transient String CREATE_TABLE_SQL = "create table "
			+ DB_COLUMNS.REDSTRING + " (" + DB_COLUMNS.ID
			+ " integer primary key, " + DB_COLUMNS.YEAR + " integer, "
			+ DB_COLUMNS.MONTH + " integer, " + DB_COLUMNS.DATE + " integer, "
			+ DB_COLUMNS.NAME + " text, " + DB_COLUMNS.PLICE + " integer);";

	public DBHelper(final Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(final SQLiteDatabase dataBase) {
		dataBase.execSQL(CREATE_TABLE_SQL);
	}

	@Override
	public void onUpgrade(final SQLiteDatabase dataBase, final int oldVersion,
			final int newVersion) {
		// TODO Auto-generated method stub

	}
}
