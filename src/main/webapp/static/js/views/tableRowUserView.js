//Author: Paulo Franca <francaniilista@gmail.com>
define(['backbone', 'underscore', 'text!../templates/user/tableRowUser.html', 'bootstrap'], 
		function(Backbone, _, TableRowUserTemplate) {
	
	'use strict';
	
	var TableRowUserView = Backbone.View.extend({
		
		el: '#rows',
		
		template: _.template(TableRowUserTemplate),
		
		initialize: function(collection) {
			this.users = collection;
		},
		
		render: function() {
			console.log("Rendering Table row...");
			this.template({users: this.users});
			return $(this.el).html(this.template);
		}
	});
	
	return TableRowUserView;
});