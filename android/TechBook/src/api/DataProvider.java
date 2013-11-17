package api;
import java.util.List;


public interface DataProvider {

	
	
	public List<Course> getMyRecentForums(String user);
	
	public List<Department> getDepartments();
	
	public List<String> getSpecificDepartment(String departmentCode);
	
	public List<Topic> getForumTopics(String departmentCode, String course);
	

}
