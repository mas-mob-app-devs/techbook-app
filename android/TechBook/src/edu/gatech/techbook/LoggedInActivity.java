package edu.gatech.techbook;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class LoggedInActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logged_in);
		
		Intent startingIntent = getIntent();
		String startingAction = startingIntent.getAction();
		
		if (!startingAction.equals(Intent.ACTION_VIEW)) {
	        throw new RuntimeException("Should not happen");
	    }

		Uri data = startingIntent.getData();
	    String sessionName = data.getQueryParameter("sessionName");
	    String sessionId = data.getQueryParameter("sessionId");
	    
	    Log.d("LoggedInActivity", "sessionName=" + sessionName);
	    Log.d("LoggedInActivity", "sessionId=" + sessionId);
	    
	    // TODO Create API calling object
	    
	    Intent homeIntent = new Intent(this, HomeActivity.class);
	    startActivity(homeIntent);
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logged_in, menu);
		return true;
	}

}
