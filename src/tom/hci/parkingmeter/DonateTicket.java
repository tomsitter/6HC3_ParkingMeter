package tom.hci.parkingmeter;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DonateTicket extends Activity {

	Button scan;
	Integer hours_remaining; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_donate_ticket);
		
		
		hours_remaining = (Integer) getIntent().getExtras().get("remaining_time");
		scan  = (Button) findViewById(R.id.button_scan1);
		
		scan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(DonateTicket.this);
				
					builder.setMessage("Donating any additional time\nThank you!")
					.setTitle("Scan now using scanner below")
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent intent = new Intent(DonateTicket.this, MainActivity.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
		getMenuInflater().inflate(R.menu.donate_ticket, menu);
		return true;
	}

}
