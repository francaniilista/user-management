// Author: Paulo Franca <francaniilista@gmail.com>
require.config({
	paths : {
		jquery : 'assets/jquery/jquery-min',
		underscore : 'assets/underscore/underscore-min',
		backbone : 'assets/backbone/backbone-optamd3-min',
		bootstrap : 'assets/bootstrap/bootstrap-min',
		templates : '../templates'
	},
	urlArgs : "bust=" + (new Date()).getTime() //TODO: cache-buster: remove for production!!!
});

require([ 'app' ], function(App) {
	App.initialize();
});