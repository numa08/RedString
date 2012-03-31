package jp.numa08.redstring.test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.numa08.objects.Goods;
import jp.numa08.redstring.utils.DBHelper;
import jp.numa08.redstring.utils.RedstringDao;
import android.content.Context;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

public class RedstringDaoTest extends AndroidTestCase {
	// private static final String TAG = RedstringDaoTest.class.getSimpleName();
	private transient DBHelper helper;
	private transient RedstringDao dao;

	private transient Goods goods;

	private final static String NAME = "íãêH";
	private final static int PLICE = 500;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();

		final Context context = new RenamingDelegatingContext(getContext(),
				"test_db");
		helper = new DBHelper(context);
		dao = new RedstringDao(helper.getReadableDatabase());

		goods = new Goods(NAME, PLICE);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		helper.close();
	}

	public void testInsert() {
		final Date date = new Date();
		long id = dao.insert(date, goods);
		assertNotSame("id is not zero", id, 0);
	}

	public void testFindByDate() {
		final Date date = new Date();
		dao.insert(date, goods);

		final List<Goods> goodsList = dao.findByDate(date);
		final Goods goods = goodsList.get(0);

		assertEquals("name is " + NAME, NAME, goods.getName());
		assertEquals("plice is " + PLICE, PLICE, goods.getPlice());
	}

	public void testFindByMonth() {
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		dao.insert(date, goods);
		final List<Goods> goodsList = dao.findByMonth(
				calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
		final Goods goods = goodsList.get(0);

		assertEquals("name is " + NAME, goods.getName(), NAME);
		assertEquals("plice is " + PLICE, goods.getPlice(), PLICE);

	}
}
