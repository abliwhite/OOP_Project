/**
 * 
 */

function fadeAlertMessage(id) {
	$("#"+id).fadeTo(1800, 600).slideUp(600, function() {
		$("#"+id).slideUp(600);
	});
}