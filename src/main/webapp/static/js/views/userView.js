//Author: Paulo Franca <francaniilista@gmail.com>
define(['backbone', '../collections/users', 'text!../templates/users.html', '../views/tableRowUserView', 'bootstrap'], 
		function(Backbone, Users, UserViewTemplate, TableRowUserView) {
	
	'use strict';
	
	var UserView = Backbone.View.extend({
		
		el: '#main',
		
		template: UserViewTemplate,
		
		collection: new Users(),
	
		initialize: function() {
			this.setPageContent();
		},
		
		events: {
			'click #btnOpenCreateUser': 'openUserModal',
			'click #btnSearch': 'searchUser',
			'click #btnCreateUser': 'saveUser',
			'click #btnCancel' : 'cancel'
			
		},
		
		setPageContent: function() {
			console.log("Setting page content...");
			$(document).attr("title", "Users | User Management");
			$('#dashboard').removeClass("active");
			$('#users').addClass("active");
		},
		
		render: function() {
			console.log("Rendering page...");
			$(this.el).html(this.template);
			this.collection.fetch({reset: true});
			this.tableRowUserView = new TableRowUserView(this.collection);
			this.tableRowUserView.render();
			return this;
		},
		
		openUserModal: function() {
			console.log("Opening user modal...")
			$("#modalCreate").modal();	
		},
		
		searchUser: function(field) {
			console.log("Searching user...");
		},
		
		saveUser: function() {
			console.log("Saving user...");
		},
		
		cancel: function() {
			console.log("Canceling user fill...");
			$("#modalCreate").modal('hide')
		}
	});
	
	return UserView;
});