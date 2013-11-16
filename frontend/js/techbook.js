var recent_list = [ 'CS6250', 'EC6505', 'ME3303' ];
var my_list = [ 'CS6250', 'CS6150', 'CS3303' ];

<<<<<<< HEAD
<<<<<<< HEAD
var recentForumURL = "/developer/adehn3/api/techbook/recentforums/";
var myCoursesURL = "/developer/adehn3/api/techbook/mycourses/";

var course_threads = '{ "threadlist" :[{"course_name" : "CS6150","threads" : [{"thread_id" : "1","thread_subject" : "Info needed about class CS6150", "thread_content" : " Instructors: Gregory Abowd (Office hours: Mon 1-2pm in TSRB) and Santosh Vempala (Office hours: Fri, 9-10am in Klaus 2222) TA: Yi Han (Office hours: Tue, 1-3pm in TSRB).  C4G is a project-based research, education and service initiative of the College of Computing. ","posts" : [{"post_id" : "1","user" : "user1","post_text" : "We have a master list of project topics over the summer along with partners in the field for each topic. It will be up to student teams to determine their precise projects based on this list."}, {"post_id" : "2","user" : "user2","post_text" : "user2 first post"}]}, {"thread_id" : "2","thread_subject" : "Important topics for quiz ??", "thread_content" : " What are the most important topics for this quiz?? ","posts" : [{"post_id" : "1","user" : "Jon","post_text" : "Read all or none :) "}, {"post_id" : "2","user" : "Tom","post_text" : "You cannot read all :( "}]}]}, {"course_name" :"CS6250", "threads" : [{ "thread_id" : "1", "thread_subject" :"Need info on 6250", "thread_content" : "Is this an interesting class ??", "posts" : [{ "post_id" : "1", "user" : "user2", "post_text" :"user2 first post" }]}] }]}';
var active_course ;
var active_thread;
populateUserList();
=======
=======

>>>>>>> upstream/master
var course_threads = '{ "threadlist" :[{"course_name" : "CS6150","threads" : [{"thread_id" : "1","thread_subject" : "Info needed about class CS6150", "thread_content" : " Instructors: Gregory Abowd (Office hours: Mon 1-2pm in TSRB) and Santosh Vempala (Office hours: Fri, 9-10am in Klaus 2222) TA: Yi Han (Office hours: Tue, 1-3pm in TSRB).  C4G is a project-based research, education and service initiative of the College of Computing. ","posts" : [{"post_id" : "1","user" : "user1","post_text" : "We have a master list of project topics over the summer along with partners in the field for each topic. It will be up to student teams to determine their precise projects based on this list."}, {"post_id" : "2","user" : "user2","post_text" : "user2 first post"}]}, {"thread_id" : "2","thread_subject" : "Important topics for quiz ??", "thread_content" : " What are the most important topics for this quiz?? ","posts" : [{"post_id" : "1","user" : "Jon","post_text" : "Read all or none :) "}, {"post_id" : "2","user" : "Tom","post_text" : "You cannot read all :( "}]}]}, {"course_name" :"CS6250", "threads" : [{ "thread_id" : "1", "thread_subject" :"Need info on 6250", "thread_content" : "Is this an interesting class ??", "posts" : [{ "post_id" : "1", "user" : "user2", "post_text" :"user2 first post" }]}] }]}';
populateUserList('Ranjan');

function populateUserList(user) {
	var recent_forum = '<ul id="recent" data-role="listview" data-divider-theme="b" data-inset="false">';
	recent_forum += '<li data-role="list-divider" role="heading" >'
			+ '<p  align="center"><strong>Recent Forums</strong></p>' + '</li>';
	recent_forum += populateList(recent_list);
	recent_forum += '</ul>';

	var user_course = '<ul id="my_courses" data-role="listview" data-divider-theme="b" data-inset="false">';
	user_course += '<li data-role="list-divider" role="heading">'
			+ '<p  align="center"><strong>My Courses</strong><p>' + '</li>';
	user_course += populateList(my_list);
	user_course += '</ul>';

	$('#forums_list').html(recent_forum + user_course);
	$('#user_panel').html(recent_forum + user_course);
>>>>>>> upstream/master

<<<<<<< HEAD
=======
var recentForumURL = "/developer/adehn3/api/techbook/recentforums/";
var myCoursesURL = "/developer/adehn3/api/techbook/mycourses/";

var course_threads = '{ "threadlist" :[{"course_name" : "CS6150","threads" : [{"thread_id" : "1","thread_subject" : "Info needed about class CS6150", "thread_content" : " Instructors: Gregory Abowd (Office hours: Mon 1-2pm in TSRB) and Santosh Vempala (Office hours: Fri, 9-10am in Klaus 2222) TA: Yi Han (Office hours: Tue, 1-3pm in TSRB).  C4G is a project-based research, education and service initiative of the College of Computing. ","posts" : [{"post_id" : "1","user" : "user1","post_text" : "We have a master list of project topics over the summer along with partners in the field for each topic. It will be up to student teams to determine their precise projects based on this list."}, {"post_id" : "2","user" : "user2","post_text" : "user2 first post"}]}, {"thread_id" : "2","thread_subject" : "Important topics for quiz ??", "thread_content" : " What are the most important topics for this quiz?? ","posts" : [{"post_id" : "1","user" : "Jon","post_text" : "Read all or none :) "}, {"post_id" : "2","user" : "Tom","post_text" : "You cannot read all :( "}]}]}, {"course_name" :"CS6250", "threads" : [{ "thread_id" : "1", "thread_subject" :"Need info on 6250", "thread_content" : "Is this an interesting class ??", "posts" : [{ "post_id" : "1", "user" : "user2", "post_text" :"user2 first post" }]}] }]}';
var active_course ;
var active_thread;
populateUserList();


>>>>>>> upstream/master
function populateUserList() {
	$('#forums_list').html('');
	var recent_forum = '<ul id="recent" data-role="listview" data-divider-theme="b" data-inset="false" data-min="true">';
	recent_forum += '<li  data-role="list-divider" role="heading">'
			+ '<p  align="center"><strong>Recent Forums</strong></p>' + '</li>';
	//data-role="list-divider" role="heading"
	// cannot pass the fn name as it is taking the entire fn defn as a string. hence appended to onClick
	$.getJSON(recentForumURL, function(data){
			fillDataInList('#forums_list', recent_forum, data, '<a href="#forum" onclick="showCollapsibleThreads');
	});
	
	var user_course = '<ul id="my_courses" data-role="listview" data-divider-theme="b" data-inset="false" data-min="true">';
	user_course += '<li data-role="list-divider" role="heading" >'
			+ '<p  align="center"><strong>My Courses</strong><p>' + '</li>';
	//data-role="list-divider" role="heading"
	$.getJSON(myCoursesURL, function(data){
			fillDataInList('#forums_list',user_course, data, '<a href="#course_info" onclick="showCourseInfo');
	});
	
	
}

function fillDataInList(parentObject, listHtml, data, onClickFn){
	//console.log(onClickFn);
	var limit = Math.min(5,data.length);
	for (var i = 0; i < limit; i++) {
		listHtml +=  '<li align="center">';
		
		listHtml += onClickFn+ '(\'' + data[i].courseNumber
				+ '\')">';
		listHtml += '<h3>' + data[i].courseNumber + '</h3>';
		listHtml += '</a>';
		listHtml += '</li>';
	}
	listHtml += '</ul>';
	$('#user_panel').append(listHtml);
	$(parentObject).append(listHtml);
	$('#home').trigger('pagecreate'); 
}

function showCourseInfo(course){
	active_course = course;
	$('#course_header h1').html( course);
//	$('#course_data').html('');
//	var course_detail ='';
//	course_detail += '<div id="goto_course"><a href="#forum" id="goto_course_button" type="button" data-inline="true" data-mini="true" data-icon="grid" onclick="showCollapsibleThreads(\''+ course +'\')"> Forum </a>';
//	course_detail += '<input id="mark_course_button" type="button" data-inline="true" data-mini="true" data-icon="Plus" onclick="addToMyCourses" value="Add to My course" /></div></div>';
//	course_detail += '<h3>About the course</h3>';
//	course_detail += '<div id="course_content"><p> This is a course in mobile app development and services being taught by  Russell J Clark</p></div>';
	
//	$('#course_data').html(course_detail);
//	$('#course_data').trigger('create');
}

function addToMyCourses(){
	var dept = active_course.substr(0,active_course.length - 4);
	$.post('/developer/adehn3/api/techbook/mycourses/',{"courseDepartment": dept, "courseCode" : active_course}, function (data) {
        console.log("success");
	   })
	   .done(function() {
	    console.log( "second success" );
	  })
	  .fail(function() {
	    console.log( "error" );
	  })
	  .always(function() {
		  
		  if ($('#mark_course_button').attr('value') == 'Add to My course') {
				$('#mark_course_button').parent().attr('data-theme', 'a');
				$('#mark_course_button').parent().attr('data-icon', 'check');
<<<<<<< HEAD

				$('#mark_course_button').val("Remove");
			} else
			{
				$('#mark_course_button').parent().attr('data-theme', 'd');
				$('#mark_course_button').parent().attr('data-icon', 'Plus');

				$('#mark_course_button').val("Add to My course");
			}
			$('#mark_course_button').button("refresh");
		  
	  });
};

=======

				$('#mark_course_button').val("Remove");
			} else
			{
				$('#mark_course_button').parent().attr('data-theme', 'd');
				$('#mark_course_button').parent().attr('data-icon', 'Plus');

				$('#mark_course_button').val("Add to My course");
			}
			$('#mark_course_button').button("refresh");
		  
	  });
};

>>>>>>> upstream/master
function showCollapsibleThreads(course) {
	active_course = course;
	// change the header of the page to course name
	$('#forum_header h1').html(course);
	var dept = course.substr(0,course.length - 4);
	// empty the previous contents of the thread container
	$('#thread_list').html('');
	
	$("#thread_list")
			.append(
					'<div id="thread-set" data-role="collapsible-set" data-inset="false"></div>');
	
	$.getJSON("/developer/adehn3/api/techbook/department/"+dept+"/class/"+course+"/forum",
	        function(data){
	        	  showThread(data,active_course);
	        });
}

$('#post_thread_form').submit(function(data){
	console.log($('#threadSubject').val());
	var dept = active_course.substr(0,active_course.length - 4);
	$.post('/d/adehn3/api/techbook/department/'+dept+'/class/'+active_course+'/forum/', $(this).serialize(), function (data) {
        console.log("success");
	   })
	   .done(function() {
	    console.log( "second success" );
	  })
	  .fail(function() {
	    console.log( "error" );
	  })
	  .always(function() {
		  showCollapsibleThreads(active_course);
	  });
	$('#threadSubject').val('');
	$('#threadPost').val('');
	
	$('#create_thread_popup').popup('close');
	return false;
	});
<<<<<<< HEAD

function showThread(threads, course){
		for (var i = 0; i < threads.length; i++) {
			var thread_data = threads[i];
			
			var cid = 'collapsible' + thread_data.threadID + course;
			
			$("#thread-set")
					.append(
							'<div id="'
									+ cid
									+ '" data-role="collapsible" data-inset="false" data-mini="true" data-collapsed="true"></div>');
			var collapDiv = "#" + cid;
			
			$(collapDiv).append('<h3>' + thread_data.prismID + '\t\t' + thread_data.timestamp + '<div id="content"><p><strong>'+ thread_data.subject +  '</strong></p></div></h3>'); //thread_data.subject
			$(collapDiv).append('<div id="content"><p>' + thread_data.first_post  + '</p></div>');
			$(collapDiv).append('<div id="replies'+thread_data.threadID+'"></div>');
			
			displayReplies(thread_data.threadID, course);

<<<<<<< HEAD
}
}

function changeMarkButton(i) {
	var checkid = $.find('#mark_button_id' + i);
	
		if ($(checkid).attr('value') == 'Mark') {
			$(checkid).parent().attr('data-theme', 'a');
			$(checkid).parent().attr('data-icon', 'check');

			$(checkid).val("Unmark");
		} else
		{
			$(checkid).parent().attr('data-theme', 'd');
			$(checkid).parent().attr('data-icon', 'star');

			$(checkid).val("Mark");
		}
		$(checkid).button("refresh");
}

function displayReplies(threadID, course){
	active_thread = threadID;
	var dept = course.substr(0,course.length - 4);
	$.getJSON("/developer/adehn3/api/techbook/department/"+dept+"/class/"+course+"/forum/"+threadID,
	      function(data){
    	  showReplies(data, course);
    	  $('#thread-set').trigger('create');
    	});
}

function showReplies(data, course){
	var repliesDiv = $('#replies' + data.threadID);
	var repId = 'replies' + data.threadID + course;
	var replies_html = '';
	repliesDiv.html(replies_html);
			
			replies_html +=	'<ul id="'
									+ repId
									+ '"data-role="listview" data-theme="d" data-divider-theme="b" data-inset="false" data-min="true">';
			
			replies_html +=	'<li data-role="list-divider" role="heading"> Replies <span class="ui-li-count">'+ data.posts.length + '</span></li>';
					for (var j = 0; j < data.posts.length; j++) {
						var post = data.posts[j];
						replies_html +=	'<li> <div><span style="text-align:left"><h1><i>' + post.prismID + '</i></h1></span><span style="text-align:right"><h3><strong>'+post.timestamp+'</strong></h3></span></div>' + '<textarea readonly>'
										+ post.post + '</textarea>' + '</li>';
					}
			var btnid = course + data.threadID;
			replies_html +=	'<textarea id="comment_text' + data.threadID + '" type="text" />' +
					'<input id="comment_button_id'
									+ data.threadID
									+ '" name="comment_button" type="submit" value="Add Comment" data-mini="true" data-inline="true" data-icon="plus"/><input id="mark_button_id' + btnid + '" type="submit"  onclick="changeMarkButton(\'' + btnid + '\')" value="Mark" data-mini="true" data-inline="true" data-icon="star"/></ul>';
		repliesDiv.append(replies_html);
		
		$('#comment_button_id'+data.threadID).click(function (){
			active_thread = data.threadID;
			var dept = course.substr(0,course.length - 4);
			$.post("/d/adehn3/api/techbook/department/"+dept+"/class/"+course+"/forum/"+ active_thread +"/post",{"postText" : $('#comment_text'+data.threadID).val()},
			        function(data){
					displayReplies(active_thread, course);
	        	});
		});
}

var classes_per_dept = JSON.parse('{"Architecture" : ["ARCH2112", "ARCH2012"],"School of Computer Science" : ["CS1331","CS1332"],"School of Interactive Computing" :["CS6750","CS7470"],"School of Computational Science and Engineering" : ["CSE6505","CSE4400"],"Electrical Engineering" : ["ECE2031","ECE2020"], "Mechanical Engineering" : ["ME3180","ME3322"]}');

function load_search(){
	$('#search_college h1').html("Search Department");
	$('#search_data').html('');
	  var schools = JSON.parse('["College of Architecture","College of Computing","College of Engineering","College of Sciences","Ivan Allen College of Liberal Arts","Scheller College of Business"]');
	  
	  var course_per_school = JSON.parse('{"College of Architecture" : ["Architecture"],"College of Computing":["School of Computer Science","School of Interactive Computing","School of Computational Science and Engineering"],"College of Engineering":["Electrical Engineering", "Mechanical Engineering"],"College of Sciences":["Maths","Physics"],"Ivan Allen College of Liberal Arts":["Economics","History, Technology, and Society","The Sam Nunn School of International Affairs","Literature, Media, and Communication","Modern Languages","Public Policy","ROTC"],"Scheller College of Business":["MBA Courses"]}');
	  var schools_html = '<div data-role="collapsible-set" data-inset="false">';
	  console.log("schools.length " + schools.length);
	  for(var i=0; i < schools.length ; i++){
		  schools_html += '<div data-role="collapsible">';
		  schools_html += '<h3>' + schools[i] + '</h3>';
		  schools_html += '<ul data-role="listview" data-inset="false" data-theme="b">';
		  
		  var list = course_per_school[schools[i]];
		  
		  for(var j=0; j < list.length ; j++){
			  schools_html  += '<li><a href="#search_class" onclick="show_class_list(\''+list[j]+'\')">' + list[j] + '</a></li>';
		  }
		  schools_html += '</ul>';
		  schools_html += '</div>';
	  }
	  schools_html += '</div>';
	  
	  $('#search_data').html(schools_html);
	  $('#search_data').trigger('create');
	};
	
	function show_class_list(dept_name){
		$('#search_class h1').html(dept_name);
		$('#search_class_data').html('');
		var classes = classes_per_dept[dept_name];
		var html = '<ul data-role="listview" data-filter="true" >';
		for(var i=0 ; i < classes.length ; i++){
			html += '<li><a href="#course_info" onclick="showCourseInfo(\''+ classes[i]+'\')">'+ classes[i]+ '</a></li>';
		}
		html += '</ul>';
		
		$('#search_class_data').html(html);
		$('#search_class_data').trigger('create');
	} 
=======
=======

function showThread(threads, course){
		for (var i = 0; i < threads.length; i++) {
			var thread_data = threads[i];
			
			var cid = 'collapsible' + thread_data.threadID + course;
			
			$("#thread-set")
					.append(
							'<div id="'
									+ cid
									+ '" data-role="collapsible" data-inset="false" data-mini="true" data-collapsed="true"></div>');
			var collapDiv = "#" + cid;
			
			$(collapDiv).append('<h3>' + thread_data.prismID + '\t\t' + thread_data.timestamp + '<div id="content"><p><strong>'+ thread_data.subject +  '</strong></p></div></h3>'); //thread_data.subject
			$(collapDiv).append('<div id="content"><p>' + thread_data.first_post  + '</p></div>');
			$(collapDiv).append('<div id="replies'+thread_data.threadID+'"></div>');
			
			displayReplies(thread_data.threadID, course);

<<<<<<< HEAD
>>>>>>> upstream/master
	// TODO
	// add the code to get the threads of a course
	var threadlist = $.parseJSON(course_threads);
	for (var i = 0; i < threadlist.threadlist.length; i++) {
		course_thread = threadlist.threadlist[i];
		if (course_thread.course_name == name) {
			showCollapsibleThreads(course_thread.course_name,
					course_thread.threads);
		}
	}
}

function showCollapsibleThreads(course, threads) {

	$("#thread_list")
			.append(
					'<div id="thread-set" data-role="collapsible-set" data-inset="false"></div>');
	for (var i = 0; i < threads.length; i++) {
		var thread_data = threads[i];
		// TODO
		// add the method to get the posts for a specific thread
		var cid = 'collapsible' + i + course;
		var repId = 'replies' + i + course;
		var repDiv = "#" + repId;
		$("#thread-set")
				.append(
						'<div id="'
								+ cid
								+ '" data-role="collapsible" data-inset="false"></div>');
		var collapDiv = "#" + cid;
		$(collapDiv).append('<h3>' + thread_data.thread_subject + '</h3>');
		$(collapDiv).append(
				'<p></strong>' + thread_data.thread_content + '</strong></p>');
		$(collapDiv)
				.append(
						'<ul id="'
								+ repId
								+ '"data-role="listview" data-theme="d" data-divider-theme="b" data-inset="false"></ul>');
		$(repDiv)
				.append(
						'<li data-role="list-divider" role="heading"> Replies <span class="ui-li-count">'
								+ thread_data.posts.length + '</span></li>');
		for (var j = 0; j < thread_data.posts.length; j++) {
			var post = thread_data.posts[j];
			$(repDiv).append(
					'<li> <h3><i>' + post.user + '</i></h3>' + '<p><strong>'
							+ post.post_text + '</strong></p>' + '</li>');
		}
		var btnid = course + i;
		$(repDiv)
				.append('<input id="comment_text' + i + ' type="text" />')
				.append(
						'<input id="comment_button_id'
								+ i
								+ '" name="comment_button" type="submit" value="Add Comment" data-mini="true" data-inline="true" data-icon="plus"/>')
				.append(
						'<input id="mark_button_id'
								+ btnid
								+ '" type="submit"  onclick="changeMarkButton(\''
								+ btnid
								+ '\')" value="Mark" data-mini="true" data-inline="true" data-icon="star"/>');
	}
	//$('#thread_set').trigger( 'updatelayout' );
	
	//$('#forum div[data-role="content"]').trigger('create')
	$('#thread_list').trigger('create');
}

function changeMarkButton(i) {
	var checkid = $.find('#mark_button_id' + i);
	
		if ($(checkid).attr('value') == 'Mark') {
			$(checkid).parent().attr('data-theme', 'a');
			$(checkid).parent().attr('data-icon', 'check');

			$(checkid).val("Unmark");
		} else
		{
			$(checkid).parent().attr('data-theme', 'd');
			$(checkid).parent().attr('data-icon', 'star');

			$(checkid).val("Mark");
		}
		$(checkid).button("refresh");

}
<<<<<<< HEAD
>>>>>>> upstream/master
=======

}
}

function changeMarkButton(i) {
	var checkid = $.find('#mark_button_id' + i);
	
		if ($(checkid).attr('value') == 'Mark') {
			$(checkid).parent().attr('data-theme', 'a');
			$(checkid).parent().attr('data-icon', 'check');

			$(checkid).val("Unmark");
		} else
		{
			$(checkid).parent().attr('data-theme', 'd');
			$(checkid).parent().attr('data-icon', 'star');

			$(checkid).val("Mark");
		}
		$(checkid).button("refresh");
}

function displayReplies(threadID, course){
	active_thread = threadID;
	var dept = course.substr(0,course.length - 4);
	$.getJSON("/developer/adehn3/api/techbook/department/"+dept+"/class/"+course+"/forum/"+threadID,
	      function(data){
    	  showReplies(data, course);
    	  $('#thread-set').trigger('create');
    	});
}

function showReplies(data, course){
	var repliesDiv = $('#replies' + data.threadID);
	var repId = 'replies' + data.threadID + course;
	var replies_html = '';
	repliesDiv.html(replies_html);
			
			replies_html +=	'<ul id="'
									+ repId
									+ '"data-role="listview" data-theme="d" data-divider-theme="b" data-inset="false" data-min="true">';
			
			replies_html +=	'<li data-role="list-divider" role="heading"> Replies <span class="ui-li-count">'+ data.posts.length + '</span></li>';
					for (var j = 0; j < data.posts.length; j++) {
						var post = data.posts[j];
						replies_html +=	'<li> <div><span style="text-align:left"><h1><i>' + post.prismID + '</i></h1></span><span style="text-align:right"><h3><strong>'+post.timestamp+'</strong></h3></span></div>' + '<textarea readonly>'
										+ post.post + '</textarea>' + '</li>';
					}
			var btnid = course + data.threadID;
			replies_html +=	'<textarea id="comment_text' + data.threadID + '" type="text" />' +
					'<input id="comment_button_id'
									+ data.threadID
									+ '" name="comment_button" type="submit" value="Add Comment" data-mini="true" data-inline="true" data-icon="plus"/><input id="mark_button_id' + btnid + '" type="submit"  onclick="changeMarkButton(\'' + btnid + '\')" value="Mark" data-mini="true" data-inline="true" data-icon="star"/></ul>';
		repliesDiv.append(replies_html);
		
		$('#comment_button_id'+data.threadID).click(function (){
			active_thread = data.threadID;
			var dept = course.substr(0,course.length - 4);
			$.post("/d/adehn3/api/techbook/department/"+dept+"/class/"+course+"/forum/"+ active_thread +"/post",{"postText" : $('#comment_text'+data.threadID).val()},
			        function(data){
					displayReplies(active_thread, course);
	        	});
		});
}

var classes_per_dept = JSON.parse('{"Architecture" : ["ARCH2112", "ARCH2012"],"School of Computer Science" : ["CS1331","CS1332"],"School of Interactive Computing" :["CS6750","CS7470"],"School of Computational Science and Engineering" : ["CSE6505","CSE4400"],"Electrical Engineering" : ["ECE2031","ECE2020"], "Mechanical Engineering" : ["ME3180","ME3322"]}');

function load_search(){
	$('#search_college h1').html("Search Department");
	$('#search_data').html('');
	  var schools = JSON.parse('["College of Architecture","College of Computing","College of Engineering","College of Sciences","Ivan Allen College of Liberal Arts","Scheller College of Business"]');
	  
	  var course_per_school = JSON.parse('{"College of Architecture" : ["Architecture"],"College of Computing":["School of Computer Science","School of Interactive Computing","School of Computational Science and Engineering"],"College of Engineering":["Electrical Engineering", "Mechanical Engineering"],"College of Sciences":["Maths","Physics"],"Ivan Allen College of Liberal Arts":["Economics","History, Technology, and Society","The Sam Nunn School of International Affairs","Literature, Media, and Communication","Modern Languages","Public Policy","ROTC"],"Scheller College of Business":["MBA Courses"]}');
	  var schools_html = '<div data-role="collapsible-set" data-inset="false">';
	  console.log("schools.length " + schools.length);
	  for(var i=0; i < schools.length ; i++){
		  schools_html += '<div data-role="collapsible">';
		  schools_html += '<h3>' + schools[i] + '</h3>';
		  schools_html += '<ul data-role="listview" data-inset="false" data-theme="b">';
		  
		  var list = course_per_school[schools[i]];
		  
		  for(var j=0; j < list.length ; j++){
			  schools_html  += '<li><a href="#search_class" onclick="show_class_list(\''+list[j]+'\')">' + list[j] + '</a></li>';
		  }
		  schools_html += '</ul>';
		  schools_html += '</div>';
	  }
	  schools_html += '</div>';
	  
	  $('#search_data').html(schools_html);
	  $('#search_data').trigger('create');
	};
	
	function show_class_list(dept_name){
		$('#search_class h1').html(dept_name);
		$('#search_class_data').html('');
		var classes = classes_per_dept[dept_name];
		var html = '<ul data-role="listview" data-filter="true" >';
		for(var i=0 ; i < classes.length ; i++){
			html += '<li><a href="#course_info" onclick="showCourseInfo(\''+ classes[i]+'\')">'+ classes[i]+ '</a></li>';
		}
		html += '</ul>';
		
		$('#search_class_data').html(html);
		$('#search_class_data').trigger('create');
	} 
>>>>>>> upstream/master
