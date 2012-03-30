package jp.numa08.widgets;

import java.util.List;

import jp.numa08.objects.Goods;
import jp.numa08.redstring.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author numanuma08 独自のListアダプター。
 * 
 */
public class GoodsListAdapter extends ArrayAdapter<Goods> {
	// private static final String TAG = GoodsListAdapter.class.getSimpleName();
	private final transient LayoutInflater inflater;

	public GoodsListAdapter(final Context context, final List<Goods> objects) {
		super(context, 0, objects);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(final int position, final View convertView,
			final ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		View myConvertView = convertView;
		if (convertView == null) {
			myConvertView = inflater.inflate(
					jp.numa08.redstring.R.layout.goods_item, parent, false);
			holder = new ViewHolder();
			holder.goodName = (TextView) myConvertView
					.findViewById(R.id.goods_name);
			holder.goodPlice = (TextView) myConvertView
					.findViewById(R.id.goods_plice);
			myConvertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final Goods goods = getItem(position);
		holder.goodName.setText(goods.getName());
		holder.goodPlice.setText(goods.getPlice() + "円");
		return myConvertView;
	}

	private class ViewHolder {
		public TextView goodName;
		public TextView goodPlice;
	}
}
