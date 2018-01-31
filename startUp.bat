call mysql -h %1 -u %2 -p < sql\initAndPopulate.sql
call mvn clean package -DskipTests
call xcopy ".\committee-web\target\sc.war" "%CATALINA_HOME%\webapps" /Y
call catalina start