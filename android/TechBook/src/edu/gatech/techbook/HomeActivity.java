package edu.gatech.techbook;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import api.API;
import api.Course;
/**
 * After CAS login, this is the main page that displays Recently Viewed Forums & MyCourses
 */
public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		final ListView myCourseListView = (ListView) findViewById(R.id.myCoursesList);
		final ListView recForumListView = (ListView) findViewById(R.id.recentForumList);
		API api = new API();
		String[] values = new String[5];
		List<Course> myRecentForums = api.getMyRecentForums("rkumar68");
		int displayCount = Math.min(myRecentForums.size(), 5);
		for(int i=0; i< displayCount ; i++) {
			values[i] = myRecentForums.get(i).getDepartmentCode() + myRecentForums.get(i).getCourseNumber();  
		}

	    final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < displayCount; ++i) {
	      list.add(values[i]);
	    }
	    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        android.R.layout.simple_list_item_1, list);
	    
	    myCourseListView.setOnItemClickListener(new OnMyCourseClickListener());
	    myCourseListView.setAdapter(adapter);
	    
	    recForumListView.setOnItemClickListener(new OnRecForumClickListener());
	    recForumListView.setAdapter(adapter);
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
