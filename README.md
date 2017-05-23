# 2017-SmartCast
Assignment for the 2017 edition of the "Web Development and the Semantic Web" course, by Joao Mario Soares Silva and Pablo Brunetti dos Santos

# Gettin Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

# Tool installation and configuration
- Java SE Development Kit 8 (download);
- Eclipse IDE for Java EE Developers, codename Neon (version 4.6) (download);
- WildFly 10.1, certified Java EE 7 (download);
- MySQL Community Server 5.7 (download);
- MySQL Workbench 6.3 (download);
- MySQL Connector/J JDBC Driver 5.1 (download).
Installation instructions vary according to operating system (Linux, MacOS or Windows), therefore we have not included detailed, step-by-step instructions on how to install these tools. In general, for Eclipse and WildFly it's enough to unpack them somewhere in your hard drive. We'll refer to the folder where you unpacked these tools as $ECLIPSE_HOME and $WILDFLY_HOME, respectively. The JDK and the MySQL server and workbench can be installed using an install wizard downloaded from their website or through your system's package manager (e.g.: apt-get in Ubuntu Linux or any other Debian-based distributions). Finally, the Connector/J driver will be used during WildFly's configuration.

# Set-up WildFly to connect to MySQL

WildFly comes with an H2 Database driver configured. In this tutorial, however, we use MySQL, so we need to add its driver to WildFly's configuration. Follow these steps to do it (make sure the server is stopped for this):

In the folder $WILDFLY_HOME/modules, create the following directory structure: com/mysql/main (we are using / to separate sub-directories; Windows users should replace it with \);

Unpack the MySQL Connector/J JDBC Driver you downloaded earlier and copy the file mysql-connector-java-5.1.41-bin.jar to the newly created folder $WILDFLY_HOME/modules/com/mysql/main. If you downloaded a different version of Connector/J, adjust the name accordingly;

Still at $WILDFLY_HOME/modules/com/mysql/main, create a file named module.xml with the following contents (again if you downloaded a different version of Connector/J, adjust the name accordingly):

```html
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.1" name="com.mysql">
	<resources>
		<resource-root path="mysql-connector-java-5.1.41-bin.jar"/>
	</resources>
	<dependencies>
		<module name="javax.api"/>
	</dependencies>
</module>
```

Now open the file $WILDFLY_HOME/standalone/configuration/standalone.xml and look for the tag <subsystem xmlns="urn:jboss:domain:datasources:4.0">. Inside this tag, locate <datasources> and then <drivers>. You should find the H2 Database driver configuration there. Next to it, add the configuration for MySQL Connector/J, as following:

```html
<driver name="mysql" module="com.mysql">
	<driver-class>com.mysql.jdbc.Driver</driver-class>
</driver>
```
You should now be ready to develop a Java EE project in Eclipse, deploying it in WildFly and configuring it to use MySQL database for persistence. The above steps need to be done just once for all projects which will use these tools. In the next step we start Smartcast with some project-specific configurations.


# Database creation and set-up
We will use JPA (Java Persistence API), one of the Java EE standards, persisting our objects in a relational database stored in the MySQL server. We need, therefore, to:

1. Create a database schema named smartcast;
2. Create a database user named smartcast with password smartcat;
3. Give user smartcast full permission for the schema marvin.
To do that, use MySQL Workbench. Once you open it, connect to the server using the root user (the administrator). If you see an error message at the bottom of the screen indicating that a connection to the server could not be established, click on Server > Startup/Shutdown and click the button to start the server.

To create the database, click the Create a new schema button in the toolbar. Fill in **smartcast** as Schema Name and select utf8 - default collation as Default Collation. Finally, click Apply and then Apply again to create the database schema.

Next, click on Users and Privileges at the left-hand side of the Workbench's window and the Administration - Users and Privileges tab should open (see figure below). Click on the Add Account button and fill in the marvin user information like shown in the figure below (the password, which is hidden in the figure, should be marvin as well):

With the SuggestionSpace folder uncompressed somewhere inside your computer go to eclipse and access file-> Open Projects to File System... and select the directory SuggestionSpace folder as import source

# Authors
Joao Mario Soares Silva
Pablo Brunetti dos Santos
