// Author: Paulo Franca <francaniilista@gmail.com>
define(['jquery', 'backbone', 'views/indexView'], 
		function($, Backbone, IndexView) {
	
	"use strict";
	
	var Router = Backbone.Router.extend({

		routes: {
			
			"": "home",
			"index": "home",
		},
			
		initialize: function() {
			console.log("started...");
			this.indexView = new IndexView();
			this.indexView.render(); 
		},
		
		home: function() {
			console.log("home");
			this.indexView.render();
		}
	});
	
	return Router;
});