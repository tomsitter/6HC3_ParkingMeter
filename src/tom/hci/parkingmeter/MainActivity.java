package tom.hci.parkingmeter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	Calendar expiry;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Bundle extras = getIntent().getExtras();
		
		if (extras != null) {
			expiry = (Calendar) extras.get("expiry");
			//Log.d("TOM", "Getting hours purchased");
			if (expiry == null) {
				Log.d("TOM", "calender is null");
				expiry = Calendar.getInstance();
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		
		MenuInflater inflator = getMenuInflater();
		inflator.inflate(R.menu.parkingmenu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if(item.getItemId() == R.id.item1) {
			Log.d("MenuOptions", "Option 1 was clicked");
		} else if (item.getItemId() == R.id.item2) {
			Log.d("MenuOptions", "Option 2 was clicked");
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void buyTicket(View view) {
	
		Intent intent = new Intent(this, BuyActivity.class);
		//Log.d("TOM", "About to launch Buy activity");
	    startActivity(intent);
	}

	public void refundTicket(View view) {	
		Intent intent = new Intent(this, RefundTicket.class);
		int remaining_time = RemainingTime(expiry);
		intent.putExtra("remaining_time", remaining_time);
		startActivity(intent);
	}
	
	public void donateTicket(View view) {
		Intent intent = new Intent(this, DonateTicket.class);
		int remaining_time = RemainingTime(expiry);
		intent.putExtra("remaining_time", remaining_time);
		//Log.d("TOM", "put extra, starting DonateTicket");
		startActivity(intent);	
	}
	
	public int RemainingTime(Calendar expiry) {
		
		if (expiry == null) {
			return 0;
		}
		
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		
		//Log.d("TOM", sdf.format(now.getTime()));
		//Log.d("TOM", sdf.format(expiry.getTime()));
		
		
		long millis = expiry.getTimeInMillis() - now.getTimeInMillis();
		int hours_remaining = (int) (millis / (60*60*1000));
		
		if (hours_remaining > 3) {
			hours_remaining = 1;
		} else if (hours_remaining < 1) {
			hours_remaining = 0;
		}
		//Log.d("TOM", "Remaining hours calculation finished");
		return hours_remaining;
	}
	

}
