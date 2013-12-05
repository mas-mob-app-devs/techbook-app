package edu.gatech.techbook;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class OnRecForumClickListener implements OnItemClickListener {

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(view.getContext(), ForumActivity.class);
		String courseName = (String)parent.getItemAtPosition(position);
		intent.putExtra("course", courseName);
		
		String dept = courseName.replace(courseName.subSequence(courseName.length() - 4, courseName.length()), "");
		intent.putExtra("department", dept);
		view.getContext().startActivity(intent);

	}

}
