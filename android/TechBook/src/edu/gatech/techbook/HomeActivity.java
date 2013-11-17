package edu.gatech.techbook;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
/**
 * After CAS login, this is the main page that displays Recently Viewed Forums & MyCourses
 */
public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	private class MyNetworkTask extends AsyncTask<String, Void, String>{
		@Override
	    protected String doInBackground(String... params) {
	       
	            
	                String result = "deleted NetInterfacer, use API instead";
	                return result;
	           
	         
	    }

	    @Override
	    protected void onPostExecute(String result){
	    	
	    }
	    
	} //end private inner MyNetworkTask calss

	
}//end HomeActivity class
