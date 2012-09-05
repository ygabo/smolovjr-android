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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends Activity implements OnClickListener {

	Button					save, maxcalc;
	Double					maxw, maxr, onemr;
	EditText				name, weight, add2, add3, maxweight, maxrep;
	SharedPreferences		commondata;
	private final String	filename	= "SECRET";
	private double[]		repmax		= { 0, 1, .943, .906, .881, .856, .831, .807, .786, .765, .744, .723, .703, .688, .675, .662, .650, .638, .627, .616,
			.606,						};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.settings);

		save = (Button) findViewById(R.id.bcalculate);
		weight = (EditText) findViewById(R.id.etweight);
		add2 = (EditText) findViewById(R.id.etaddweek2);
		add3 = (EditText) findViewById(R.id.etaddweek3);
		maxweight = (EditText) findViewById(R.id.etweightmax);
		maxrep = (EditText) findViewById(R.id.etrepmax);
		maxcalc = (Button) findViewById(R.id.bcalcmax);

		maxcalc.setOnClickListener(this);
		save.setOnClickListener(this);
		commondata = getSharedPreferences(filename, 0);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bcalculate: {
				String wt = weight.getText().toString();
				String ad2 = add2.getText().toString();
				String ad3 = add3.getText().toString();

				if (checkInputCalc(wt, ad2, ad3)) {
					SharedPreferences.Editor prefseditor = commondata.edit();
					prefseditor.putString("weight", wt);
					prefseditor.putString("add", ad2);
					prefseditor.putString("addd", ad3);
					prefseditor.commit();
					finish();
				}
				else {
					Toast.makeText(this, "Need Proper Input. Please Try Again.", Toast.LENGTH_SHORT).show();
				}
				break;
			}
			case R.id.bcalcmax: {
				if (checkInputCalc(maxweight.getText().toString(), maxrep.getText().toString(), "lol")) {
					maxw = Double.parseDouble(maxweight.getText().toString());
					maxr = Double.parseDouble(maxrep.getText().toString());
					onemr = maxw / repmax[Integer.parseInt(maxrep.getText().toString())];
					Bundle basket = new Bundle();
					basket.putDouble("weight", maxw);
					basket.putDouble("reps", maxr);
					basket.putDouble("max", onemr);
					Intent i = new Intent("yelnatz.smolov.proj.MAXDIALOG");
					i.putExtras(basket);
					startActivity(i);
				}
				else {
					Toast.makeText(this, "Need Proper Input. Please Try Again.", Toast.LENGTH_SHORT).show();
				}
				break;
			}
		}
	}

	// return true if one of them is empty
	private boolean checkInputCalc(String wt, String ad2, String ad3) {
		return !(wt.equals("") || ad2.equals("") || ad3.equals(""));
	}

	// ////////MENU BUTTON

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);

		MenuInflater popUp = getMenuInflater();
		popUp.inflate(R.menu.edit_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menuedithelp:
				Intent i = new Intent("yelnatz.smolov.proj.HELP");
				startActivity(i);
				break;
			case R.id.menueditback:
				finish();
				break;
		}
		return false;
	}
}
