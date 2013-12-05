package edu.gatech.techbook;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class OnMyCourseClickListener implements OnItemClickListener {

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(view.getContext(), CourseActivity.class);
		intent.putExtra("course", (String)parent.getItemAtPosition(position));
		view.getContext().startActivity(intent);
	}

	

}
