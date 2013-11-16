package edu.gatech.techbook;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class InitActivity extends Activity  implements OnClickListener{

	private Button loginButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        
        loginButton = (Button) findViewById(R.id.initLoginButton);
        loginButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_init, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.d("InitActivity", "A button was pressed");
		if(v == loginButton) {
			Log.d("InitActivity", "Login button was pressed");
			Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dev.m.gatech.edu/login?url=techbook://loggedin&sessionTransfer=window"));
			startActivity(myIntent);
		} else {
			Log.d("InitActivity", "Some other button was pressed");
		}
	}
    
}
