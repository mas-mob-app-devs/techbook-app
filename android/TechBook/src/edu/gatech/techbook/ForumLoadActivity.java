package edu.gatech.techbook;

import java.util.HashMap;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.Toast;
import api.API;
import api.Topic;

public class ForumLoadActivity extends Activity {
	
private API loggedInApi = LoggedInActivity.loggedInApi;

	public static List<Topic> globalTopicList;
	public static HashMap<String, Topic> globalPopulatedTopicList;
	
	private Activity loadingActivity;
	
	private List<String> listDataHeader;
	private HashMap<String, List<String>> listDataChild;
	
	private List<Topic> topicList;
	private HashMap<String, Topic> populatedTopicList;
	
	private String department;
	private String course;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_load);
		
//		Intent forumIntent = new Intent(this, ForumActivity.class);
//	    forumIntent.putExtra("department", "CS");
//	    forumIntent.putExtra("course", "CS1331");
//	    startActivity(forumIntent);
		
		loadingActivity = this;
		
		ForumLoadActivity.globalTopicList = null;
		ForumLoadActivity.globalPopulatedTopicList = null;
		
		department = getIntent().getExtras().getString("department");
		course = getIntent().getExtras().getString("course");
		
		ObtainForumInformation getTask = new ObtainForumInformation();
		getTask.execute(new Void[0]);
		WaitForumInformation waitTask = new WaitForumInformation();
		waitTask.execute(new Void[0]);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forum_load, menu);
		return true;
	}
	
	private class  ObtainForumInformation extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.d("ForumLoad Task", "Loading forum data");
			
			//Toast.makeText(loadingContext, "Doing in background", Toast.LENGTH_LONG).show();
			
			
			topicList = loggedInApi.getForumTopics(department, course);
			populatedTopicList = new HashMap<String, Topic>();
			
			for(Topic topic : topicList) {
				Topic populatedTopic = loggedInApi.getThread(department, course, topic.getThreadID());
				populatedTopicList.put(topic.getThreadID(), populatedTopic);
			}
			
			ForumLoadActivity.globalTopicList = topicList;
			ForumLoadActivity.globalPopulatedTopicList = populatedTopicList;
			
			return null;
		}
		
//		@Override
//		protected void onPostExecute(Void v) {
//			super.onPostExecute(v);
//			Toast.makeText(loadingContext, "Post executing", Toast.LENGTH_LONG).show();
//			
//			ForumLoadActivity.globalTopicList = topicList;
//			ForumLoadActivity.globalPopulatedTopicList = populatedTopicList;
//			
//			Intent forumIntent = new Intent(loadingContext, ForumActivity.class);
//		    forumIntent.putExtra("department", "CS");
//		    forumIntent.putExtra("course", "CS1331");
//		    startActivity(forumIntent);
//		}
		
	}

	
	private class  WaitForumInformation extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.d("WaitForum", "Waiting for forum data");
			
			while(true) {
				if((ForumLoadActivity.globalTopicList != null) 
						&& (ForumLoadActivity.globalPopulatedTopicList != null)) {
					break;
				}
			}
			
			Log.d("WaitForum", "Finished getting forum data!");
			
			loadingActivity.runOnUiThread(new Runnable() {
				public void run() {
					Intent forumIntent = new Intent(loadingActivity, ForumActivity.class);
					forumIntent.putExtra("department", department);
					forumIntent.putExtra("course", course);
					startActivity(forumIntent);
				}
			});
			
			return null;
		}
		
	}

	
}
