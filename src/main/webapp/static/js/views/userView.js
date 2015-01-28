//Author: Paulo Franca <francaniilista@gmail.com>
define(['backbone', 'text!../templates/users.html', 'bootstrap'], 
		function(Backbone, UserViewTemplate) {
	
	'use strict';
	var UserView = Backbone.View.extend({
		
		el: '#main',
		
		template: UserViewTemplate,
		
		initialize: function() {
			
		},
		
		events: {
			'click #btnCreate': 'openUserModal'
		},
		
		render: function() {
			$(this.el).html(this.template);
			return this;
		},
		
		openUserModal: function() {
			$("#create").modal();	
		}
	});
	
	return UserView;
});