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
 *         �N�����ɕ\������Activity
 */
public class TopActivity extends ActionBarActivity {
	private final transient SimpleDateFormat Date_Format = new SimpleDateFormat(
			"yyyy�NMM��dd��", Locale.JAPAN);
	private final transient SimpleDateFormat Month_Format = new SimpleDateFormat(
			"yyyy�NMM��", Locale.JAPAN);
	private transient ListView goodListView;
	/**
	 * �I������Ă�����t�̕`��p�^�[���B�����l�́h�����h
	 */
	private transient DateSelector selectedDate = DateSelector.Today;

	/** Called when the activity is first created. */
	/*
	 * {@inheritDoc} Activity�������̓����`�D
	 */
	@Override
	public void onCreate(final Bundle sInstanceState) {
		super.onCreate(sInstanceState);
		setContentView(R.layout.main);

		// ���X�g�r���[�̏����ݒ�
		setListView();
		// �A�N�V�����o�[�̃^�C�g���ݒ�
		if (selectedDate.equals(DateSelector.Today)) {
			final Date now = new Date();
			setTitle(Date_Format.format(now));
		}
		// TODO DB�ǂݍ���
		// TODO �`��
		final List<Goods> goodsList = new ArrayList<Goods>();
		goodsList.add(new Goods("����", 500));
		goodListView.setAdapter(new GoodsListAdapter(getApplicationContext(),
				goodsList));
		// TODO ���v�̌v�Z
		// TODO �\��
		final TextView sumText = (TextView) findViewById(R.id.sum_plice);
		sumText.setText("500�~");
	}

	/**
	 * ���X�g�r���[�̏����ݒ�
	 */
	private void setListView() {
		goodListView = (ListView) findViewById(R.id.goods_list);
		// final Button addButton = new Button(this);
		// addButton.setText(getString(R.string.add_button));
		// goodListView.addFooterView(addButton);
	}

	/*
	 * {@inheritDoc} �I�v�V�������j���[�������̓���
	 */
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// TODO Auto-generated method stub
		final MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	/*
	 * {@inheritDoc} �I�v�V�������j���[�I�����̓���
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