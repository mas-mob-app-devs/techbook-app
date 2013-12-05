package edu.gatech.techbook;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class PostButtonListener implements OnClickListener {
	
	private Activity callingActivity;
	private String department;
	private String course;
	private String threadID;
	private EditText textbox;
	
	public PostButtonListener(Activity callingActivity, String department, String course, String threadID, EditText textbox) {
		this.callingActivity = callingActivity;
		this.department = department;
		this.course = course;
		this.threadID = threadID;
		this.textbox = textbox;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String postText = textbox.getText().toString();
		Toast.makeText(callingActivity, "Posting to " + threadID, Toast.LENGTH_LONG).show();
		LoggedInActivity.loggedInApi.postReply(department, course, threadID, postText);
	}
	
}
