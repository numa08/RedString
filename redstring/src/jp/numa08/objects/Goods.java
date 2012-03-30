package jp.numa08.objects;

public class Goods {
	// private static final String TAG = Goods.class.getSimpleName();

	private String name;
	private int plice;

	public Goods(final String name, final int plice) {
		super();
		this.name = name;
		this.plice = plice;
	}

	public String getName() {
		return name;
	}

	public int getPlice() {
		return plice;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPlice(final int plice) {
		this.plice = plice;
	}

	@Override
	public String toString() {
		return "Goods [name=" + name + ", plice=" + plice + "]";
	}

}
