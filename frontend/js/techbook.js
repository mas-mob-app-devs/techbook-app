var recent_list = [ 'CS6250', 'EC6505', 'ME3303' ];
var my_list = [ 'CS6250', 'CS6150', 'CS3303' ];

var course_threads = '{ "threadlist" :[{"course_name" : "CS6150","threads" : [{"thread_id" : "1","thread_subject" : "Wats up 6150", "thread_content" : " Can any one tell me what is going on in this class ?? ","posts" : [{"post_id" : "1","user" : "user1","post_text" : "user1 first post"}, {"post_id" : "2","user" : "user2","post_text" : "user2 first post"}]}, {"thread_id" : "2","thread_subject" : "Important topics for quiz ??", "thread_content" : " What are the most important topics for this quiz?? ","posts" : [{"post_id" : "1","user" : "Jon","post_text" : "Read all or none :) "}, {"post_id" : "2","user" : "Tom","post_text" : "You cannot read all :( "}]}]}, {"course_name" :"CS6250", "threads" : [{ "thread_id" : "1", "thread_subject" :"Need info on 6250", "thread_content" : "Is this an interesting class ??", "posts" : [{ "post_id" : "1", "user" : "user2", "post_text" :"user2 first post" }]}] }]}';
populateUserList('Ranjan');

function populateUserList(user) {
	var panel_data = '<ul data-role="listview" data-divider-theme="b" data-inset="false">';
	panel_data += '<li data-role="list-divider" role="heading" >'
			+ '<p  align="center"><strong>Recent Forums</strong></p>' + '</li>';
	var recent_forums = populateList(recent_list);
	var my_courses = populateList(my_list);
	panel_data += recent_forums;
	panel_data += '<li data-role="list-divider" role="heading">' + '<p  align="center"><strong>My Courses</strong><p>'
			+ '</li>';
	panel_data += my_courses;
	panel_data += '</ul>';
	$('#forums_list').html(panel_data);
	$('#user_panel').html(panel_data);	

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

	//TODO
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
	
	$("#thread_list").append('<div id="thread-set" data-role="collapsible-set" data-inset="false"></div>');
	for (var i = 0; i < threads.length; i++) {
		var thread_data = threads[i];
		//TODO
		//add the method to get the posts for a specific thread
		var cid = 'collapsible'+i;
		var repId = 'replies'+i;
		var repDiv = "#"+repId;
		$("#thread-set").append(
				'<div id="'+cid+'" data-role="collapsible" data-inset="false"></div>');
		var collapDiv = "#"+cid;
		$(collapDiv).append('<h3>' + thread_data.thread_subject + '</h3>');
		$(collapDiv).append('<p></strong>' + thread_data.thread_content + '</strong></p>');
		$(collapDiv).append('<ul id="'+repId+'" data-role="listview" data-theme="d" data-divider-theme="b" data-inset="false"></ul>');
		$(repDiv).append('<li data-role="list-divider" role="heading"> Replies <span class="ui-li-count">' + thread_data.posts.length +'</span></li>');
		for(var j=0; j < thread_data.posts.length ; j++){
			var post = thread_data.posts[j];
			$(repDiv).append('<li> <h3><i>' + post.user + '</i></h3>'
				+ '<p><strong>' + post.post_text + '</strong></p>'
				+'</li>' );
		}
		$(repDiv)
	    .append('<input type="text" />')
	    .append('<input id="button" type="submit" value="Add Comment" data-mini="true" data-inline="true"/>')
	    .append('<input id="button" type="submit" value="Mark Post" data-mini="true" data-inline="true"/>');
	}
	$('#thread_list').trigger('create');
}
