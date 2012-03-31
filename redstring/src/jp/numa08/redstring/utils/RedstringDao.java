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
 * @author numanuma08 �f�[�^�[�x�[�X�𑀍삷��N���X
 * 
 */
public class RedstringDao {
	// private static final String TAG = RedstringDao.class.getSimpleName();
	private final transient SQLiteDatabase database;

	public RedstringDao(final SQLiteDatabase database) {
		this.database = database;
	}

	/**
	 * ���t����f�[�^�𓾂�
	 * 
	 * @param date
	 *            ���t
	 * @return ���i�ƒl�i�̃��X�g
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

	private Goods getGoodsData(final Cursor cursor) {
		final String name = cursor.getString(0);
		final int plice = cursor.getInt(1);
		return new Goods(name, plice);
	}

	/**
	 * ������f�[�^�𓾂�
	 * 
	 * @param year
	 *            �N
	 * @param month
	 *            ��
	 * @return �f�[�^�̃��X�g
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
	 * �f�[�^�x�[�X�ɕۑ�����
	 * 
	 * @param date
	 *            ���t
	 * @param goods
	 *            ���i�̃f�[�^
	 * @return �f�[�^ID
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
}
