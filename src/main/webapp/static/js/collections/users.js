//Author: Paulo Franca <francaniilista@gmail.com>
define(["backbone", "../models/user"], function(Backbone, User) {
	
	'user strict';
	
	var Users = Backbone.Collection.extend({
		model: User,
		
		url: '/user-management/rest/user'
	});
	
	return Users;
});