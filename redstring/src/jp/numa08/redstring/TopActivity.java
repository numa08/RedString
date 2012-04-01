package jp.numa08.redstring;

import java.text.SimpleDateFormat;
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
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
	private transient Button addButton;
	private transient DBHelper helper;
	private transient RedstringDao dao;
	/**
	 * 選択されている日付の描画パターン。初期値は”今日”
	 */
	private transient DateSelector selectedDate = DateSelector.Today;
	private transient Date viewDate;

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
		// ボタンの設定
		addButton = (Button) findViewById(R.id.add_button);
		addButton.setOnClickListener(new AddButtonListener(this));
		// アクションバーのタイトル設定
		if (selectedDate.equals(DateSelector.Today)) {
			viewDate = new Date();
			setTitle(Date_Format.format(viewDate));
		}
		// TODO DB読み込み
		helper = new DBHelper(getApplicationContext());
		dao = new RedstringDao(helper.getReadableDatabase());
		final List<Goods> goodsList = dao.findByDate(new Date());
		helper.close();
		// TODO 描画
		goodListView.setAdapter(new GoodsListAdapter(getApplicationContext(),
				goodsList));
		// TODO 合計の計算
		int sum = calcSum(goodsList);
		// TODO 表示
		viewSum(sum);
	}

	private void viewSum(int sum) {
		final TextView sumText = (TextView) findViewById(R.id.sum_plice);
		sumText.setText(sum + "円");
	}

	private int calcSum(final List<Goods> goodsList) {
		int sum = 0;
		for (final Goods goods : goodsList) {
			sum += goods.getPlice();
		}
		return sum;
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

	/**
	 * オプションから日付が変更された
	 * 
	 * @param selector
	 *            変更後の日付パターン
	 */
	public void onDateSelected(final DateSelector selector) {
	}

	/**
	 * 追加ボタンが押された
	 */
	public void onAddButtonClicked() {
		// TODO ダイアログを作る
		final LayoutInflater inflater = LayoutInflater.from(this);
		final View layout = inflater.inflate(R.layout.add_dialog,
				(ViewGroup) findViewById(R.id.add_dialog_root));
		final EditText nameText = (EditText) layout.findViewById(R.id.name);
		final EditText pliceText = (EditText) layout.findViewById(R.id.plice);
		final Builder builder = new AlertDialog.Builder(this);
		builder.setView(layout);
		builder.setPositiveButton("OK", new AddDialogListener(this, nameText,
				pliceText));
		// TODO ダイアログを開く
		builder.show();
	}

	/**
	 * 追加ダイアログから商品データが追加された
	 */
	public void onAddGoods(final Goods goods) {
		// TODO DBに追加する
		helper = new DBHelper(this);
		dao = new RedstringDao(helper.getReadableDatabase());
		dao.insert(viewDate, goods);
		// TODO 描画する
		List<Goods> goodsList = dao.findByDate(viewDate);
		goodListView.setAdapter(new GoodsListAdapter(this, goodsList));
		final int sum = calcSum(goodsList);
		viewSum(sum);
	}
}