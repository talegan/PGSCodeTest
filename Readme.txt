Installation Setup :

A). Pre-requisites
	1. Java
	2. Maven
	3. Eclipse
	4. Eclipse Plugins
		a. Maven
		b. Cucumber

B). Setting up selenium-cucumber-java
	1. Install Java and set path.
	2. Install Maven and set path.
	3. Clone respective repository or download zip.
		a. maven : https://github.com/talegan/PGSCodeTest

C). Running features :
	PreRequisite
	1. Path for Chrome browser exe must be set in Environment variable
	
	Steps to Execute Test:
	1. Right Click on pom.xml and Run As Maven Test
	2. To exclude scenario mark @Ignore in feature file
	
	Test Report:
	1. Go to target > cucumber folder 
	2. Open index.html report in web browser