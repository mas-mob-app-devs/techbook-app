var recent_list = [ 'CS6250', 'EC6505', 'ME3303' ];
var my_list = [ 'CS6250', 'CS6150', 'CS3303' ];

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

}

function populateList(list) {
	var output = '';
	for (var x = 0; x < list.length; x++) {
		output += '<li align="center">';
		output += '<a href="#forum" onclick="showCourseThread(\'' + list[x]
				+ '\')">';
		output += '<h3>' + list[x] + '</h3>';
		output += '</a>';
		output += '</li>';
	}
	return output;
}

function showCourseThread(name) {
	// change the header of the page to course name
	$('#forum_header h1').html(name);

	// empty the previous contents of the thread container
	$('#thread_list').html('');

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
