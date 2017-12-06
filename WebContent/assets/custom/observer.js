/**
 * javascript functions for observer system
 */
var LIGHT_IMAGE = 'light.png';
var MOTION_IMAGE = 'person.png';

var TYPE_LIGHTON = 'LightOn';
var TYPE_MOVING = 'Moving';

var $eventContainer = $('#event_container');

var item = '<li class="clearfix">' + 
		   '	<div class="wi">' +
		   '		<img src="assets/custom/%IMAGE%" alt="">' +
		   '	</div>' +
		   '	<div class="wb">' +
		   '		%INFO%' +
		   '		<span class="post-date">%TIME%</span>' +
		   '	</div>' +
		   '</li>';

$(document).ready(function() {
	setInterval(function(){ updateEvents() }, 5000);
});

function updateEvents() {
	$.ajax({
		url: 'receiveEvents',
		type: 'post',
		dataType: 'json',
		data: {
			'top': 37
		},
		success: function(data, status, xhr) {
			var itemX;
			$eventContainer.empty();
			$.each(data, function(idx, val) {
				if (val.type == TYPE_LIGHTON) {
					itemX = item.replace('%IMAGE%', LIGHT_IMAGE).
								replace('%TIME%', new Date(val.time).toLocaleString()).
								replace('%INFO%', 'Light Is On');
				} else if (val.type == TYPE_MOVING) {
					itemX = item.replace('%IMAGE%', MOTION_IMAGE).
								replace('%TIME%', new Date(val.time).toLocaleString()).
								replace('%INFO%', 'Detected Motions');
				}
				$eventContainer.append(itemX);
			});
		},
		error: function(xhr, status, error) {
			
		}
	});
}

