package edu.gatech.techbook;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

import api.API;

public class LoggedInActivity extends Activity {
	
	public static API loggedInApi;

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
	    
	    LoggedInActivity.loggedInApi = new API(sessionName, sessionId); 
	    
	    //Intent homeIntent = new Intent(this, HomeActivity.class);
	    Intent homeIntent = new Intent(this, ForumActivity.class);
//	    Bundle bundle = homeIntent.getExtras();
//	    bundle.putString("department", "CS");
//	    bundle.putString("course", "CS1331");
	    homeIntent.putExtra("department", "CS");
	    homeIntent.putExtra("course", "CS1332");
	    startActivity(homeIntent);
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logged_in, menu);
		return true;
	}

}
