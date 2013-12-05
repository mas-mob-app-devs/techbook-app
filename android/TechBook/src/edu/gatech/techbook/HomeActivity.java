package edu.gatech.techbook;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import api.API;
import api.Course;
/**
 * After CAS login, this is the main page that displays Recently Viewed Forums & MyCourses
 */
public class HomeActivity extends Activity {
	private API loggedInApi = LoggedInActivity.loggedInApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		final ListView myCourseListView = (ListView) findViewById(R.id.myCoursesList);
		final ListView recForumListView = (ListView) findViewById(R.id.recentForumList);
		//String[] recForumValues = new String[5];
		//String[] myCoursesValues = new String[5];
		List<Course> recentForums = loggedInApi.getMyRecentForumsCookie();
		List<Course> myCourses = loggedInApi.getMyRecentForumsCookie();
		
		int displayCount = Math.min(recentForums.size(), 5);
		
//		for(int i=0; i< displayCount ; i++) {
//			recForumValues[i] = recentForums.get(i).getCourseNumber();  
//		}

	    final ArrayList<String> recForumlist = new ArrayList<String>();
	    final ArrayList<String> myCourseslist = new ArrayList<String>();
	    for (int i = 0; i < displayCount; ++i) {
	      recForumlist.add(recentForums.get(i).getCourseNumber());
	      myCourseslist.add(myCourses.get(i).getCourseNumber());
	      
	    }
	    final ArrayAdapter<String> recForumadapter = new ArrayAdapter<String>(this,
	        android.R.layout.simple_list_item_1, recForumlist);
	    
	    final ArrayAdapter<String> myCoursesAdapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, myCourseslist);
	    
	    myCourseListView.setOnItemClickListener(new OnMyCourseClickListener());
	    myCourseListView.setAdapter(recForumadapter);
	    
	    recForumListView.setOnItemClickListener(new OnRecForumClickListener());
	    recForumListView.setAdapter(myCoursesAdapter);
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
