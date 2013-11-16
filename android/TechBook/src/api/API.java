package api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;


public class API implements DataProvider{

	@Override
	public List<Course> getMyCourses(String user) {
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

	@Override
	public String[] getMyRecentForums() {
		// TODO Auto-generated method stub
		return null;
	}

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

	@Override
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

	@Override
	public String[] getForumTopics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getThread() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getUsersThreads() {
		// TODO Auto-generated method stub
		return null;
	}

}
