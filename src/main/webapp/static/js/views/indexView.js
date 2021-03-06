//Author: Paulo Franca <francaniilista@gmail.com>
define(['backbone', 'text!../templates/home.html'], 
		function(Backbone, HomeViewTemplate) {
	
	'use strict';
	var IndexView = Backbone.View.extend({
		
		el: '#main',
		
		template: HomeViewTemplate,
		
		initialize: function() {
			
		},
		
		events: {
			
		},
		
		render: function() {
			$(document).attr("title", "Dashboard | User Management");
			$('#dashboard').addClass("active");
			$('#users').removeClass("active");
			$(this.el).html(this.template);
			return this;
		}
	});
	
	return IndexView;
});