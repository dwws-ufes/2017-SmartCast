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
You should now be ready to develop a Java EE project in Eclipse, deploying it in WildFly and configuring it to use MySQL database for persistence. The above steps need to be done just once for all projects which will use these tools. In the next step we start **Smartcast** with some project-specific configurations.


# Database creation and set-up
We will use JPA (Java Persistence API), one of the Java EE standards, persisting our objects in a relational database stored in the MySQL server. We need, therefore, to:

1. Create a database schema named **smartcast**;
2. Create a database user named smartcast with password smartcat;
3. Give user smartcast full permission for the schema **smartcast**.

To do that, use MySQL Workbench. Once you open it, connect to the server using the root user (the administrator). If you see an error message at the bottom of the screen indicating that a connection to the server could not be established, click on Server > Startup/Shutdown and click the button to start the server.

To create the database, click the Create a new schema button in the toolbar. Fill in **smartcast** as Schema Name and select utf8 - default collation as Default Collation. Finally, click Apply and then Apply again to create the database schema.

Next, click on Users and Privileges at the left-hand side of the Workbench's window and the Administration - Users and Privileges tab should open. Click on the Add Account button and fill in the **smartcast** user information.

With the SuggestionSpace folder uncompressed somewhere inside your computer go to eclipse and access file-> Open Projects to File System... and select the directory SuggestionSpace folder as import source

You can now close MySQL Workbench.

# Datasource configuration in WildFly



While we could configure JPA to connect to the database we have just created in a configuration file in our project, creating a datasource for it in WildFly allows us to use JTA (Java Transaction API), another standard from Java EE, which provides us with automatic transaction management.

To create a JTA datasource for **Smartcast** in WildFly, open the file **$WILDFLY_HOME/standalone/configuration/standalone.xml** and look for the tag <subsystem xmlns="urn:jboss:domain:datasources:4.0">. Inside this tag, there is a <datasources> tag which holds the configuration for the java:jboss/datasources/ExampleDS datasource that WildFly comes with. Next to it, add a datasource for the smartcast database in MySQL:

```html
<datasource jta="true" jndi-name="java:jboss/datasources/Smartcast" pool-name="SmartcastPool" enabled="true" use-java-context="true">
    <connection-url>jdbc:mysql://localhost:3306/smartcast</connection-url>
    <driver>mysql</driver>
    <security>
        <user-name>smartcast</user-name>
        <password>smartcast</password>
    </security>
</datasource>
```
# Project creation and JButler set-up in Eclipse
Import Projects from Git: in Eclipse, click on the File > Import > Project from a Git Repository > Clone URI. In **URI** add **https://github.com/dwws-ufes/2017-SmartCast**  and click Next > Finish. 

Everything should be error-free at this point and if you open Java Resources > Libraries > Maven Dependencies you should see some JARs: primefaces-6.1.jar, bootstrap-1.0.10.jar and jbutler-wp-1.2.4.jar (versions may vary if these components are updated after this tutorial was written).

If you want, you can delete the choose_remote_name/master branch by right-clicking on it under Smartcast > Branches > Remote Tracking at the Git Repositories view and selecting Delete Branch. Also, it makes sense to delete or to edit the README.md file, which was fetched from the base project and may cause confusion if you send this new project to a GitHub repository...

You might also want to make sure that the **<wb-module deploy-name="">** tag in .settings/org.eclipse.wst.common.component contains the same name as the context root of your WebApp. It may contain a version suffix (e.g., Smartcast-1.2.4), so remove it (e.g., make it Smartcast).

# Testing the application
To make sure everything is in order before we actually start developing the application, deploy and test it:

1. Open the Servers view, right-click the WildFly server and choose Add and Remove.... Move the Smartcast project from the Available list to the Configured list and click Finish;

2. Make sure the MySQL server is already started, start the WildFly server and open http://localhost:8080/Smartcast/ -- the initial screen should be shown, as in the figure below;

# Authors

@github/JMoicano

@github/pablobrunetti
