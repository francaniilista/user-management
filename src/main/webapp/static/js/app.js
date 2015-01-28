// Author: Paulo Franca <francaniilista@gmail.com>
define([ 'jquery', 'underscore', 'backbone', 'router' ], 
		function($, _, Backbone, Router) {

	var App = new (function() {
		this.router = {};
		
		this.initialize = function() {
			this.router = new Router();
		}
	});
	return App;
});