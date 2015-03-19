//Author: Paulo Franca <francaniilista@gmail.com>
define(['backbone', 'underscore', 'text!../templates/user/tableRowUser.html', 'bootstrap'], 
		function(Backbone, _, TableRowUserTemplate) {
	
	'use strict';
	
	var TableRowUserView = Backbone.View.extend({
		
		el: '#rows',
		
		template: _.template(TableRowUserTemplate),
		
		initialize: function(collection) {
			this.users = collection;
			this.listenTo(this.users, 'add reset remove', this.render);
		},
		
		render: function() {
			console.log("Rendering Table row...");
			if (this.users.size() > 0) {
				$(this.el).html(this.template({users: this.users}));
			}
			return $(this.el);
		}
	});
	
	return TableRowUserView;
});