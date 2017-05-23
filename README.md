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
```java
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


Installing

With the SuggestionSpace folder uncompressed somewhere inside your computer go to eclipse and access file-> Open Projects to File System... and select the directory SuggestionSpace folder as import source

# Authors
Joao Mario Soares Silva
Pablo Brunetti dos Santos
