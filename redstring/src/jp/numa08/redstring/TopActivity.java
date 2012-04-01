package jp.numa08.redstring;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jp.numa08.actionbarcompat.ActionBarActivity;
import jp.numa08.objects.Goods;
import jp.numa08.redstring.utils.DBHelper;
import jp.numa08.redstring.utils.RedstringDao;
import jp.numa08.widgets.AddButtonListener;
import jp.numa08.widgets.AddDialogListener;
import jp.numa08.widgets.DeleteDialogListener;
import jp.numa08.widgets.GoodsListAdapter;
import jp.numa08.widgets.MonthSelectoListener;
import jp.numa08.widgets.NegativeButtonListener;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author numanuma08 最初に表示されるActivity
 */
public class TopActivity extends ActionBarActivity {
	private final static transient int MAX_SIZE = 100;
	private final transient int YESTERDAY = -1;
	private final transient SimpleDateFormat Date_Format = new SimpleDateFormat(
			"yyyy/MM/dd", Locale.JAPAN);
	private final transient SimpleDateFormat Month_Format = new SimpleDateFormat(
			"yyyy/MM", Locale.JAPAN);
	private transient ListView goodListView;
	private transient DBHelper helper;
	private transient RedstringDao dao;
	/**
	 * 現在の日付表示パターン 初期値は　今日
	 */
	private transient DateSelector selectedDate = DateSelector.DATE;
	private transient Date viewDate;

	/** Called when the activity is first created. */
	/*
	 * {@inheritDoc} Activty起動時に実行
	 */
	@Override
	public void onCreate(final Bundle sInstanceState) {
		super.onCreate(sInstanceState);
		setContentView(R.layout.main);

		// ListViewの初期設定
		goodListView = (ListView) findViewById(R.id.goods_list);
		// 追加　ボタンの初期設定
		final Button addButton = (Button) findViewById(R.id.add_button);
		addButton.setOnClickListener(new AddButtonListener(this));
		// タイトルバーの表示
		viewDate = new Date();
		setTitle(viewDate);
		// TODO DB読み込み
		final List<Goods> goodsList = findByDate(viewDate);
		// TODO ListView表示
		goodListView.setAdapter(new GoodsListAdapter(getApplicationContext(),
				goodsList));
		// TODO 合計値計算
		final int sum = calcSum(goodsList);
		// TODO 合計値表示
		viewSum(sum);
		goodListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(final AdapterView<?> parent,
					final View view, final int position, final long viewId) {
				// TODO Auto-generated method stub
				if (selectedDate.equals(DateSelector.DATE)) {
					// TODO 選択されたアイテム取得
					final ListView listView = (ListView) parent;
					final Goods goods = (Goods) listView
							.getItemAtPosition(position);
					// TODO ダイアログ作成
					final StringBuilder messageBuilder = new StringBuilder();
					messageBuilder.append(goods.getName()).append(",")
							.append(goods.getPlice()).append("円")
							.append("を削除します。");
					final Builder builder = new Builder(TopActivity.this);
					builder.setTitle(R.string.delete_check);
					builder.setMessage(messageBuilder.toString());
					builder.setPositiveButton(R.string.delete_positive_button,
							new DeleteDialogListener(TopActivity.this,
									viewDate, goods));
					builder.setNegativeButton(R.string.add_negative_button,
							new NegativeButtonListener());
					// TODO 表示
					builder.show();
				} else {
					Toast.makeText(TopActivity.this,
							R.string.when_month_delete, Toast.LENGTH_SHORT)
							.show();
				}
				return false;
			}
		});
	}

	/**
	 * 日付をキーにデータベースから読み込む
	 * 
	 * @param date
	 *            日付
	 * @return 商品データ
	 */
	private List<Goods> findByDate(final Date date) {
		helper = new DBHelper(getApplicationContext());
		dao = new RedstringDao(helper.getReadableDatabase());
		final List<Goods> goodsList = dao.findByDate(date);
		helper.close();
		return goodsList;
	}

	/**
	 * タイトルバーの文字を設定する
	 * 
	 * @param date
	 *            表示されている日付
	 */
	public void setTitle(final Date date) {
		String title = null;
		if (selectedDate.equals(DateSelector.DATE)) {
			title = Date_Format.format(date);
		} else {
			title = Month_Format.format(date);
		}
		setTitle(title);
	}

	private void viewSum(final int sum) {
		final TextView sumText = (TextView) findViewById(R.id.sum_plice);
		sumText.setText(sum + "円");
	}

	/**
	 * 合計値を計算する
	 * 
	 * @param goodsList
	 *            商品のリスト
	 * @return 値段の合計
	 */
	private int calcSum(final List<Goods> goodsList) {
		int sum = 0;
		for (final Goods goods : goodsList) {
			sum += goods.getPlice();
		}
		return sum;
	}

	/*
	 * {@inheritDoc} オプションメニュー生成時の動作
	 */
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// TODO Auto-generated method stub
		final MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	/*
	 * {@inheritDoc} オプションメニュー選択時の動作
	 */
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.menu_back_yesterday) {
			if (selectedDate.equals(DateSelector.DATE)) {
				// TODO 昨日に戻るが選択された
				// TODO　表示時間変更
				final Calendar calendar = Calendar.getInstance();
				calendar.setTime(viewDate);
				calendar.add(Calendar.DAY_OF_MONTH, YESTERDAY);
				viewDate = calendar.getTime();
				// TODO タイトルバーの表示変更
				setTitle(viewDate);
				// TODO　DB読み込み
				final List<Goods> goodsList = findByDate(viewDate);
				// TODO　ListView表示
				goodListView.setAdapter(new GoodsListAdapter(
						getApplicationContext(), goodsList));
				// TODO　合計値計算
				final int sum = calcSum(goodsList);
				// TODO　表示
				viewSum(sum);
			} else {
				Toast.makeText(getApplicationContext(),
						R.string.when_mont_back_to_yesterday,
						Toast.LENGTH_SHORT).show();
			}
		} else if (item.getItemId() == R.id.menu_month_select) {
			// TODO 月を選ぶが選択された
			// ダイアログ生成
			final LayoutInflater inflater = LayoutInflater.from(this);
			final View layout = inflater.inflate(R.layout.month_select_dialog,
					(ViewGroup) findViewById(R.id.month_select_dialog));
			// ここから　DatePickerで日付部分がでないようにする小細工
			final int day_id = Resources.getSystem().getIdentifier("day", "id",
					"android");
			final DatePicker datePicker = (DatePicker) layout
					.findViewById(R.id.month_selector);
			datePicker.findViewById(day_id).setVisibility(View.GONE);
			// ここまで
			final Builder builder = new AlertDialog.Builder(this);
			builder.setView(layout);
			builder.setTitle(R.string.menu_month_select);
			builder.setPositiveButton(R.string.add_positive_button,
					new MonthSelectoListener(this, datePicker));
			builder.setNegativeButton(R.string.add_negative_button,
					new NegativeButtonListener());
			// TODO　月選択ダイアログが出る
			builder.show();
		} else if (item.getItemId() == android.R.id.home) {
			// TODO 日付を今日にする
			selectedDate = DateSelector.DATE;
			viewDate = new Date();
			// TODO タイトルバー更新
			setTitle(viewDate);
			// TODO　DB読み込み
			final List<Goods> goodsList = findByDate(viewDate);
			// ListView表示
			goodListView.setAdapter(new GoodsListAdapter(
					getApplicationContext(), goodsList));
			// 合計値計算
			final int sum = calcSum(goodsList);
			// 合計値表示
			viewSum(sum);
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 月が選択された
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 */
	public void onMonthSelected(final int year, final int month) {
		// TODO 表示月変更
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		viewDate = calendar.getTime();
		selectedDate = DateSelector.MONTH;
		// TODO タイトルバーの表示変更
		setTitle(viewDate);
		// TODO DB読み込み
		helper = new DBHelper(getApplicationContext());
		dao = new RedstringDao(helper.getReadableDatabase());
		final List<Goods> goodsList = dao.findByMonth(year, month);
		helper.close();
		// TODO LisView表示
		goodListView.setAdapter(new GoodsListAdapter(getApplicationContext(),
				goodsList));
		// TODO 合計値計算
		final int sum = calcSum(goodsList);
		// TODO 合計値表示
		viewSum(sum);
	}

	// /**
	// * @deprecated 日付選択はなくなりました
	// * @param selector
	// */
	// public void onDateSelected(final DateSelector selector) {
	// }

	/**
	 * 追加ボタンクリック時の動作
	 */
	public void onAddButtonClicked() {
		if (goodListView.getAdapter().getCount() <= MAX_SIZE) {
			if (selectedDate.equals(DateSelector.DATE)) {
				// TODO ダイアログ生成
				final LayoutInflater inflater = LayoutInflater.from(this);
				final View layout = inflater.inflate(R.layout.add_dialog,
						(ViewGroup) findViewById(R.id.add_dialog_root));
				final EditText nameText = (EditText) layout
						.findViewById(R.id.name);
				final EditText pliceText = (EditText) layout
						.findViewById(R.id.plice);
				final Builder builder = new AlertDialog.Builder(this);
				builder.setView(layout);
				builder.setTitle(R.string.add_button);
				builder.setPositiveButton(R.string.add_positive_button,
						new AddDialogListener(this, nameText, pliceText));
				builder.setNegativeButton(R.string.add_negative_button,
						new NegativeButtonListener());
				// TODO ダイアログ表示
				builder.show();
			} else {
				Toast.makeText(getApplicationContext(),
						R.string.when_month_add_button, Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			Toast.makeText(getApplicationContext(), R.string.over_max_size,
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * データ追加時の動作
	 */
	public void onAddGoods(final Goods goods) {
		// TODO DB読み込み
		helper = new DBHelper(this);
		dao = new RedstringDao(helper.getReadableDatabase());
		dao.insert(viewDate, goods);
		// TODO ListViewの表示
		final List<Goods> goodsList = dao.findByDate(viewDate);
		goodListView.setAdapter(new GoodsListAdapter(this, goodsList));
		helper.close();
		final int sum = calcSum(goodsList);
		viewSum(sum);
	}

	/**
	 * データ削除時の処理
	 * 
	 * @param date
	 *            日付
	 * @param goods
	 *            削除するデータ
	 */
	public void onDeleteDate(final Date date, final Goods goods) {
		// TODO　データ削除
		deleteDate(date, goods);
		helper = new DBHelper(this);
		dao = new RedstringDao(helper.getReadableDatabase());
		// TODO ListViewの表示
		final List<Goods> goodsList = dao.findByDate(viewDate);
		helper.close();
		goodListView.setAdapter(new GoodsListAdapter(this, goodsList));
		final int sum = calcSum(goodsList);
		viewSum(sum);
	}

	/**
	 * データを削除
	 * 
	 * @param date
	 *            日付
	 * @param goods
	 *            商品
	 */
	private void deleteDate(final Date date, final Goods goods) {
		helper = new DBHelper(getApplicationContext());
		dao = new RedstringDao(helper.getReadableDatabase());
		dao.delete(date, goods);
	}
}