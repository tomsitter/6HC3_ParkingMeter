package tom.hci.parkingmeter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Payment extends Activity {

	Button pay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payment);
		
		pay  = (Button) findViewById(R.id.button_confirm);
		
		final String hours = getIntent().getExtras().getString("hours");
		String paymentmethod = getIntent().getExtras().getString("paymentmethod");
		
		TextView confirm = (TextView) findViewById(R.id.text_confirm);
		if (hours.equals("4")) {
			confirm.setText("Purchase parking until morning (6:00 AM) for $20.00 using " + paymentmethod);
		} 
		else {
			if (hours.equals("1")) {
				confirm.setText("Purchase parking:\n\n" + hours + " hour for $" + 
						(Integer.parseInt(hours) * 5) + ".00 using " + 
						paymentmethod);
			} else if (hours.equals("4")) {
				confirm.setText("Purchase parking:\n\nuntil 6:00 AM for $" + 
						(Integer.parseInt(hours) * 5) + ".00 using " + 
						paymentmethod);
			} else {
				confirm.setText("Purchase parking:\n\n" + hours + " hours for $" + 
						(Integer.parseInt(hours) * 5) + ".00 using " + 
						paymentmethod);
			}

			//Time time = new Time();

		}
		
		pay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Calendar expiry = Calendar.getInstance();
				int numhours = Integer.parseInt(hours);
				if (numhours < 4) {
					expiry.add(Calendar.HOUR, numhours);
				} else {
					expiry.add(Calendar.DAY_OF_YEAR, 1);
					expiry.set(Calendar.HOUR_OF_DAY, 6);
					expiry.set(Calendar.MINUTE, 0);
				}
				SimpleDateFormat expires = new SimpleDateFormat("hh:mm a, EEE, MMM yyyy");
				
				AlertDialog.Builder builder = new AlertDialog.Builder(Payment.this);
				
				builder.setMessage("Valid until \n" + expires.format(expiry.getTime()) +
						"\nDisplay in your vehicle window")
						.setTitle("Parking Permit")
						.setIcon(R.drawable.barcode)
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent = new Intent(Payment.this, MainActivity.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								intent.putExtra("expirytime", expiry);
								startActivity(intent);
							}
						});
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		});
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.payment, menu);
		return true;
	}

}
