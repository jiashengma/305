# 305
#TODO
[x] notify user to login when they try to book a flight (in handler for booking
form submission: get session, check if session("user")==null)
[] add registration validation (use javax validation + hibernate validator)

# Setup (Accurate as of November 13)
- Download maven and place the bin directory into the environment variables
- Download 'MySQL Community Server' from 'MySQL Community Downloads' (not MySQL Workbench)
- Proceed through download process until you hit the 'Select Product and Features Page'
	- Select 'MySql Servers' -> 'MySQL Server' -> latest version
	- Feel free to select any other products you may deem useful
	- Proceed to fill in user details and finish
- Setting up the database
	- log into thr mysql account and run the following commands (high-tech security)
		- create user 'ajaxadmin'@'localhost' identified by 'passwordajax305';
		- grant all privileges on ajax305.* to 'ajaxadmin'@'localhost';
		- CREATE DATABASE ajax305;
		- use ajax305;
		- source .\src\main\resources\localdump.sql
	- For Powershell users, may also run
		- cmd.exe /c "mysql -u ajaxadmin -p ajax305 < .\src\main\resources\localdump.sql"
	- For macOS or Linux, run the following command
		- mysql -u ajaxadmin -p ajax305 < .\src\main\resources\localdump.sql


# Using AJAX Program
- git clone out project at 'https://github.com/jiashengma/305'
	- To easily view our code -> Open Intellij and open the 'pom.xml' which will auto import everything
- Once again, we are using maven so to run
	- mvn clean tomcat7:run
	- head over to localhost / server and run gui application


# Possible error messages and corresponding solutions
	- 'Cannot establish database connection.'
		- Refer to # Setup -> 'Setting up the database'
	- Project does not compile under maven
		- Consider adding this block into plugins section of the pom.xml file
		    <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>

 # logining into db
 	mysql -u ajaxadmin -P ajax305
 	password: passwordajax305

 	verify tables with 'show tables;'
