
<?php

	include 'db_helper.php';
	
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
			(SELECT COURSE.courseID FROM COURSE where COURSE.department_code = '%s' AND COURSE.courseNumber = '%s') ORDER BY THREAD.threadID DESC",
			mysql_real_escape_string($department),
			mysql_real_escape_string($class));
			//LIMIT ###
		$results = getDBResultsArray($query);
		header("Content-type: application/json");
		echo json_encode($results);
		visitForum($department, $class);
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
		//var_dump($thread_result);
		
		$post_query = sprintf("SELECT POST.prismID, POST.post, POST.timestamp FROM POST where POST.threadID = '%s' ORDER BY POST.postID ASC",
			mysql_real_escape_string($thread));
		//echo $post_query;
		$post_results = getDBResultsArrayNoDie($post_query);
		//var_dump($post_results);
		//echo 'Bananas';
		
		$full_thread = $thread_result;
		//var_dump($full_thread);
		$full_thread["posts"] = $post_results;
		
		header("Content-type: application/json");
		echo json_encode($full_thread);
	}
	
	function addCourseThread($department, $class, $subject, $post) {
		$prismId = getPrismId();
		
//		$courseId_query = sprintf("SELECT COURSE.courseID FROM COURSE where COURSE.department_code = '%s' AND COURSE.courseNumber = '%s'",
//			mysql_real_escape_string($department),
//			mysql_real_escape_string($class));
//		$courseId = getDBResultRecord($courseId_query)["courseID"];
		$courseId = getCourseId($department, $class);
		
		$thread_insert = sprintf("INSERT INTO THREAD (`prismID`, `courseID`, `threadID`, `first_post`, `timestamp`, `subject`) 
			VALUES ('%s', '%s', NULL, '%s', CURRENT_TIMESTAMP, '%s')",
			mysql_real_escape_string($prismId),
			mysql_real_escape_string($courseId),
			mysql_real_escape_string($post),
			mysql_real_escape_string($subject));
		$result = getDBResultInserted($thread_insert, 'threadID');
		
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function addCourseThreadPost($thread, $post) {
		$prismId = getPrismId();
		$post_insert = sprintf("INSERT INTO `POST` (`threadID`, `prismID`, `post`) VALUES ('%s', '%s', '%s')", 
			mysql_real_escape_string($thread),
			mysql_real_escape_string($prismId),
			mysql_real_escape_string($post));
		$result = getDBResultInserted($post_insert, 'postID');
		
		header("Content-type: application/json");
		echo json_encode($result);
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
	
	function addMyCourse($department, $class) {
		$prismId = getPrismId();
		$courseId = getCourseId($department, $class);
		$myCourse_insert = sprintf("INSERT INTO `STUDENT_COURSE` (`prismID`, `courseID`) VALUES ('%s',  '%s');",
			mysql_real_escape_string($prismId),
			mysql_real_escape_string($courseId));
		$result = getDBResultInserted($myCourse_insert, 'courseID');
		
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function listMyRecentForums() {
		$prismId = getPrismID();
		listUserRecentForums($prismId);
	}
	
	function listUserRecentForums($user) {
		$query = sprintf("SELECT c.department_code, c.courseNumber, srf.timestamp 
			FROM (STUDENT_RECENT_FORUM srf INNER JOIN COURSE c ON c.courseID = srf.courseID) 
			where srf.prismID = '%s' ORDER BY srf.timestamp DESC",
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
			WHERE t.prismID = '%s' ORDER BY t.timestamp DESC",
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
		
		$post_query = sprintf("SELECT POST.prismID, POST.post, POST.timestamp FROM POST where POST.threadID = '%s' ORDER BY POST.postID ASC",
			mysql_real_escape_string($thread));
		$post_results = getDBResultsArray($post_query);
		
		$full_thread = $thread_result;
		$full_thread["posts"] = $post_results;
		
		header("Content-type: application/json");
		echo json_encode($full_thread);
	}
	
	function register() {
		$prismId = getPrismId();
		$user_insert = sprintf("INSERT INTO `USER`(`prismID`) VALUES ('%s') ON DUPLICATE KEY UPDATE prismID = prismID",
			mysql_real_escape_string($prismId));
		$result = getDBResultInserted($user_insert, 'prismID');
		
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function visitForum($department, $class) {
		$prismId = getPrismId();
		$courseId = getCourseId($department, $class);
		$recentForum_insert = sprintf("INSERT INTO `STUDENT_RECENT_FORUM`(`prismID`, `courseID`, `timestamp`)
			VALUES ('%s',  '%s', CURRENT_TIMESTAMP) ON DUPLICATE KEY UPDATE timestamp = CURRENT_TIMESTAMP",
			mysql_real_escape_string($prismId), 
			mysql_real_escape_string($courseId));
		$result = getDBResultInserted($recentForum_insert, 'courseID');
	}
	
	function markThread($threadId) {
		$prismId = getPrismId();
		$markThread_insert = sprintf("INSERT INTO `STUDENT_MARKED_THREADS` (`prismID`, `threadID`) 
			VALUES ('%s',  '%s')",
			mysql_real_escape_string($prismId),
			mysql_real_escape_string($threadId));
		$result = getDBResultInserted($markThread_insert, 'threadID');
	}
	
	function unmarkThread($threadId) {
		$prismId = getPrismId();
		$markThread_delete = sprintf("DELETE FROM `STUDENT_MARKED_THREADS` WHERE `prismID`='%s' AND `threadID`='%s'",
			mysql_real_escape_string($prismId),
			mysql_real_escape_string($threadId));
		$result = getDBResultAffected($markThread_delete);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	// Helper functions
	
	function getPrismID() {
		global $_USER;
		$prismID = $_USER['uid'];
		registerUser($prismID);
		return $prismID;
	}
	
	function getCourseId($department, $class) {
		$courseId_query = sprintf("SELECT COURSE.courseID FROM COURSE where COURSE.department_code = '%s' AND COURSE.courseNumber = '%s'",
			mysql_real_escape_string($department),
			mysql_real_escape_string($class));
		$courseId = getDBResultRecord($courseId_query)["courseID"];
		return $courseId;
	}
	
	function registerUser($prismId) {
		$user_insert = sprintf("INSERT INTO `USER`(`prismID`) VALUES ('%s') ON DUPLICATE KEY UPDATE prismID = prismID",
			mysql_real_escape_string($prismId));
		$result = getDBResultInserted($user_insert, 'prismID');
	}
	
	
?>