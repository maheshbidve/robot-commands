'use strict';

var app = angular.module('app', []);

app.controller('robotController', function($scope, $http) {

	var urlBase = '';

	$scope.move_script = `POSITION 1 3 EAST 
FORWARD 1 
WAIT 
TURNAROUND 
FORWARD 1 
RIGHT 
FORWARD 2`;


	$scope.updateRobot = function() {
		return $http.post(urlBase + '/v1/robots/control?script=' + $scope.move_script.replace(/(\r\n|\n|\r)/gm, "<br>"))
			.then(function successCallback(response) {
				if (response.data.error) {
					return null;
				} else {
					if (response.data !== null && response.data !== undefined) {
						var canvas = document.getElementById("robotTabletopCanvas");
						var ctx = canvas.getContext("2d");
						ctx.clearRect(0, 0, canvas.width, canvas.height);

						ctx.fillStyle = "#FF0000";
						ctx.fillRect(response.data.x * 50, 250 - response.data.y * 50, 50, 50);
					} else {
						return null;
					}
				}
			}, function errorCallback(response) {
				$scope.error = true;
				$scope.errorMessage = response.data.message;
			});
	};

	$scope.drawRobot = function() {
		var canvas = document.getElementById("robotTabletopCanvas");
		var ctx = canvas.getContext("2d");
		ctx.fillStyle = "#FF0000";
		ctx.fillRect(0, 250, 50, 50);
	};

});


