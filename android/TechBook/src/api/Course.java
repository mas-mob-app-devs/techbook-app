package api;

public class Course {


String timestamp, department_code, courseNumber;

	public Course(String timestamp, String department_code, String courseNumber){
		this.timestamp=timestamp;
		this.department_code=department_code;
		this.courseNumber=courseNumber;
	}
	
	public String getTimestamp(){
		return timestamp;
	}
	public String getDepartmentCode(){
		return department_code;
	}
	public String getCourseNumber(){
		return courseNumber;
	}
	
	public String toString(){
		//String result= timestamp+", "+department_code+", "+courseNumber;
		return timestamp+", "+department_code+", "+courseNumber;
	}
}
