color 4 & title startBot
set MAVEN_HOME=D:\mvn
set JAVA_HOME=D:\18
set MYSQL_HOME=D:\mysql
cd /D D:\mysql\bin
start mysqld --console
timeout 10
cd /D D:\jsource\bots
mvn clean compile exec:java