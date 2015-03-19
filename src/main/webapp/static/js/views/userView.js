//Author: Paulo Franca <francaniilista@gmail.com>
define(['backbone', '../models/userdto', '../collections/users', 'text!../templates/users.html', '../views/tableRowUserView', 'bootstrap'], 
		function(Backbone, UserDTO, Users, UserViewTemplate, TableRowUserView) {
	
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
			
			console.log("Fetching users...")
			this.collection.fetch();
			console.log(this.collection.size());
			
			this.tableRowUserView = new TableRowUserView(this.collection);
			this.tableRowUserView.render();
			return this;
		},
		
		openUserModal: function() {
			console.log("Opening user modal...")
			$("#modalCreate").modal('show');	
		},
		
		searchUser: function(field) {
			console.log("Searching user...");
		},
		
		saveUser: function() {
			console.log("Saving user...");
			var model = new UserDTO({
				email: $('#inputEmail').val(),
				password: $('#inputPassword').val(),
				username: $('#inputUsername').val()
			});
			model.save();
			this.collection.add(model);
			this.cleanFields();
			$("#modalCreate").modal('hide');	
		},
		
		cancel: function() {
			console.log("Canceling user fill...");
			$("#modalCreate").modal('hide')
		},
		
		cleanFields: function() {
			console.log("Cleaning create user's fields...");
			$("inputEmail").val('');
			$("inputUsername").val('');
			$("inputPassword").val('');
		}
	});
	
	return UserView;
});