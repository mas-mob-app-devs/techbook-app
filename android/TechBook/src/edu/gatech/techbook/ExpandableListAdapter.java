package edu.gatech.techbook;

import java.util.HashMap;
import java.util.List;
 
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import api.Post;
import api.Topic;
 
public class ExpandableListAdapter extends BaseExpandableListAdapter {
 
    private Activity activity;
//    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
//    private HashMap<String, List<String>> _listDataChild;
	private View view;
    private String department;
    private String course;
    private List<Topic> topicList;
    private HashMap<String, Topic> populatedTopicList;
 
//    public ExpandableListAdapter(Context context, List<String> listDataHeader,
//            HashMap<String, List<String>> listChildData) {
//        this._context = context;
//        this._listDataHeader = listDataHeader;
//        this._listDataChild = listChildData;
//    }
    
    public ExpandableListAdapter(Activity context, ExpandableListView view, String department, String course,
    		List<Topic> topicList, HashMap<String, Topic> populatedTopicList) {
    	this.activity = context;
    	this.view = view;
    	this.department = department;
    	this.course = course;
    	this.topicList = topicList;
    	this.populatedTopicList = populatedTopicList;
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return getPopulatedTopic(groupPosition).getPosts().get(childPosititon);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
    	
    	if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.thread_post, null);
		}
    	
    	TextView postUserView = (TextView) convertView.findViewById(R.id.threadPostUser);
		TextView postDateView = (TextView) convertView.findViewById(R.id.threadPostDate);
		TextView postTextView = (TextView) convertView.findViewById(R.id.threadPostText);
		EditText postEntryText = (EditText) convertView.findViewById(R.id.threadEntryText);
		Button postEntryPostButton = (Button) convertView.findViewById(R.id.threadEntryPostButton);

    	if(isLastChild) {
    		postUserView.setVisibility(View.GONE);
    		postDateView.setVisibility(View.GONE);
    		postTextView.setVisibility(View.GONE);
    		postEntryText.setVisibility(View.VISIBLE);
    		postEntryPostButton.setVisibility(View.VISIBLE);
    		
    		String threadID = topicList.get(groupPosition).getThreadID();
    		PostButtonListener buttonListener = new PostButtonListener(activity, department, course, threadID, postEntryText);
    		postEntryPostButton.setOnClickListener(buttonListener);
    		postEntryPostButton.clearComposingText();
    		postEntryPostButton.clearFocus();
    		Log.d("ExpandableListAdapter", "childNum=" + childPosition);
    	} else {
    		postUserView.setVisibility(View.VISIBLE);
    		postDateView.setVisibility(View.VISIBLE);
    		postTextView.setVisibility(View.VISIBLE);
    		postEntryText.setVisibility(View.GONE);
    		postEntryPostButton.setVisibility(View.GONE);
    		
    		String postUser;
    		String postDate;
    		String postText;
    		if(childPosition > 0)
    		{
    			Post post = (Post) getChild(groupPosition, (childPosition - 1));
    			postUser = post.getPrismID();
    			postDate = post.getTimeStamp();
    			postText = post.getPost();
    		} else {
    			Topic topic;
    			synchronized(topicList) {
    				topic = topicList.get(groupPosition);
    			}
    			postUser = "";
    			postDate = "";
    			postText = topic.getFirst_post();
    		}


    		

    		postUserView.setText(postUser);
    		postDateView.setText(postDate);
    		postTextView.setText(postText);
    	}

        return convertView;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
    	Topic threadTopic = getPopulatedTopic(groupPosition);
//    	Topic threadTopic = updatePopulatedTopic(groupPosition);
    	if(threadTopic == null) {
    		return 0;
    	}
    	return (threadTopic.getPosts().size() + 2);
//    	return (updatePopulatedTopic(groupPosition).getPosts().size() + 1);
    	/* 
    	 * +1 is used here to allow a separate post layer for the original posters post.
    	 */
    }
 
    @Override
    public Object getGroup(int groupPosition) {
    	Topic groupTopic;
//    	synchronized(this.topicList) {
//    		groupTopic = this.topicList.get(groupPosition);
//    	}
//    	updatePopulatedTopic(groupPosition);
		groupTopic = this.topicList.get(groupPosition);
    	return groupTopic;
    }
 
    @Override
    public int getGroupCount() {
    	int topicCount;
    	synchronized (this.topicList) {
    		topicCount = this.topicList.size();
		}
    	getTopics();
    	return topicCount;
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        
        Topic groupTopic = (Topic) getGroup(groupPosition);
        
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.thread_heading, null);
        }
 
        TextView topicUser = (TextView) convertView.findViewById(R.id.threadHeaderUser);
        TextView topicDate = (TextView) convertView.findViewById(R.id.threadHeaderDate);
        TextView topicSubject = (TextView) convertView.findViewById(R.id.threadHeaderSubject);
        
        topicUser.setText(groupTopic.getPrismID());
        topicDate.setText(groupTopic.getTimestamp());
        
        topicSubject.setTypeface(null, Typeface.BOLD);
        topicSubject.setText(groupTopic.getSubject());
        
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    
    private List<Topic> getTopics() {
//    	UpdateThreadsTask updateTask = new UpdateThreadsTask(this, null);
//    	updateTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    	return this.topicList;
    }
    
    private Topic getPopulatedTopic(int groupPosition) {
    	Topic topic;
    	synchronized(topicList) {
    		topic = topicList.get(groupPosition);
    	}
    	
    	Topic populatedTopic;
    	synchronized(populatedTopicList) {
    		populatedTopic = populatedTopicList.get(topic.getThreadID());
    	}
    	
    	return populatedTopic;
    }
    
//    private Topic updatePopulatedTopic(int groupPosition) {
//    	Topic topic;
//    	synchronized(topicList) {
//    		topic = topicList.get(groupPosition);
//    	}
//    	
//    	Topic populatedTopic;
//    	synchronized(populatedTopicList) {
//    		populatedTopic = populatedTopicList.get(topic.getThreadID());
//    	}
//    	
//    	UpdatePostsTask updateTask = new UpdatePostsTask(this, null);
//    	updateTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, groupPosition);
//    	
//    	
//    	return populatedTopic;
//    }
    
//    private class UpdateThreadsTask extends AsyncTask<Void, Void, Void> {
//    	
//    	private ExpandableListAdapter adapter;
//    	private View calledView;
//    	
//    	private List<Topic> localTopicList;
//    	
//    	UpdateThreadsTask(ExpandableListAdapter adapter, View view) {
//    		super();
//    		this.adapter = adapter;
//    		this.calledView = view;
//    	}
//
//		@Override
//		protected Void doInBackground(Void... args) {
//			
//			localTopicList = LoggedInActivity.loggedInApi.getForumTopics(department, course);
//			return null;
//		}
//		
//		@Override
//		protected void onPostExecute(Void v) {
//			super.onPostExecute(v);
//			
//			if(localTopicList != null) {
//				synchronized (topicList) {
//					topicList = localTopicList;
//				} 
//			}
//		}
//    	
//    }
//    
//    private class UpdatePostsTask extends AsyncTask<Integer, Void, Void> {
//    	
//    	private ExpandableListAdapter adapter;
//    	private View calledView;
//    	
//    	private Topic topic;
//    	private Topic populatedTopic;
//    	
//    	UpdatePostsTask(ExpandableListAdapter adapter, View view) {
//    		super();
//    		this.adapter = adapter;
//    		this.calledView = view;
//    	}
//    	
//		@Override
//		protected Void doInBackground(Integer... params) {
//			int groupPosition = params[0];
//			
//	    	synchronized(topicList) {
//	    		topic = topicList.get(groupPosition);
//	    	}
//	        populatedTopic = LoggedInActivity.loggedInApi.getThread(department, 
//	        		course, topic.getThreadID());
//			
//			return null;
//		}
//		
//		@Override
//		protected void onPostExecute(Void v) {
//			super.onPostExecute(v);
//			
//			if(populatedTopic != null) {
//	        	synchronized(populatedTopicList) {
//	        		populatedTopicList.put(populatedTopic.getThreadID(), populatedTopic);
//	        	}
//	        }
//			
//			
//		}
//    	
//    }
}
