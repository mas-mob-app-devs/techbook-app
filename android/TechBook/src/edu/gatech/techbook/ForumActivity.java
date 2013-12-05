package edu.gatech.techbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import api.API;
import api.Topic;

public class ForumActivity extends Activity {
	
	private API loggedInApi = LoggedInActivity.loggedInApi;
	
	private ExpandableListView expListView;
	private ExpandableListAdapter expListAdapter;
	
//	private List<String> listDataHeader;
//	private HashMap<String, List<String>> listDataChild;
	
	private List<Topic> topicList;
	
	private String department;
	private String course;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum);
		
		//Bundle bundle = getIntent().getExtras();
		department = getIntent().getExtras().getString("department");
		course = getIntent().getExtras().getString("course");
		
		Log.d("ForumActivity", "Department:" + department + " Course:" +  course);
		
//		department = "CS";
//		course = "CS1331";
		
		//prepTestData();
		topicList = loggedInApi.getForumTopics(department, course);
		HashMap<String, Topic> populatedTopicList = new HashMap<String, Topic>();
		
		for(Topic topic : topicList) {
			Topic populatedTopic = loggedInApi.getThread(department, course, topic.getThreadID());
			populatedTopicList.put(topic.getThreadID(), populatedTopic);
		}
		
//		setContentView(R.layout.activity_forum);
		
		TextView forumTitleView = (TextView) findViewById(R.id.forumTitle);
		forumTitleView.setText(course + " Forums");
		
		expListView = (ExpandableListView) findViewById(R.id.forumList);
		
//		topicList = ForumLoadActivity.globalTopicList;
//		HashMap<String, Topic> populatedTopicList = ForumLoadActivity.globalPopulatedTopicList;
//		ForumLoadActivity.globalTopicList = null;
//		ForumLoadActivity.globalPopulatedTopicList = null;
		
//		expListAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
		expListAdapter = new ExpandableListAdapter(this, expListView, department, course, topicList, populatedTopicList);
		
		expListView.setAdapter(expListAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forum, menu);
		return true;
	}
	
}
