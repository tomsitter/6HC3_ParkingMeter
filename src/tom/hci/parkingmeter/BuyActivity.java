package tom.hci.parkingmeter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class BuyActivity extends Activity {

	SeekBar seekbar;
	TextView value;
	TextView cost;
	Button button;
	Spinner spinner;
	String selected_hours = "1";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy);
		// Show the Up button in the action bar.
		//setupActionBar();
		
		
		value = (TextView) findViewById(R.id.text_hours);
		cost = (TextView) findViewById(R.id.text_cost);
		seekbar = (SeekBar) findViewById(R.id.seek_hours);
		button = (Button) findViewById(R.id.button_buyticket);
		spinner = (Spinner) findViewById(R.id.spinner_pay);
		
		//Log.d("TOM", "Set up variables");
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.paymentmethods, R.layout.spinner_item);
		spinner.setAdapter(adapter);
		
		//Log.d("TOM", "Set up spinner with payment methods");
		
		
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (progress == 0) {
					button.setEnabled(false);
				} else {
					button.setEnabled(true);
				}
				selected_hours = String.valueOf(progress);
				
				if (progress >= 4) {
					value.setText(R.string.until_morning);
					cost.setText("$20.00");
				} else {
					if (progress == 1) {
						value.setText(progress + " hour");
					} else {
						value.setText(progress + " hours");
					}
					
					int price = progress * 5;
					cost.setText("$" + price + ".00");
				}
			}
		});
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(BuyActivity.this, Payment.class);
				intent.putExtra("hours", selected_hours);
				intent.putExtra("paymentmethod", spinner.getSelectedItem().toString());
				Log.d("TOM", "Put extra, preparing to start intent");
				startActivity(intent);
			}
		});
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buy, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
