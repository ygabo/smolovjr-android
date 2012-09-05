package yelnatz.smolov.proj;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SmolovJrCalcActivity extends Activity implements OnClickListener {

	private final String	filename		= "SECRET";
	private final String	defaultadd		= "0";
	private final String	defaultweight	= "-1";
	SharedPreferences		commondata;
	TextView				w1d1, w1d2, w1d3, w1d4, w2d1, w2d2, w2d3, w2d4, w3d1, w3d2, w3d3, w3d4;
	TextView				pw2d1, pw2d2, pw2d3, pw2d4, pw3d1, pw3d2, pw3d3, pw3d4;
	TextView				displayname, displaymax;
	String					name, weight, add, addd;
	String					d1				= " 6 reps x 6 sets x ";
	String					d2				= " 7 reps x 5 sets x ";
	String					d3				= " 8 reps x 4 sets x ";
	String					d4				= "10 reps x 3 sets x ";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		intitialize();
	}

	@Override
	protected void onResume() {
		commondata = getSharedPreferences(filename, 0);

		if (commondata.contains("weight")) {
			weight = commondata.getString("weight", defaultweight);
			add = commondata.getString("add", "0");
			addd = commondata.getString("addd", "0");
			if (checkIfValid(weight, add, addd)) {
				updateWeight(weight, add, addd);
			}
			else {
				updateWeight(defaultweight, defaultadd, defaultadd);
			}
		}
		else {
			updateWeight(defaultweight, defaultadd, defaultadd);
		}

		super.onResume();
	}

	private boolean checkIfValid(String weight2, String add2, String addd2) {
		
		if( weight2 == null || add2 == null || addd2 == null ) return false;
		
		return !(weight2.equals("") || add2.equals("") || addd2.equals(""));
	}

	private void updateWeight(String weight, String add, String addd) {
		if (Integer.parseInt(weight) < 0) return;

		Integer w = Integer.parseInt(weight);
		Integer a = Integer.parseInt(add);
		Integer b = Integer.parseInt(addd);

		if (b == 0) b = a;

		w1d1.setText(d1 + roundDown(w * 0.7));
		w1d2.setText(d2 + roundDown(w * 0.75));
		w1d3.setText(d3 + roundDown(w * 0.80));
		w1d4.setText(d4 + roundDown(w * 0.85));
		w2d1.setText(d1 + roundDown((w * 0.7) + a));
		w2d2.setText(d2 + roundDown((w * 0.75) + a));
		w2d3.setText(d3 + roundDown((w * 0.8) + a));
		w2d4.setText(d4 + roundDown((w * 0.85) + a));
		w3d1.setText(d1 + roundDown((w * 0.7) + a + b));
		w3d2.setText(d2 + roundDown((w * 0.75) + a + b));
		w3d3.setText(d3 + roundDown((w * 0.80) + a + b));
		w3d4.setText(d4 + roundDown((w * 0.85) + a + b));

		pw2d1.setText("70%+" + a);
		pw2d2.setText("75%+" + a);
		pw2d3.setText("80%+" + a);
		pw2d4.setText("85%+" + a);
		pw3d1.setText("70%+" + (a + b));
		pw3d2.setText("75%+" + (a + b));
		pw3d3.setText("80%+" + (a + b));
		pw3d4.setText("85%+" + (a + b));

		displaymax.setText("1 Rep Maximum: " + weight);

	}

	// round to nearest 5
	private String roundDown(double d) {
		Double t = (d % 5);
		Double x;
		if (t > 2.5)
			x = d + (5 - t);
		else
			x = d - t;
		return Double.toString(x);
	}

	private void intitialize() {
		w1d1 = (TextView) findViewById(R.id.tvweightwk1d1);
		w1d2 = (TextView) findViewById(R.id.tvweightwk1d2);
		w1d3 = (TextView) findViewById(R.id.tvweightwk1d3);
		w1d4 = (TextView) findViewById(R.id.tvweightwk1d4);
		w2d1 = (TextView) findViewById(R.id.tvweightwk2d1);
		w2d2 = (TextView) findViewById(R.id.tvweightwk2d2);
		w2d3 = (TextView) findViewById(R.id.tvweightwk2d3);
		w2d4 = (TextView) findViewById(R.id.tvweightwk2d4);
		w3d1 = (TextView) findViewById(R.id.tvweightwk3d1);
		w3d2 = (TextView) findViewById(R.id.tvweightwk3d2);
		w3d3 = (TextView) findViewById(R.id.tvweightwk3d3);
		w3d4 = (TextView) findViewById(R.id.tvweightwk3d4);
		pw2d1 = (TextView) findViewById(R.id.percentwk2d1);
		pw2d2 = (TextView) findViewById(R.id.percentwk2d2);
		pw2d3 = (TextView) findViewById(R.id.percentwk2d3);
		pw2d4 = (TextView) findViewById(R.id.percentwk2d4);
		pw3d1 = (TextView) findViewById(R.id.percentwk3d1);
		pw3d2 = (TextView) findViewById(R.id.percentwk3d2);
		pw3d3 = (TextView) findViewById(R.id.percentwk3d3);
		pw3d4 = (TextView) findViewById(R.id.percentwk3d4);
		// displayname = (TextView) findViewById(R.id.tvname);
		displaymax = (TextView) findViewById(R.id.tvmax);
	}

	public void onClick(View v) {

	}
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);

		MenuInflater popUp = getMenuInflater();
		popUp.inflate(R.menu.side_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
			case R.id.menuedit:
				Intent i = new Intent("yelnatz.smolov.proj.SETTINGS");
				startActivity(i);
				break;
			case R.id.menuquit:
				finish();
				break;
		}
		return false;
	}
}