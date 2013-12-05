package api;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.Cookie;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;

import android.content.ClipData.Item;



public class API  extends ServerResource  implements DataProvider{

	private String cookieName, cookieValue;
	private Cookie myCookie;

	public API(String cookieName, String cookieValue){
		this.cookieName=cookieName;
		this.cookieValue=cookieValue;
		this.myCookie = new Cookie(cookieName, cookieValue);//cookie here

	}
	/**
	 * This API constructor is only meant for debugging purposes, always pass the cookie, whether it is used or not
	 */
	public API(){
		this.cookieName=null;
		this.cookieValue=null;
	}

/**
 * The user's recently viewed forums
 * @return a list of courses for the forums the user has recently visited
 */
	public List<Course> getMyRecentForumsCookie() {
		List<Course> myList =new ArrayList<Course>();
		//Cookie myCookie = new Cookie(cookieName, cookieValue);//cookie here

		try {
			ClientResource client = new ClientResource("http://dev.m.gatech.edu/developer/adehn3/api/techbook/recentforums/");

			Series<Cookie> seriesCookie=new Series<Cookie>(Cookie.class); //create series
			seriesCookie.add(myCookie); //adding cookie to Series<Cookie>
			client.setCookies(seriesCookie); //addied series to ClientResource
			JsonRepresentation jsonRep = new JsonRepresentation(client.get());
			JSONArray ja =jsonRep.getJsonArray();
			JSONObject jo=null;

			for(int i=0; i < ja.length(); i++){
				jo=ja.getJSONObject(i);
				Course newCourse= new Course(jo.get("timestamp").toString(),jo.get("department_code").toString(),jo.get("courseNumber").toString());
				myList.add(newCourse);
			}//end for loop


		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return myList;
	}
/**
 * Debugging only, use the cookie version above
 */
	@Override
	public List<Course> getMyRecentForums(String user) {
		List<Course> myList =new ArrayList<Course>();

		try {
			ClientResource client = new ClientResource("http://dev.m.gatech.edu/developer/adehn3/api/techbook/recentforums/"+user+"/");
			JsonRepresentation jsonRep = new JsonRepresentation(client.get());

			JSONArray ja =jsonRep.getJsonArray();
			JSONObject jo=null;

			for(int i=0; i < ja.length(); i++){
				jo=ja.getJSONObject(i);
				Course newCourse= new Course(jo.get("timestamp").toString(),jo.get("department_code").toString(),jo.get("courseNumber").toString());
				myList.add(newCourse);
			}//end for loop


		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return myList;
	}

/**
 * Returns all available academic departments
 * @return Returns a list of department objects
 */
	@Override
	public List<Department> getDepartments() {
		List<Department> myList =new ArrayList<Department>();

		try {
			ClientResource client = new ClientResource("http://dev.m.gatech.edu/developer/adehn3/api/techbook/department/");
			JsonRepresentation jsonRep = new JsonRepresentation(client.get());

			JSONArray ja =jsonRep.getJsonArray();
			JSONObject jo=null;

			for(int i=0; i < ja.length(); i++){
				jo=ja.getJSONObject(i);
				Department newDepartment= new Department(jo.get("department_code").toString(),jo.get("college_name").toString(),jo.get("department_name").toString());
				myList.add(newDepartment);
			}//end for loop


		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return myList;
	}

	
	/**
	 * Queries for a list of department codes from our GTMOB database
	 * @return Returns a list of department codes (CS, ECE, ME)
	 */
	public List<String> getSpecificDepartment(String departmentCode) {
		List<String> myList =new ArrayList<String>();

		try {
			ClientResource client = new ClientResource("http://dev.m.gatech.edu/developer/adehn3/api/techbook/department/"+departmentCode+"/");
			JsonRepresentation jsonRep = new JsonRepresentation(client.get());

			JSONArray ja =jsonRep.getJsonArray();
			JSONObject jo=null;

			for(int i=0; i < ja.length(); i++){
				jo=ja.getJSONObject(i);

				myList.add(jo.get("courseNumber").toString());
			}//end for loop


		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return myList;
	}

	/**
	 * Returns a list of of topics in a specific class's forum
	 * @param departmentCode Example CS, ECE, ACCT
	 * @param course  Example CS1332, CS2110
	 * @return A list of Topic objects
	 */
	public List<Topic> getForumTopics(String departmentCode, String course) {
		List<Topic> myList =new ArrayList<Topic>();

		try {
			ClientResource client = new ClientResource("http://dev.m.gatech.edu/developer/adehn3/api/techbook/department/"+departmentCode+"/class/"+course+"/forum");
			JsonRepresentation jsonRep = new JsonRepresentation(client.get());

			JSONArray ja =jsonRep.getJsonArray();
			JSONObject jo=null;

			for(int i=0; i < ja.length(); i++){
				jo=ja.getJSONObject(i);
				Topic newTopic= new Topic(jo.get("prismID").toString(),jo.get("threadID").toString(),jo.get("subject").toString(),jo.get("first_post").toString(),jo.get("timestamp").toString(),null);
				myList.add(newTopic);
			}//end for loop


		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return myList;
	}
/**
 * This is for debugging, use the method directly under this one, which uses our cookie
 * @param user username of user (crazy!)
 * @return A list of courses the user has bookmarked
 */

	public List<Course> getMyCourses(String user) {
		List<Course> myList =new ArrayList<Course>();

		try {
			ClientResource client = new ClientResource("http://dev.m.gatech.edu/developer/adehn3/api/techbook/mycourses/"+user+"/");
			JsonRepresentation jsonRep = new JsonRepresentation(client.get());

			JSONArray ja =jsonRep.getJsonArray();
			JSONObject jo=null;

			for(int i=0; i < ja.length(); i++){
				jo=ja.getJSONObject(i);
				Course newCourse= new Course(null,jo.get("department_code").toString(),jo.get("courseNumber").toString());
				myList.add(newCourse);
			}//end for loop


		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return myList;
	}

/**
 * Retrieves a list of the courses that the user has bookmarked
 * @return a list of course objects
 */
	public List<Course> getMyCoursesCookie() {
		List<Course> myList =new ArrayList<Course>();
		//Cookie myCookie = new Cookie(cookieName, cookieValue);

		try {
			ClientResource client = new ClientResource("http://dev.m.gatech.edu/developer/adehn3/api/techbook/mycourses/");

			Series<Cookie> seriesCookie=new Series<Cookie>(Cookie.class); //create series
			seriesCookie.add(myCookie); //adding cookie to Series<Cookie>
			client.setCookies(seriesCookie); //added series to ClientResource
			JsonRepresentation jsonRep = new JsonRepresentation(client.get());
			JSONArray ja =jsonRep.getJsonArray();
			JSONObject jo=null;

			for(int i=0; i < ja.length(); i++){
				jo=ja.getJSONObject(i);
				Course newCourse= new Course(null,jo.get("department_code").toString(),jo.get("courseNumber").toString());
				myList.add(newCourse);
			}//end for loop


		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return myList;
	}//end getMyCoursesCookie()
	
/**
 * Returns all information related to a specific thread
 * 
 * @param departmentCode Example are CS, ECE, ME
 * @param courseNumber Examples are CS1331, ECE2031
 * @param threadID the number that corresponds the unique thread in our database
 * @return A topic object which contains thread information, as well as a List<Posts>
 */
	public Topic getThread(String departmentCode, String courseNumber,String threadID) {
		List<Post> myList =new ArrayList<Post>(); //I'll pass this into the last element of the Topic cosntructor
		Topic newTopic=null;
		String url ="http://dev.m.gatech.edu/developer/adehn3/api/techbook/department/"+departmentCode+"/class/"+courseNumber+"/forum/"+threadID+"/";
		try {
			ClientResource client = new ClientResource(url);
			JsonRepresentation jsonRep = new JsonRepresentation(client.get());
			System.out.println(url);
			//				JSONArray ja =jsonRep.getJsonArray(); //The debugger says this is where failure occurs!
			JSONObject jo=jsonRep.getJsonObject();
			JSONArray ja=jo.getJSONArray("posts");
			for(int i=0; i<ja.length();i++){
				JSONObject j2=ja.getJSONObject(i);
				Post newPost =new Post(j2.getString("prismID").toString(),j2.getString("post").toString(),j2.getString("timestamp").toString());
				myList.add(newPost);
			}//end for loop

            //I instantiate this at the end, because I need my list of Posts before I can use the constructor properly
			newTopic= new Topic(jo.get("prismID").toString(),jo.get("threadID").toString(),jo.get("subject").toString(),jo.get("first_post").toString(),jo.get("timestamp").toString(),myList);



		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newTopic;
	}
	
	/**
	 * Returns all information related to a specific thread
	 * 
	 * @param departmentCode Example are CS, ECE, ME
	 * @param courseNumber Examples are CS1331, ECE2031
	 */
		public void postTopic(String departmentCode, String courseNumber, String subject, String first_post) {
		//	List<Post> myList =new ArrayList<Post>(); //I'll pass this into the last element of the Topic constructor
		//	Topic newTopic=null;
			String data=null;
			Representation result;
			
		  //  result.setMediaType(MediaType.)
			String url ="http://dev.m.gatech.edu/developer/adehn3/api/techbook/department/"+departmentCode+"/class/"+courseNumber+"/forum";
			try {
				Series<Cookie> seriesCookie=new Series<Cookie>(Cookie.class); //create series
				seriesCookie.add(myCookie); //adding cookie to Series<Cookie>
				
				ClientResource client = new ClientResource(url);
				client.setCookies(seriesCookie); //addied series to ClientResource
				
				//JsonRepresentation jsonRep = new JsonRepresentation(client.get());
				//System.out.println(url);
				//				JSONArray ja =jsonRep.getJsonArray(); //The debugger says this is where failure occurs!
				//JSONObject jo=jsonRep.getJsonObject();
				
				
				//I create a JSON object, add the key/value pairs, then I invoke client.post. Is that it?
//				JSONObject jo= new JSONObject();
//			//	jo.put("prismID", prismID);  //This should be gotten from cookie I believe
//				jo.put("subject", subject);
//				jo.put("first_post", first_post);
				Map<String, String> params = new HashMap<String, String>();
				params.put("threadSubject", subject);
				params.put("threadPost", first_post);
				
				data = urlEncode(params);
				//data="threadSubject="+subject+"&threadPost="+first_post;
//				client.post(data, MediaType.MESSAGE_HTTP);
//				client.post(myCookie, MediaType.APPLICATION_HTTP_COOKIES);
//				client.post(data, MediaType.APPLICATION_HTTP_COOKIES);
//				client.post(data, MediaType.TEXT_PLAIN);
				//result = client.post(data, MediaType.APPLICATION_WWW_FORM);
				//result.setMediaType(MediaType.APPLICATION_WWW_FORM);
				
		        Representation r = client.post(getRepresentation(params));
				
				
		//		System.out.println(r.getText());
				System.out.println("done");
				
				
				
				
				//JsonRepresentation jsonRep = new JsonRepresentation(client.post(jo));
				
				
//				JSONArray ja=jo.getJSONArray("posts");
//				for(int i=0; i<ja.length();i++){
//					JSONObject j2=ja.getJSONObject(i);
//					Post newPost =new Post(j2.getString("prismID").toString(),j2.getString("post").toString(),j2.getString("timestamp").toString());
//					myList.add(newPost);
//				}//end for loop
//
//	            //I instantiate this at the end, because I need my list of Posts before I can use the constructor properly
//				newTopic= new Topic(jo.get("prismID").toString(),jo.get("threadID").toString(),jo.get("subject").toString(),jo.get("first_post").toString(),jo.get("timestamp").toString(),myList);



			} catch (ResourceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}//end postTopic

		
		private Representation getRepresentation(Map<String, String> params) {
			Form form = new Form();
	        for (String key : params.keySet()) {
				form.add(key, params.get(key));
			}
	        return form.getWebRepresentation();
	}
		/**
		 * Adds a course to home page specified by user
		 * 
		 * @param departmentCode Example are CS, ECE, ME
		 * @param courseNumber Examples are CS1331, ECE2031
		 */
			public void addToMyCourses(String departmentCode, String courseNumber) {
			
					String data=null;
					Representation result;
					
					String url ="http://dev.m.gatech.edu/developer/adehn3/api/techbook/mycourses/";
					try {
						Series<Cookie> seriesCookie=new Series<Cookie>(Cookie.class); //create series
						seriesCookie.add(myCookie); //adding cookie to Series<Cookie>
						
						ClientResource client = new ClientResource(url);
						client.setCookies(seriesCookie); //added series to ClientResource
	
						Map<String, String> params = new HashMap<String, String>();

						
						params.put("courseDepartment", departmentCode);
						params.put("courseCode", courseNumber);
						//params.put("courseDepartment", courseNumber);
						//params.put("courseCode", departmentCode);
						//mycourses courseDepartment && courseCode
						//marked threads  threadID
						data = urlEncode(params);
						
				        Representation r = client.post(getRepresentation(params));
						
						
					} catch (ResourceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				
			}//end addToMyCourses
			
			/**
			 * Adds a course to home page specified by user
			 * 
			 * @param departmentCode Example are CS, ECE, ME
			 * @param courseNumber Examples are CS1331, ECE2031
			 */
				public void bookmarkThreads(String threadID) {
					//	List<Post> myList =new ArrayList<Post>(); //I'll pass this into the last element of the Topic constructor
					//	Topic newTopic=null;
						String data=null;
						Representation result;
						
					  //  result.setMediaType(MediaType.)
						String url ="http://dev.m.gatech.edu/developer/adehn3/api/techbook/markedthreads/";
						try {
							Series<Cookie> seriesCookie=new Series<Cookie>(Cookie.class); //create series
							seriesCookie.add(myCookie); //adding cookie to Series<Cookie>
							
							ClientResource client = new ClientResource(url);
							client.setCookies(seriesCookie); //added series to ClientResource
							
							//JsonRepresentation jsonRep = new JsonRepresentation(client.get());
							//System.out.println(url);
							//				JSONArray ja =jsonRep.getJsonArray(); //The debugger says this is where failure occurs!
							//JSONObject jo=jsonRep.getJsonObject();
							
							
							//I create a JSON object, add the key/value pairs, then I invoke client.post. Is that it?
//							JSONObject jo= new JSONObject();
//						//	jo.put("prismID", prismID);  //This should be gotten from cookie I believe
//							jo.put("subject", subject);
//							jo.put("first_post", first_post);
							Map<String, String> params = new HashMap<String, String>();
							params.put("threadID", threadID);
							//params.put("courseCode", departmentCode);
							//mycourses courseDepartment && courseCode
							//marked threads  threadID
							data = urlEncode(params);
							//data="threadSubject="+subject+"&threadPost="+first_post;
//							client.post(data, MediaType.MESSAGE_HTTP);
//							client.post(myCookie, MediaType.APPLICATION_HTTP_COOKIES);
//							client.post(data, MediaType.APPLICATION_HTTP_COOKIES);
//							client.post(data, MediaType.TEXT_PLAIN);
							//result = client.post(data, MediaType.APPLICATION_WWW_FORM);
							//result.setMediaType(MediaType.APPLICATION_WWW_FORM);
							
					        Representation r = client.post(getRepresentation(params));
							
							
					//		System.out.println(r.getText());
							System.out.println("done");
							
							
							
							
							//JsonRepresentation jsonRep = new JsonRepresentation(client.post(jo));
							
							
//							JSONArray ja=jo.getJSONArray("posts");
//							for(int i=0; i<ja.length();i++){
//								JSONObject j2=ja.getJSONObject(i);
//								Post newPost =new Post(j2.getString("prismID").toString(),j2.getString("post").toString(),j2.getString("timestamp").toString());
//								myList.add(newPost);
//							}//end for loop
			//
//				            //I instantiate this at the end, because I need my list of Posts before I can use the constructor properly
//							newTopic= new Topic(jo.get("prismID").toString(),jo.get("threadID").toString(),jo.get("subject").toString(),jo.get("first_post").toString(),jo.get("timestamp").toString(),myList);



						} catch (ResourceException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


					
				}//end bookmarkThreads
			
			public String urlEncode(Map<String, String> formParameters) {
				
				String encodedValue = "";	
				try {
					if( formParameters != null ) {
						Set<String> parameters = formParameters.keySet();
						Iterator<String> it = parameters.iterator();
						StringBuffer buffer = new StringBuffer();

						for( int i = 0, paramCount = 0; it.hasNext(); i++ ) {
							String parameterName = (String) it.next();
							String parameterValue = (String) formParameters.get( parameterName );

							if( parameterValue != null ) {
								parameterValue = URLEncoder.encode( parameterValue, "UTF-8" );
								if( paramCount > 0 ) {
									buffer.append( "&" );
								} //if
								buffer.append( parameterName );
								buffer.append( "=" );
								buffer.append( parameterValue );
								++paramCount;
							} // if
						} // for
						encodedValue += buffer.toString();
					} // if

				} catch (UnsupportedEncodingException uee) {
					encodedValue = "";
				} // try/catch
				
				return encodedValue;
			}

			/**
			 * Returns all information related to a specific thread
			 * 
			 * @param departmentCode Example are CS, ECE, ME
			 * @param courseNumber Examples are CS1331, ECE2031
			 */
				public void postReply(String departmentCode, String courseNumber, String threadID, String postText) {
				//	List<Post> myList =new ArrayList<Post>(); //I'll pass this into the last element of the Topic constructor
				//	Topic newTopic=null;
					String data=null;
					Representation result;
					
				  //  result.setMediaType(MediaType.)
					String url ="http://dev.m.gatech.edu/developer/adehn3/api/techbook/department/"+departmentCode+"/class/"+courseNumber+"/forum/"+threadID+"/post/";
					try {
						Series<Cookie> seriesCookie=new Series<Cookie>(Cookie.class); //create series
						seriesCookie.add(myCookie); //adding cookie to Series<Cookie>
						
						ClientResource client = new ClientResource(url);
						client.setCookies(seriesCookie); //addied series to ClientResource
						
						//JsonRepresentation jsonRep = new JsonRepresentation(client.get());
						//System.out.println(url);
						//				JSONArray ja =jsonRep.getJsonArray(); //The debugger says this is where failure occurs!
						//JSONObject jo=jsonRep.getJsonObject();
						
						
						//I create a JSON object, add the key/value pairs, then I invoke client.post. Is that it?
//						JSONObject jo= new JSONObject();
//					//	jo.put("prismID", prismID);  //This should be gotten from cookie I believe
//						jo.put("subject", subject);
//						jo.put("first_post", first_post);
						Map<String, String> params = new HashMap<String, String>();
						params.put("postText", postText);
						//mycourses courseDepartment && courseCode
						//marked threads  threadID
						data = urlEncode(params);
						//data="threadSubject="+subject+"&threadPost="+first_post;
//						client.post(data, MediaType.MESSAGE_HTTP);
//						client.post(myCookie, MediaType.APPLICATION_HTTP_COOKIES);
//						client.post(data, MediaType.APPLICATION_HTTP_COOKIES);
//						client.post(data, MediaType.TEXT_PLAIN);
						//result = client.post(data, MediaType.APPLICATION_WWW_FORM);
						//result.setMediaType(MediaType.APPLICATION_WWW_FORM);
						
				        Representation r = client.post(getRepresentation(params));
						
						
				//		System.out.println(r.getText());
						System.out.println("done");
						
						
						
						
						//JsonRepresentation jsonRep = new JsonRepresentation(client.post(jo));
						
						
//						JSONArray ja=jo.getJSONArray("posts");
//						for(int i=0; i<ja.length();i++){
//							JSONObject j2=ja.getJSONObject(i);
//							Post newPost =new Post(j2.getString("prismID").toString(),j2.getString("post").toString(),j2.getString("timestamp").toString());
//							myList.add(newPost);
//						}//end for loop
		//
//			            //I instantiate this at the end, because I need my list of Posts before I can use the constructor properly
//						newTopic= new Topic(jo.get("prismID").toString(),jo.get("threadID").toString(),jo.get("subject").toString(),jo.get("first_post").toString(),jo.get("timestamp").toString(),myList);



					} catch (ResourceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
				}//end postReply
			
}
