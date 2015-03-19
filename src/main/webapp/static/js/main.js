// Author: Paulo Franca <francaniilista@gmail.com>
require.config({
	paths : {
		jquery     : 'assets/jquery-1.11.2.min',
		underscore : 'assets/underscore-min',
		text       : 'assets/text',
		backbone   : 'assets/backbone-min',
		bootstrap  : 'assets/bootstrap.min', 
		require    : 'assets/require.min',
		templates  : 'templates'
	},
	
	shim:  {
		"jquery": {
			exports: "$"
		},
		"underscore": {
			exports: "_"
		},
		"backbone": {
			deps: ["jquery", "underscore"],
			exports: "Backbone"
		},
		"bootstrap": {
			deps: ["jquery"]
		}
	}
});

require([ 'app' ], function(App) {
	App.initialize();
});