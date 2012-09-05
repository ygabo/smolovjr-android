package yelnatz.smolov.proj;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.widget.TextView;

public class Help extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Integer max, weight, rep;
		setContentView(R.layout.help);
		TextView maxet = (TextView) findViewById(R.id.ethelp);

		maxet.setText(Html.fromHtml("<p><b><u> Max Bench/Deadlift/Squat</b></u> - Max weight at one repition, either lb or kg. </p>"
				+ "<p><b><u> 2nd Week</b></u> - Additional weight for the second week.</p>" + " <p><b><u> 3rd Week</b></u> - Additional weight for the third week.</p>"
				+ "<p><b><u> Weight </b></u>- Weight at working set, either lb or kg.</p>" + "<p><b><u> Reps </b> </u>- Number of reps at working set. </p>"));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		finish();
		return true;
	}
}
