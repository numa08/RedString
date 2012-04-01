package jp.numa08.redstring.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.numa08.objects.DB_COLUMNS;
import jp.numa08.objects.Goods;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author numanuma08 データベース操作クラス
 * 
 */
public class RedstringDao {
	// private static final String TAG = RedstringDao.class.getSimpleName();
	private final transient SQLiteDatabase database;

	public RedstringDao(final SQLiteDatabase database) {
		this.database = database;
	}

	/**
	 * 日付からデータを得る
	 * 
	 * @param date
	 *            日付
	 * @return 得られたデータ
	 */
	public List<Goods> findByDate(final Date date) {
		final List<Goods> goodsList = new ArrayList<Goods>();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		final String selection = DB_COLUMNS.YEAR + " = "
				+ calendar.get(Calendar.YEAR) + " AND " + DB_COLUMNS.MONTH
				+ " = " + calendar.get(Calendar.MONTH) + " AND "
				+ DB_COLUMNS.DATE + " = " + calendar.get(Calendar.DAY_OF_MONTH);
		final String[] columns = { DB_COLUMNS.NAME.name(),
				DB_COLUMNS.PLICE.name() };

		final Cursor cursor = database.query(DB_COLUMNS.REDSTRING.name(),
				columns, selection, null, null, null, null);

		while (cursor.moveToNext()) {
			final Goods goods = getGoodsData(cursor);
			goodsList.add(goods);
		}
		return goodsList;
	}

	/**
	 * カーソルからデータを得る
	 * 
	 * @param cursor
	 *            　カーソル
	 * @return 得られたデータ
	 */
	private Goods getGoodsData(final Cursor cursor) {
		final String name = cursor.getString(0);
		final int plice = cursor.getInt(1);
		return new Goods(name, plice);
	}

	/**
	 * 年、月からデータを得る
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 得られたデータ
	 */
	public List<Goods> findByMonth(final int year, final int month) {
		final List<Goods> goodsList = new ArrayList<Goods>();

		final String selection = DB_COLUMNS.YEAR + " = " + year + " AND "
				+ DB_COLUMNS.MONTH + " = " + month;
		final String[] columns = { DB_COLUMNS.NAME.name(),
				DB_COLUMNS.PLICE.name() };

		final Cursor cursor = database.query(DB_COLUMNS.REDSTRING.name(),
				columns, selection, null, null, null, null);

		while (cursor.moveToNext()) {
			final Goods goods = getGoodsData(cursor);
			goodsList.add(goods);
		}
		return goodsList;
	}

	/**
	 * データを保存する
	 * 
	 * @param date
	 *            日付
	 * @param goods
	 *            保存するデータ
	 * @return 保存したID
	 */
	public long insert(final Date date, final Goods goods) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		final ContentValues contentValue = new ContentValues();
		contentValue.put(DB_COLUMNS.YEAR.name(), calendar.get(Calendar.YEAR));
		contentValue.put(DB_COLUMNS.MONTH.name(), calendar.get(Calendar.MONTH));
		contentValue.put(DB_COLUMNS.DATE.name(),
				calendar.get(Calendar.DAY_OF_MONTH));
		contentValue.put(DB_COLUMNS.NAME.name(), goods.getName());
		contentValue.put(DB_COLUMNS.PLICE.name(), goods.getPlice());

		return database.insert(DB_COLUMNS.REDSTRING.name(), null, contentValue);
	}

	/**
	 * データを削除する
	 * 
	 * @param date
	 *            日付
	 * @param goods
	 *            データ
	 * @return 削除できたら1。できなかったら０
	 */
	public int delete(final Date date, final Goods goods) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		final String selection = DB_COLUMNS.YEAR + " = "
				+ calendar.get(Calendar.YEAR) + " AND " + DB_COLUMNS.MONTH
				+ " = " + calendar.get(Calendar.MONTH) + " AND "
				+ DB_COLUMNS.DATE + " = " + calendar.get(Calendar.DAY_OF_MONTH)
				+ " AND " + DB_COLUMNS.NAME + " = \"" + goods.getName()
				+ "\" AND " + DB_COLUMNS.PLICE + " = " + goods.getPlice();
		final String[] columns = { DB_COLUMNS.ID.name() };
		final Cursor cursor = database.query(DB_COLUMNS.REDSTRING.name(),
				columns, selection, null, null, null, null);
		int dataId = 0;
		while (cursor.moveToNext()) {
			dataId = cursor.getInt(0);
		}
		return database.delete(DB_COLUMNS.REDSTRING.toString(), DB_COLUMNS.ID
				+ " = " + dataId, null);
	}
}
