#	Idealo ROBOT Challenge
The api documentation for this project can be found at following location

http://localhost:8080/swagger-ui.html

Following API details can be used by client
 
 URL 		 : http://localhost:8080/v1/robots/control 
 
 Method Type : GET
 
 Params		 : 
 	
 	Name		: script
 	
 	Type		: String
 	
 	Example :   "POSITION 1 3 EAST 
				FORWARD 1 
				WAIT 
				TURNAROUND 
				FORWARD 1 
				RIGHT 
				FORWARD 2"
				
				
Incomplete Tasks
1. More generic logic to accept different commands in controller
2. Add functionality to send multiple commands from UI
3. Render the robot in more descriptive manner
4. Define board size at backend in more flexible manner i.e. from configuration or UI params