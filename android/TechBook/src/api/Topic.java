package api;

import java.util.List;

public class Topic {
	private String prismID, threadID, subject, first_post, timestamp;
	private List<Post> plist;



	public Topic(String prismID, String threadID, String subject, String first_post, String timestamp, List<Post> plist){
		this.prismID=prismID;
		this.threadID=threadID;
		this.subject=subject;
		this.first_post=first_post;
		this.timestamp=timestamp;
		this.plist=plist;
	}

	public String getPrismID() {
		return prismID;
	}

	public String getThreadID() {
		return threadID;
	}

	public String getSubject() {
		return subject;
	}

	public String getFirst_post() {
		return first_post;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public List<Post> getPosts(){
		return plist;
	}

	public String toString(){

		return prismID+", "+threadID+", "+subject+", "+first_post+", "+timestamp;
	}
}




