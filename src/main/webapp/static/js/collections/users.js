//Author: Paulo Franca <francaniilista@gmail.com>
define(["backbone", "../models/userdto"], function(Backbone, UserDTO) {
	
	'user strict';
	
	var Users = Backbone.Collection.extend({
		model: UserDTO,
		
		url: '/user-management/rest/user'
	});
	
	return Users;
});