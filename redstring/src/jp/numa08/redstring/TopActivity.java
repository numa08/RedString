package jp.numa08.redstring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jp.numa08.actionbarcompat.ActionBarActivity;
import jp.numa08.objects.Goods;
import jp.numa08.widgets.GoodsListAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author numanuma08
 * 
 *         起動時に表示するActivity
 */
public class TopActivity extends ActionBarActivity {
	private final transient SimpleDateFormat Date_Format = new SimpleDateFormat(
			"yyyy年MM月dd日", Locale.JAPAN);
	private final transient SimpleDateFormat Month_Format = new SimpleDateFormat(
			"yyyy年MM月", Locale.JAPAN);
	private transient ListView goodListView;
	/**
	 * 選択されている日付の描画パターン。初期値は”今日”
	 */
	private transient DateSelector selectedDate = DateSelector.Today;

	/** Called when the activity is first created. */
	/*
	 * {@inheritDoc} Activity生成時の動作定義．
	 */
	@Override
	public void onCreate(final Bundle sInstanceState) {
		super.onCreate(sInstanceState);
		setContentView(R.layout.main);

		// リストビューの初期設定
		setListView();
		// アクションバーのタイトル設定
		if (selectedDate.equals(DateSelector.Today)) {
			final Date now = new Date();
			setTitle(Date_Format.format(now));
		}
		// TODO DB読み込み
		// TODO 描画
		final List<Goods> goodsList = new ArrayList<Goods>();
		goodsList.add(new Goods("昼飯", 500));
		goodListView.setAdapter(new GoodsListAdapter(getApplicationContext(),
				goodsList));
		// TODO 合計の計算
		// TODO 表示
		final TextView sumText = (TextView) findViewById(R.id.sum_plice);
		sumText.setText("500円");
	}

	/**
	 * リストビューの初期設定
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
		if (item.getItemId() == R.id.menu_date_select) {
		}
		return super.onOptionsItemSelected(item);
	}

	public void onDateSelected(int witch) {

	}
}