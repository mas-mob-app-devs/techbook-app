package api;

public class Department {
	String department_code, college_name, department_name;

	public Department(String department_code, String college_name, String department_name){
		this.department_code=department_code;
		this.college_name=college_name;
		this.department_name=department_name;
	}
	
	public String getDepartmentCode(){
		return department_code;
	}
	public String getCollegeName(){
		return college_name;
	}
	public String getDepartmentName(){
		return department_name;
	}
	
	public String toString(){
		
		return department_code+", "+college_name+", "+department_name;
	}
}


