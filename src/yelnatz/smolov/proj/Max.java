package yelnatz.smolov.proj;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.widget.TextView;

public class Max extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Integer max, weight, rep;
		setContentView(R.layout.max);
		TextView maxet = (TextView) findViewById(R.id.etmaxshow);
		Bundle gotPackage = getIntent().getExtras();
		weight = (int) gotPackage.getDouble("weight");
		rep = (int) gotPackage.getDouble("reps");
		max = (int) gotPackage.getDouble("max");
		maxet.setText(Html.fromHtml("Approximate Max of " + weight + "x" + rep+ " is <b><font size=24>" + max + "</b></font>"));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		finish();
		return true;
	}
}
