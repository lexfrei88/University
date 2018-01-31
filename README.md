# University Selection Committee

### Heroku:

 * Current application is deployed on Heroku and available by link [University-epam](https://university-epam.herokuapp.com)
 
   **NOTE!** It could take about 1 min to start up application after you push enter in browser line, 
   because hosting is free and it undeploy app if it was inactive for some time, so be patient.

### How to use:

1. Download source code : `https://github.com/lexfrei88/University.git`

2. Configure database connection via `committee-web/src/main/resources/app-context.xml`. It's beans declaration file where you need to
 rewrite necessary parameters (such as _userName_ or _password_) for `ConnectionPool` bean
 
3. Now you can execute `startUp.bat [host] [username]` in command line and then enter _\[password\]_ to 
 your database.
    - _\[host\]_ - Connect to MySQL server on given host
    - _\[username\]_ - MySQL user name to use when connecting to server
    - _\[password\]_ - The password to use when connecting to the server.
    
    NOTE! This option is available only if you have necessary environment variables such as CATALINA_HOME, 
    and PATH variables for Maven, Tomcat, MySQL
 
3. Create scheme in MySQL database with `sql/initDb.sql` script and then fill it with `sql/populateDb.sql`

4. Build with maven command: `mvn package`

5. Now you can take `war` file from web module and deploy it to tomcat


### FAQ

#### Why do java webapps use 'do' extension, where did it come from?

 * https://stackoverflow.com/questions/3597582/why-do-java-webapps-use-do-extension-where-did-it-come-from
: According to this there's no technical reason to use anything to specified commands so i decide not to 

#### Specify xml file with application beans:
 * in web.xml write init-param with a name "app-context" and value equal to path to file from classpath.

#### Specify xml file with web-commands beans:
 * in web.xml write init-param with a name "web-commands" and value equal to path to file from classpath.

#### To specify database properties file:
 * set necessary method-params while describe ConnectionPool in application context xml.
 * set single method-param while describe ConnectionPool in application context xml that is a path
to a properties file that contains database configuration in appropriate format
 * leave no method-params then by default it will look for a properties file with name `db.xml` from root of the `war`
