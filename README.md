#### Why do java webapps use 'do' extension, where did it come from?

 * https://stackoverflow.com/questions/3597582/why-do-java-webapps-use-do-extension-where-did-it-come-from

#### Specify xml file with application beans:
- in web.xml write init-param with a name "app-context" and value equal to path to file from classpath.

#### Specify xml file with web-commands beans:
- in web.xml write init-param with a name "web-commands" and value equal to path to file from classpath.

#### Еo specify database properties file:
- set necessary method-param while describe ConnectionPool in application context xml.