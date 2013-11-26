package api;

public class Post {
	private String prismID, post, timeStamp;


	Post(String prismID, String post, String timeStamp){
		this.prismID=prismID;
		this.post=post;
		this.timeStamp=timeStamp;

	}


	public String getPrismID() {
		return prismID;
	}


	public String getPost() {
		return post;
	}


	public String getTimeStamp() {
		return timeStamp;
	}
}
