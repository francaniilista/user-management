// Author: Paulo Franca <francaniilista@gmail.com>
define(['jquery', 'backbone', 'views/indexView', "views/userView"], 
		function($, Backbone, IndexView, UserView) {
	
	"use strict";
	
	var Router = Backbone.Router.extend({

		routes: {
			
			'' : 'home',
			'users' : 'users',
			'users/:id' : 'showUser'
		},
			
		initialize: function() {
			Backbone.history.start();
			console.log("started..."); 
		},
		
		home: function() {
			console.log("home");
			this.indexView = new IndexView();
			this.indexView.render();
		},
		
		users: function() {
			console.log("Loading user screen...");
			this.userView = new UserView();
			this.userView.render();
		},
		
		showUser: function(id) {
			console.log("Showing user with id: " + id);
		}
	});
	
	return Router;
});