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
import jp.numa08.widgets.GoodsListAdapter;
import jp.numa08.widgets.MonthSelectoListener;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author numanuma08 起動時に表示されるActivity
 */
/**
 * @author numanuma08
 * 
 */
/**
 * @author numanuma08
 * 
 */
public class TopActivity extends ActionBarActivity {
	private final transient int YESTERDAY = -1;
	private final transient SimpleDateFormat Date_Format = new SimpleDateFormat(
			"yyyy/MM/dd", Locale.JAPAN);
	private final transient SimpleDateFormat Month_Format = new SimpleDateFormat(
			"yyyy/MM", Locale.JAPAN);
	private transient ListView goodListView;
	private transient Button addButton;
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
		setListView();
		// 追加　ボタンの初期設定
		addButton = (Button) findViewById(R.id.add_button);
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

	/**
	 * ListViewの設定
	 */
	private void setListView() {
		goodListView = (ListView) findViewById(R.id.goods_list);
		// final Button addButton = new Button(this);
		// addButton.setText(getString(R.string.add_button));
		// goodListView.addFooterView(addButton);
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
					new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
			// TODO　月選択ダイアログが出る
			builder.show();
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
	public void onMonthSelected(int year, int month) {
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

	/**
	 * @deprecated 日付選択はなくなりました
	 * @param selector
	 */
	public void onDateSelected(final DateSelector selector) {
	}

	/**
	 * 追加ボタンクリック時の動作
	 */
	public void onAddButtonClicked() {
		if (selectedDate.equals(DateSelector.DATE)) {
			// TODO ダイアログ生成
			final LayoutInflater inflater = LayoutInflater.from(this);
			final View layout = inflater.inflate(R.layout.add_dialog,
					(ViewGroup) findViewById(R.id.add_dialog_root));
			final EditText nameText = (EditText) layout.findViewById(R.id.name);
			final EditText pliceText = (EditText) layout
					.findViewById(R.id.plice);
			final Builder builder = new AlertDialog.Builder(this);
			builder.setView(layout);
			builder.setTitle(R.string.add_button);
			builder.setPositiveButton(R.string.add_positive_button,
					new AddDialogListener(this, nameText, pliceText));
			builder.setNegativeButton(R.string.add_negative_button,
					new OnClickListener() {

						@Override
						public void onClick(final DialogInterface dialog,
								final int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
			// TODO ダイアログ表示
			builder.show();
		} else {
			Toast.makeText(getApplicationContext(),
					R.string.when_month_add_button, Toast.LENGTH_SHORT).show();
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
		final int sum = calcSum(goodsList);
		viewSum(sum);
	}
}