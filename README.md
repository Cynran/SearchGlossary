# Search Glossary

Search glossary is a simple implementation of an autocomplete and search functionality specifically tailored for data containing specialized terms with their meanings such as a glossary. 
You can see the working example here: http://glossary.utfrpgbg.com/

## Features

Search Glossary is a simple web application with:
- a minimalistic user interface optimized for different display sizes
- an autocomplete and search service
- http request and response handling

The code can easily adapted to work with different datasources as long as the structure (keywords and their description) remains the same.

## Built With

I created Search Glossary using the following:
- Spring boot with Spring Web, JPA and JDBC
- JavaEE
- Maven
- Eclipse
- Pure Javascript and a bit of jQuery

For testing:
- Spring Test
- Mockito
- JUnit

## Getting Started

You should be able to run the application right away, however if you want it to use a different database you will have to change some things described in the next section.

### Changing datasource (optional)
1. Prepare the table and data in your database. I recommend to use the script in the schema.sql file so you won't have to change things in the code.
2. Modify the application.properties file to have the necessary configuration for your database
[Here](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.data) is a list of common properties you could use.
For mysql 8 I used this:
```
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.datasource.url=jdbc:mysql://example:3306/example
spring.datasource.username=username
spring.datasource.password=password
```
3. Add the new dependency in the pom.xml (mysql8 is already included)
4. (optional) modify the GlossaryEntry class if your data structure is different

### Changing data (optional)
If you are okay to use H2 in memory, but want to use your own data, you will have to add your own data.sql. You will also have to change the tests as described in the next section.

### Running the tests
You can find the tests in src/test/java and they shouldn't show any errors initially. 
If you use different data than provided, you have to change the test values where indicated.

## Installation
I use Maven to install the application from Eclipse.

In Eclipse:
1. right click on project name
2. hover over to Run as...
3. choose Maven install

It is configured to create a WAR file for you once successfully run, so it should be easy to deploy it somewhere. 
I used [AWS Elastic Beanstalk](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/create_deploy_Java.html) for my project.

## Contributing
Feel free to contribute. Pull requests are welcome. 
Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)