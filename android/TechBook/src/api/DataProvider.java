package api;
import java.util.List;


public interface DataProvider {

	public List<Course> getMyCourses(String user);
	
	public String[] getMyRecentForums();
	
	public List<Department> getDepartments();
	
	public List<String> getSpecificDepartment(String departmentCode);
	
	public String[] getForumTopics();
	
	public String[] getThread();
	
	public String[] getUsersThreads();

}
