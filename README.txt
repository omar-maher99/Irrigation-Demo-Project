Hi, 
I'm omar maher and this is README.txt for the demo project of an irrigation system.

Used technologies in the task:

1- JDK 17
2- Maven 3.8.1
3- SprinBoot 3
4- Intellij 2021 community edition
5- MySQL Server
6- MySQL Workbench

How to run:
1- import the project in any IDE
2- make sure to have internet connection for maven to install the dependencies
3- after making sure to install all dependencies and a suitable JDK (17 or higher), configure the database credintials then, run the application.
4- Once the application is running the database table will be created in the provided schema.
5- the server will use port 8088 (As specified in the 'application.properties' file)
6- run the provided sql scripts in a SQL environment like workbench(To get some seed data)
7- use the Following APIs to do the required functionalities:
	*the url should be as follows: "http://localhost:8088/plotOfLand/" the the path of the needed method*
	1- create-plot (must provide a body) (POST)
	2- /update-plot/{id for the plot to update} (must provide a body) (PUT)
	3- /get-all-plot-of-lands (GET)
	4- /get-plots-need-irrigation (GET)
	5- /sensor-irrigate-all-plots (must provide a body) (PUT)
	6- /sensor-irrigate-a-plot/{id for the plot to irrigate}
 
Finally, it is only a backend application that consists 6 APIs, no front-end cause i did not use angular before. it may not match all the requirements (due to a busy week) but all the functionalities are provided. Hope to hear from you soon.