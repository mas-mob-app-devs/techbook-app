package edu.gatech.techbook.test;
import api.*;

import java.util.List;

import junit.framework.TestCase;

public class APITest extends TestCase {


	API newAPI = new API("PHPSESSID", "mt7lgsbhd5k82devopokgvka36");
	public static void setUpBeforeClass() throws Exception {

	}
	protected void setUp() throws Exception {
		super.setUp();

	}

	public void testGetDepartments() {
		//	API newAPI =new API();
		List<Department> myList = newAPI.getDepartments();
		assertEquals("Computer Science",myList.get(0).getDepartmentName());
		assertEquals("CS", myList.get(0).getDepartmentCode());
		assertEquals("College of Computing", myList.get(0).getCollegeName());
		assertEquals("Computational Media", myList.get(1).getDepartmentName());
	}

	public void testGetSpecificDepartment() {
		//	API newAPI =new API();
		List<String> myList = newAPI.getSpecificDepartment("ECE");
		assertEquals("ECE2031",myList.get(0).toString());
		assertEquals("ECE2020",myList.get(1).toString());
	}

	public void testGetMyRecentForums(){
		//	API newAPI = new API();
		List<Course> myList = newAPI.getMyRecentForums("jruiz30");
		assertEquals("CS2110", myList.get(0).getCourseNumber());
	}

	public void testGetMyRecentForumsCookie(){
		//API newAPI = new API("PHPSESSID", "6tder7n69cngnacqcb476fhjq3");
		List<Course> myList = newAPI.getMyRecentForumsCookie();
		assertEquals("CS2110", myList.get(0).getCourseNumber());
	}

	public void testGetMyCourses(){
		//	API newAPI = new API();
		List<Course> myList = newAPI.getMyCourses("jruiz30");
		assertEquals("CS1331", myList.get(0).getCourseNumber());
	}

	public void testGetMyCoursesCookie(){
		//	API newAPI = new API("PHPSESSID","6tder7n69cngnacqcb476fhjq3");
		List<Course> myList = newAPI.getMyCoursesCookie();
		assertEquals("CS1331", myList.get(0).getCourseNumber());
	}


	public void testGetTopics(){
		//	API newAPI = new API();
		List<Topic> myList = newAPI.getForumTopics("CS", "CS1331");
		//	System.out.println(myList.get(0).toString());
		assertEquals("jruiz30", myList.get(0).getPrismID());
		assertEquals("103", myList.get(0).getThreadID());
		assertEquals("", myList.get(0).getSubject());
		assertEquals("", myList.get(0).getFirst_post());
		assertEquals("2013-12-04 20:27:38", myList.get(0).getTimestamp());
	}

	public void testGetThreadInformation(){
		//	API newAPI = new API();
		Topic newTopic = newAPI.getThread("CS", "CS1331","1");
		//	System.out.println(myList.get(0).toString());
		assertEquals("user1", newTopic.getPrismID());
		assertEquals("user2",newTopic.getPosts().get(0).getPrismID());
		assertEquals("Hey, I'm commenting on your thread!",newTopic.getPosts().get(0).getPost());
		assertEquals("2013-10-07 14:46:52",newTopic.getPosts().get(0).getTimeStamp());

	}
	
	public void testPostTopic(){
		
		newAPI.postTopic("CS", "CS2110", "Its working again part 2", "Yes it is working");
		
	}//end post
	
	public void testPostReply(){
		newAPI.postReply("CS", "CS1331", "1", "Android Reply!");
	}
	public void testPostMyCourses(){
		newAPI.addToMyCourses("CS", "CS1331");
	}
	
	public void testBookmarkThreads(){
		newAPI.bookmarkThreads("11");
	}

}
