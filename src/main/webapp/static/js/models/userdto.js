define(["backbone"], function(Backbone) {
	
	'use strict';
	
	var UserDTO = Backbone.Model.extend({
		url: '/user-management/rest/user/'
	});
	
	return UserDTO;
});