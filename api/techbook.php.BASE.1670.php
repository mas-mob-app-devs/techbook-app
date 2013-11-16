
<?php

	//include 'db_credentials.php';
	include 'db_helper.php';
	
	//global $db_host;
	//global $db_database;
	//global $db_username;
	//global $db_password;
	
	
	$connection;

	function listDepartments() {
		$query = sprintf("SELECT * FROM DEPARTMENT");
		$results = getDBResultsArray($query);
		header("Content-type: application/json");
		echo json_encode($results);
	}
	
	function listCourses($department) {
		$query = sprintf("SELECT courseNumber FROM COURSE where COURSE.department_code = '%s'",
			mysql_real_escape_string($department));
		$results = getDBResultsArray($query);
		header("Content-type: application/json");
		echo json_encode($results);
	}
	
	function getCourseInfo($department, $class) {
		$query = sprintf("SELECT courseName FROM COURSE where COURSE.department_code = '%s' AND COURSE.courseNumber = '%s'",
			mysql_real_escape_string($department), 
			mysql_real_escape_string($class));
		$result = getDBResultRecord($query);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function getCourseForumList($department, $class) {
		$query = sprintf("SELECT THREAD.prismID, THREAD.threadID, THREAD.subject, THREAD.first_post, THREAD.timestamp FROM THREAD where THREAD.courseID IN 
			(SELECT COURSE.courseID FROM COURSE where COURSE.department_code = '%s' AND COURSE.courseNumber = '%s')",
			mysql_real_escape_string($department),
			mysql_real_escape_string($class));
			//LIMIT ###
		$results = getDBResultsArray($query);
		header("Content-type: application/json");
		echo json_encode($results);
	}
	
	function getCourseThread($department, $class, $thread) {
		$thread_query = sprintf("SELECT THREAD.prismID, THREAD.threadID, THREAD.subject, THREAD.first_post, THREAD.timestamp FROM THREAD 
			where THREAD.threadID = '%s' AND THREAD.courseID IN 
			(SELECT COURSE.courseID FROM COURSE where COURSE.department_code = '%s' AND COURSE.courseNumber = '%s')",
			mysql_real_escape_string($thread),
			mysql_real_escape_string($department),
			mysql_real_escape_string($class));
		$thread_result = getDBResultRecord($thread_query);
		//	LIMIT ###
		
		$post_query = sprintf("SELECT POST.prismID, POST.post, POST.timestamp FROM POST where POST.threadID = '%s' ORDER BY POST.sequenceID ASC",
			mysql_real_escape_string($thread));
		$post_results = getDBResultsArray($post_query);
		
		$full_thread = $thread_result;
		$full_thread["posts"] = $post_results;
		
		header("Content-type: application/json");
		echo json_encode($full_thread);
	}
	
	function listMyCourses() {
		$prismID = getPrismID();
		listUserCourses($prismID);
	}
	
	function listUserCourses($user) {
		$query = sprintf("SELECT department_code, courseNumber 
			FROM (STUDENT_COURSE sc INNER JOIN COURSE c ON c.courseID = sc.courseID) 
			where sc.prismID = '%s'",
			mysql_real_escape_string($user));
		$results = getDBResultsArray($query);
		header("Content-type: application/json");
		echo json_encode($results);
	}
	
	function listMyRecentForums() {
		$prismId = getPrismID();
		listUserRecentForums();
	}
	
	function listUserRecentForums($user) {
		$query = sprintf("SELECT c.department_code, c.courseNumber, srf.timestamp 
			FROM (STUDENT_RECENT_FORUM srf INNER JOIN COURSE c ON c.courseID = srf.courseID) 
			where srf.prismID = '%s'",
			mysql_real_escape_string($user));
		$results = getDBResultsArray($query);
		header("Content-type: application/json");
		echo json_encode($results);
	}
	
	function listMyMarkedThreads() {
		$prismID = getPrismID();
		listUserMarkedThreads($prismID);
	}
	
	function listUserMarkedThreads($user) {
		$query = sprintf("SELECT t.prismID, t.threadID, t.subject, t.first_post, t.timestamp 
			FROM (STUDENT_MARKED_THREADS smt INNER JOIN THREAD t ON t.threadID = smt.threadID) 
			WHERE smt.prismID = '%s'",
			mysql_real_escape_string($user));
		$results = getDBResultsArray($query);
		header("Content-type: application/json");
		echo json_encode($results);
	}
	
	function listMyThreads() {
		$prismID = getPrismID();
		listUserThreads($prismID);
	}
	
	function listUserThreads($user) {
		$query = sprintf("SELECT  c.department_code, c.courseNumber, t.threadID, t.subject, t.first_post, t.timestamp
			FROM (THREAD t INNER JOIN COURSE c ON t.courseID = c.courseID)
			WHERE t.prismID = '%s'",
			mysql_real_escape_string($user));
		$results = getDBResultsArray($query);
		
		header("Content-type: application/json");
		echo json_encode($results);
	}
	
	function getClassThread($thread) {
		$thread_query = sprintf("SELECT c.department_code, c.courseNumber, t.prismID, t.threadID, t.subject, t.first_post, t.timestamp 
			FROM (THREAD t INNER JOIN COURSE c ON t.courseID = c.courseID)
			where t.threadID = '%s'",
			mysql_real_escape_string($thread));
		$thread_result = getDBResultRecord($thread_query);
		
		$post_query = sprintf("SELECT POST.prismID, POST.post, POST.timestamp FROM POST where POST.threadID = '%s' ORDER BY POST.sequenceID ASC",
			mysql_real_escape_string($thread));
		$post_results = getDBResultsArray($post_query);
		
		$full_thread = $thread_result;
		$full_thread["posts"] = $post_results;
		
		header("Content-type: application/json");
		echo json_encode($full_thread);
	}
	
	
	
	
	
	function getPrismID() {
		global $_USER;
		$prismID = $_USER['uid'];
		return $prismID;
	}
	
	
//	function database_connect() {
//		$db_host = "db.cip.gatech.edu";
//		$db_database = "CONTRIB_HWM";
//		$db_username = "contrib_hwm";
//		$db_password = "LuttEhQNmvAA4Tau";
//		$connection = mysqli_connect($db_host, $db_username, $db_password, $db_database);
//		if (mysqli_connect_errno())
//		{
//				echo "Failed to connect to MySQL: " . mysqli_connect_error() . '<br/>';
//		}
//	}
	
//	function database_close() {
//		mysqli_close($connection);
//	}
	
	
	
?>